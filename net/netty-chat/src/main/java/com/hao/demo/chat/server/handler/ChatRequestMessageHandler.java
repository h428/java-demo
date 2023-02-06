package com.hao.demo.chat.server.handler;

import com.hao.demo.chat.message.ChatRequestMessage;
import com.hao.demo.chat.message.ChatResponseMessage;
import com.hao.demo.chat.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        // 取出消息发送目标用户名
        String to = msg.getTo();
        // 获取用户名对应的 channel
        Channel channel = SessionFactory.getSession().getChannel(to);

        // 若目标用户不在线，往发送方写入提示
        if (channel == null) {
            ctx.writeAndFlush(new ChatResponseMessage(false, "对方用户不存在或不在线"));
            return;
        }

        // 对方在线，则向对方的 channel 写入消息
        channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
    }
}
