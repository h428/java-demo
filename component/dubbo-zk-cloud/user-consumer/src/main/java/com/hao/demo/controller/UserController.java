package com.hao.demo.controller;

import com.hao.demo.api.UserService;
import com.hao.demo.entity.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    /**
     * mock 为 dubbo 默认提供的服务降级功能，当未找到服务提供者或者服务提供者挂掉后采用的兜底手段
     */
    @DubboReference(mock = "com.hao.demo.mock.MockUserService", cluster = "failfast")
    private UserService userService;

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userService.getById(id);
    }

}
