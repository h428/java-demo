package com.hao.demo.netty.test;

import com.hao.demo.netty.util.DebugUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        new ServerBootstrap()
                // 两个 worker 线程
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            // 处理读事件
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                // 原始输入是一个 ByteBuf，
                                ByteBuf buffer = (ByteBuf) msg;
                                process(buffer, ctx);
                            }
                        });
                    }
                })
                .bind(8080);
    }

    public static void process(ByteBuf input, ChannelHandlerContext ctx) {
        // 打印输入
        String data = input.toString(Charset.defaultCharset()).trim();
        String address = ctx.channel().remoteAddress().toString();
        String out = String.format("received message from %s at %s : %s\n",
                address, DebugUtil.time(), data);
        System.out.print(out);

        // 写回 response
        // 建议使用 ctx.alloc() 创建 ByteBuf
        ByteBuf response = ctx.alloc().buffer();
        response.writeBytes(Charset.defaultCharset().encode(data));
        ctx.writeAndFlush(response);
    }
}
