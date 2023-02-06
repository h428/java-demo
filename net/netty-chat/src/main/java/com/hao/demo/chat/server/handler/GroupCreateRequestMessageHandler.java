package com.hao.demo.chat.server.handler;

import com.hao.demo.chat.message.GroupCreateRequestMessage;
import com.hao.demo.chat.message.GroupCreateResponseMessage;
import com.hao.demo.chat.server.session.Group;
import com.hao.demo.chat.server.session.GroupSession;
import com.hao.demo.chat.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.List;
import java.util.Set;

/**
 * 处理创建群组消息
 */
@Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {
        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();

        Group group = groupSession.createGroup(groupName, members);

        // 名称已被占用
        if (group != null) {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已存在"));
            return;
        }

        ctx.writeAndFlush(new GroupCreateResponseMessage(true, groupName + "创建成功"));
        // 往成员发送拉群消息，已经做了空过滤
        List<Channel> membersChannel = groupSession.getMembersChannel(groupName);
        membersChannel.forEach(channel ->
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已被拉入群聊" + groupName)));
    }
}
