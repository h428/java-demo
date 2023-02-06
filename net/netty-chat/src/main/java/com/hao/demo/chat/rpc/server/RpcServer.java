package com.hao.demo.chat.rpc.server;

import com.hao.demo.chat.rpc.server.handler.RpcRequestMessageHandler;
import com.hao.demo.chat.server.protocol.MessageCodecSharableHandler;
import com.hao.demo.chat.server.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

// 模拟 RPC 服务器
public class RpcServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        MessageCodecSharableHandler MESSAGE_CODEC_HANDLER = new MessageCodecSharableHandler();
        RpcRequestMessageHandler RPC_HANDLER = new RpcRequestMessageHandler();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    ch.pipeline().addLast(MESSAGE_CODEC_HANDLER);
                    ch.pipeline().addLast(RPC_HANDLER);
                }
            });
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            System.out.println("server error" + e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
