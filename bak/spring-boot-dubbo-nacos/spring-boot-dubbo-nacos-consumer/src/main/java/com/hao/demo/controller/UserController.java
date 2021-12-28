package com.hao.demo.controller;

import com.hao.demo.entity.User;
import com.hao.demo.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @DubboReference(version = UserService.VERSION)
    private UserService userService;

    @GetMapping("{id}")
    public User user(@PathVariable Long id) {
        return userService.get(id);
    }
}
