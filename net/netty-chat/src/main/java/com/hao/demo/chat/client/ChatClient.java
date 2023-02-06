package com.hao.demo.chat.client;

import com.hao.demo.chat.message.ChatRequestMessage;
import com.hao.demo.chat.message.GroupChatRequestMessage;
import com.hao.demo.chat.message.GroupCreateRequestMessage;
import com.hao.demo.chat.message.GroupJoinRequestMessage;
import com.hao.demo.chat.message.GroupMembersRequestMessage;
import com.hao.demo.chat.message.GroupQuitRequestMessage;
import com.hao.demo.chat.message.LoginRequestMessage;
import com.hao.demo.chat.message.LoginResponseMessage;
import com.hao.demo.chat.server.protocol.MessageCodecSharableHandler;
import com.hao.demo.chat.server.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatClient {

    // 存储变量
    private static final AtomicBoolean LOGIN = new AtomicBoolean(false);
    private static final AtomicBoolean EXIT = new AtomicBoolean(false);
    private static final CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);

    // 用于 Message 和 ByteBuf 的编码解码器，是个双向处理器
    private static final MessageCodecSharableHandler MESSAGE_CODEC_HANDLER = new MessageCodecSharableHandler();

    // 标准输入流
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                // 首先添加长度域处理器，解决半包粘包问题
                ch.pipeline().addLast(new ProtocolFrameDecoder());
                // 添加消息编码解码器进行 ByteBuf 和 Message 间的互转
                ch.pipeline().addLast(MESSAGE_CODEC_HANDLER);
                // 针对该 Client 的业务处理
                ch.pipeline().addLast(new BusinessHandler());

            }
        });

        Channel channel = bootstrap.connect(new InetSocketAddress(8080)).sync().channel();
        channel.closeFuture().sync();
    }

    private static class BusinessHandler extends ChannelInboundHandlerAdapter {

        // 处理 read 事件，在接收到 server 的响应后调用
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.debug("收到响应：{}", msg);

            if (msg instanceof LoginResponseMessage) {
                // 是登录结果
                LoginResponseMessage responseMessage = (LoginResponseMessage) msg;

                // 若是登录成功，记录 LOGIN 为 true
                if (responseMessage.isSuccess()) {
                    LOGIN.set(true);
                }

                // 不管是否登录成功都要唤醒输入线程
                WAIT_FOR_LOGIN.countDown();
            }
        }

        // 处理 active 事件，在连接建立后会触发调用
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.debug("连接建立成功，即将开启线程处理用户输入并进行通信...");
            // 开启一个线程接收用户输入并发起请求到 Server
            new Thread(() -> {

                String username = "";
                // 如果登录失败，进行循环登录直至成功
                while (!LOGIN.get()) {
                    System.out.println("请输入用户名：");
                    username = scanner.nextLine();
                    if (EXIT.get()) {
                        return;
                    }

                    System.out.println("请输入密码：");
                    String password = scanner.nextLine();
                    if (EXIT.get()) {
                        return;
                    }

                    // 构造登录消息并发送到服务端
                    LoginRequestMessage loginRequestMessage = new LoginRequestMessage(username, password);
                    ctx.writeAndFlush(loginRequestMessage);

                    // 阻塞指导登录获得响应
                    try {
                        WAIT_FOR_LOGIN.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while (true) {
                    System.out.println("==================================");
                    System.out.println("send [username] [content]");
                    System.out.println("gsend [group name] [content]");
                    System.out.println("gcreate [group name] [m1,m2,m3...]");
                    System.out.println("gmembers [group name]");
                    System.out.println("gjoin [group name]");
                    System.out.println("gquit [group name]");
                    System.out.println("quit");
                    System.out.println("==================================");

                    String command;
                    command = scanner.nextLine();
                    if (EXIT.get()) {
                        return;
                    }

                    String[] arr = command.split(" ");
                    switch (arr[0]) {
                        case "send":
                            ctx.writeAndFlush(new ChatRequestMessage(username, arr[1], arr[2]));
                            break;
                        case "gsend":
                            ctx.writeAndFlush(new GroupChatRequestMessage(username, arr[1], arr[2]));
                            break;
                        case "gcreate":
                            Set<String> members = new HashSet<>(Arrays.asList(arr[2].split(",")));
                            // 加入自己
                            members.add(username);
                            ctx.writeAndFlush(new GroupCreateRequestMessage(arr[1], members));
                            break;

                        case "gmembers":
                            ctx.writeAndFlush(new GroupMembersRequestMessage(arr[1]));
                            break;
                        case "gjoin":
                            ctx.writeAndFlush(new GroupJoinRequestMessage(username, arr[1]));
                            break;
                        case "gquit":
                            ctx.writeAndFlush(new GroupQuitRequestMessage(username, arr[1]));
                            break;
                        case "quit":
                            ctx.channel().close();
                            break;
                    }
                }
            }, "system-in").start();
        }

        // 在连接断开是触发
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            log.debug("连接已正常断开，请按任意键退出...");
            EXIT.set(true);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            log.debug("连接异常断开，请按任意键退出...");
            EXIT.set(true);
        }
    }
}
