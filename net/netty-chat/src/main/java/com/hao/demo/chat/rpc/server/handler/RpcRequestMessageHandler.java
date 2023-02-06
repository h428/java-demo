package com.hao.demo.chat.rpc.server.handler;

import com.hao.demo.chat.rpc.message.RpcRequestMessage;
import com.hao.demo.chat.rpc.message.RpcResponseMessage;
import com.hao.demo.chat.rpc.server.service.BeanFactory;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Sharable
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage msg) throws Exception {
        RpcResponseMessage response = new RpcResponseMessage();
        int seqId = msg.getSeqId();
        response.setSeqId(seqId);

        try {
            Class<?> interfaceClass = Class.forName(msg.getInterfaceName());
            Object service = BeanFactory.getService(interfaceClass);
            Method method = service.getClass().getMethod(msg.getMethodName(), msg.getParameterTypes());
            Object returnValue = method.invoke(service, msg.getParameterValues());
            response.setReturnValue(returnValue);

        } catch (Exception e) {
            e.printStackTrace();
            String message = e.getCause().getMessage();
            response.setExceptionValue(new Exception("远程调用出错:" + message));
        }

        // 像发送端发送远程调用响应
        ctx.writeAndFlush(response);
    }

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RpcRequestMessage message = new RpcRequestMessage(
                1,
                "com.hao.demo.chat.rpc.server.service.HelloService",
                "hello",
                String.class,
                new Class[]{String.class},
                new Object[]{"张三22"}
        );
        Object helloService = BeanFactory.getService(Class.forName(message.getInterfaceName()));
        Method method = helloService.getClass().getMethod(message.getMethodName(), message.getParameterTypes());
        Object invoke = method.invoke(helloService, message.getParameterValues());
        System.out.println(invoke);
    }
}
