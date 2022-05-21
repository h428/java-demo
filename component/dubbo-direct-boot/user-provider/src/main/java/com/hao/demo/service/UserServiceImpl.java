package com.hao.demo.service;

import com.hao.demo.api.UserService;
import com.hao.demo.entity.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getById(Long id) {
        return User.randomOne(id);
    }
}
