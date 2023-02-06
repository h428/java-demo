package com.hao.demo.chat.server.handler;

import com.hao.demo.chat.message.GroupJoinRequestMessage;
import com.hao.demo.chat.message.GroupJoinResponseMessage;
import com.hao.demo.chat.server.session.Group;
import com.hao.demo.chat.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().joinMember(msg.getGroupName(), msg.getUsername());

        if (group == null) {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "群聊不存在"));
            return;
        }

        ctx.writeAndFlush(new GroupJoinResponseMessage(true, "成功加入 " + msg.getGroupName()));
    }
}
