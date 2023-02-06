package com.hao.demo.chat.server;

import com.hao.demo.chat.server.handler.ChatRequestMessageHandler;
import com.hao.demo.chat.server.handler.GroupChatRequestMessageHandler;
import com.hao.demo.chat.server.handler.GroupCreateRequestMessageHandler;
import com.hao.demo.chat.server.handler.GroupJoinRequestMessageHandler;
import com.hao.demo.chat.server.handler.GroupMembersRequestMessageHandler;
import com.hao.demo.chat.server.handler.GroupQuitRequestMessageHandler;
import com.hao.demo.chat.server.handler.LoginRequestMessageHandler;
import com.hao.demo.chat.server.handler.QuitHandler;
import com.hao.demo.chat.server.protocol.MessageCodecSharableHandler;
import com.hao.demo.chat.server.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatServer {

    // 用于日志记录的 handler
    private static final LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);

    // 是一个双向复用的处理器 ChannelDuplexHandler，
    // 入站时扮演 InboundHandler 角色，用于从 ByteBuf 中解析数据，使用 decode 解析为 Message
    // 出站时扮演 OutboundHandler 角色，用于将 Message 编码为 ByteBuf
    private static final MessageCodecSharableHandler MESSAGE_CODEC_HANDLER = new MessageCodecSharableHandler();

    // 各个消息处理器
    private static final LoginRequestMessageHandler LOGIN_HANDLER = new LoginRequestMessageHandler();
    private static final ChatRequestMessageHandler CHAT_HANDLER = new ChatRequestMessageHandler();
    private static final GroupCreateRequestMessageHandler GROUP_CREATE_HANDLER = new GroupCreateRequestMessageHandler();
    private static final GroupChatRequestMessageHandler GROUP_CHAT_HANDLER = new GroupChatRequestMessageHandler();
    private static final GroupJoinRequestMessageHandler GROUP_JOIN_HANDLER = new GroupJoinRequestMessageHandler();
    private static final GroupMembersRequestMessageHandler GROUP_MEMBERS_HANDLER = new GroupMembersRequestMessageHandler();
    private static final GroupQuitRequestMessageHandler GROUP_QUIT_HANDLER = new GroupQuitRequestMessageHandler();
    private static final QuitHandler QUIT_HANDLER = new QuitHandler();


    public static void main(String[] args) {

        // boss 事件循环
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // work 事件循环
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            // 服务端构造器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置服务器通道类型
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 设置时间循环
            serverBootstrap.group(boss, worker);
            // 设置子通道初始化器
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    // 基于 pipeline 添加各种类型的 handler
                    sc.pipeline()
                            // 先使用 ProtocolFrameDecoder 处理半包粘包问题，分离出正确的，单个 Message 对应的 ByteBuf
                            .addLast(new ProtocolFrameDecoder())
                            // 打印 ByteBuf
                            .addLast(LOGGING_HANDLER)
                            // 双向处理器，用于 ByteBuffer 和 Message 间的编码解码
                            .addLast(MESSAGE_CODEC_HANDLER)
                    ;

                    sc.pipeline()
                            .addLast(LOGIN_HANDLER)
                            .addLast(CHAT_HANDLER)
                            .addLast(GROUP_CREATE_HANDLER)
                            .addLast(GROUP_CHAT_HANDLER)
                            .addLast(GROUP_JOIN_HANDLER)
                            .addLast(GROUP_QUIT_HANDLER)
                            .addLast(GROUP_MEMBERS_HANDLER)
                            .addLast(QUIT_HANDLER)
                    ;
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8080);
            Channel channel = channelFuture.sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            log.error("server error ", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
