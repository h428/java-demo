package com.hao.demo.chat.server.handler;

import com.hao.demo.chat.message.GroupJoinResponseMessage;
import com.hao.demo.chat.message.GroupQuitRequestMessage;
import com.hao.demo.chat.server.session.Group;
import com.hao.demo.chat.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage msg) throws Exception {
        Group group = GroupSessionFactory.getGroupSession().removeMember(msg.getGroupName(), msg.getUsername());

        if (group == null) {
            ctx.writeAndFlush(new GroupJoinResponseMessage(true, msg.getGroupName() + "不存在"));
            return;
        }
        ctx.writeAndFlush(new GroupJoinResponseMessage(true, "已退出" + msg.getGroupName()));
    }
}
