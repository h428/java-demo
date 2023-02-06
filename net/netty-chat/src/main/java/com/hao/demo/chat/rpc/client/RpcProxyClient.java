package com.hao.demo.chat.rpc.client;

import com.hao.demo.chat.rpc.message.RpcRequestMessage;
import com.hao.demo.chat.rpc.server.handler.RpcResponseMessageHandler;
import com.hao.demo.chat.rpc.server.service.HelloService;
import com.hao.demo.chat.server.protocol.MessageCodecSharableHandler;
import com.hao.demo.chat.server.protocol.ProtocolFrameDecoder;
import com.hao.demo.chat.util.SequenceIdGenerator;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import java.lang.reflect.Proxy;

public class RpcProxyClient {

    public static void main(String[] args) {
        HelloService service = getProxyService(HelloService.class);
        System.out.println(service.hello("zhangsan"));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxyService(Class<T> serviceClass) {
        ClassLoader classLoader = serviceClass.getClassLoader();
        Class<?>[] interfaces = {serviceClass};

        return (T) Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
            int seqId = SequenceIdGenerator.nextId();
            RpcRequestMessage rpcRequestMessage = new RpcRequestMessage(
                    seqId,
                    serviceClass.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args
            );
            getChannel().writeAndFlush(rpcRequestMessage);

            DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
            RpcResponseMessageHandler.PROMISES.put(seqId, promise);
            promise.await();
            if (promise.isSuccess()) {
                // 调用正常
                return promise.getNow();
            } else {
                // 调用失败
                throw new RuntimeException(promise.cause());
            }
        });
    }

    private static Channel getChannel() {
        if (channel != null) {
            return channel;
        }
        synchronized (LOCK) { //  t2
            if (channel != null) { // t1
                return channel;
            }
            initChannel();
            return channel;
        }
    }

    private static final Object LOCK = new Object();

    private static volatile Channel channel;

    private static void initChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharableHandler MESSAGE_CODEC = new MessageCodecSharableHandler();
        RpcResponseMessageHandler RPC_HANDLER = new RpcResponseMessageHandler();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    // ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    ch.pipeline().addLast(RPC_HANDLER);
                }
            });
            channel = bootstrap.connect("localhost", 8080).sync().channel();
            channel.closeFuture().addListener(promise -> {
                group.shutdownGracefully();
            });
        } catch (Exception e) {
            System.out.println("client error" + e);
        }
    }

}
