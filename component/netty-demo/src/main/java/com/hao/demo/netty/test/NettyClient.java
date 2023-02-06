package com.hao.demo.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(new NioEventLoopGroup(1))
                .handler(new ChannelInitializer<NioServerSocketChannel>() {

                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {

                    }
                })
                .channel(NioSocketChannel.class);

        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(8080));
        Channel channel = channelFuture.sync().channel();


    }

}
