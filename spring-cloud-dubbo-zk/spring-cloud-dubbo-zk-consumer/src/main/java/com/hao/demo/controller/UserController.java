package com.hao.demo.controller;

import com.hao.demo.entity.User;
import com.hao.demo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return this.userService.get(id);
    }


}
