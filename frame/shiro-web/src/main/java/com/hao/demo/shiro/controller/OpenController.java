package com.hao.demo.shiro.controller;

import com.hao.demo.shiro.pojo.bean.ResBean;
import com.hao.demo.shiro.pojo.dto.SysUserLoginDTO;
import com.hao.demo.shiro.pojo.message.BaseUserMessage;
import com.hao.demo.shiro.pojo.vo.LoginResultVo;
import com.hao.demo.shiro.pojo.vo.SysUserVO;
import com.hao.demo.shiro.service.SysUserService;
import com.hao.demo.shiro.util.RedisUtil;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open")
public class OpenController {

    @Autowired
    private SysUserService sysUserService;

    // private final BaseUserConverter baseUserConverter = BaseUserConverter.INSTANCE;


    @PostMapping("login")
    public ResBean<LoginResultVo> login(@RequestBody @Validated SysUserLoginDTO sysUserLoginDTO) {
        // todo captcha check
        SysUserVO sysUserVO = this.sysUserService.loginByUsername(sysUserLoginDTO);

        if (sysUserVO == null) {
            return ResBean.badRequest_400("用户名或密码错误，登录失败");
        }

        String token = UUID.randomUUID().toString();
        RedisUtil.put(token, sysUserVO);

        LoginResultVo loginResult = LoginResultVo.builder()
            .token(token)
            .build();

        return ResBean.ok_200(loginResult);
    }




}
