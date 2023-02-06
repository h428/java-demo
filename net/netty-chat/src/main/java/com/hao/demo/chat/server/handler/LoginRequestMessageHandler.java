package com.hao.demo.chat.server.handler;

import com.hao.demo.chat.message.LoginRequestMessage;
import com.hao.demo.chat.message.LoginResponseMessage;
import com.hao.demo.chat.server.service.UserService;
import com.hao.demo.chat.server.service.UserServiceFactory;
import com.hao.demo.chat.server.session.SessionFactory;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String username = msg.getUsername();
        String password = msg.getPassword();
        UserService userService = UserServiceFactory.getUserService();
        boolean loginSuccess = userService.login(username, password);

        LoginResponseMessage responseMessage;
        if (loginSuccess) {
            // 登录成功后，绑定 session
            SessionFactory.getSession().bind(ctx.channel(), username);
            responseMessage = new LoginResponseMessage(true, "登录成功");
        } else {
            responseMessage = new LoginResponseMessage(false, "用户名或密码不正确");
        }

        ctx.writeAndFlush(responseMessage);
    }
}
