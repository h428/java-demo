package com.hao.demo.chat.rpc.server.handler;

import com.hao.demo.chat.rpc.message.RpcResponseMessage;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Sharable
public class RpcResponseMessageHandler extends SimpleChannelInboundHandler<RpcResponseMessage> {

    public static final Map<Integer, Promise<Object>> PROMISES = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcResponseMessage message) throws Exception {
        System.out.println("远程调用结果类：" + message);
        // 拿到空的 promise
        Promise<Object> promise = PROMISES.remove(message.getSeqId());
        if (promise != null) {
            Object returnValue = message.getReturnValue();
            Exception exceptionValue = message.getExceptionValue();
            if (exceptionValue != null) {
                promise.setFailure(exceptionValue);
            } else {
                promise.setSuccess(returnValue);
            }
        }
    }
}
