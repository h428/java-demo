package com.hao.demo.service;

import com.hao.demo.entity.User;
import org.apache.dubbo.config.annotation.Service;

@Service
public class UserServiceImpl implements UserService {

    public User get(Long id) {
        return User.generate(id);
    }
}
