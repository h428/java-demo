package com.hao.demo.service;

import com.hao.demo.entity.User;
import org.apache.dubbo.config.annotation.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User get(Long id) {
        User user = User.builder().id(id).username("user" + id).password("pass" + id).build();
        return user;
    }
}
