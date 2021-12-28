package com.hao.demo.service;

import com.hao.demo.entity.User;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = UserService.VERSION)
public class UserServiceImpl implements UserService {
    public User get(Long id) {
        return User.generate(id);
    }
}
