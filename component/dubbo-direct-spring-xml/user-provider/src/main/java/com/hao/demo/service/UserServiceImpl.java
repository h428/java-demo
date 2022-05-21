package com.hao.demo.service;

import com.hao.demo.api.UserService;
import com.hao.demo.entity.User;


public class UserServiceImpl implements UserService {

    @Override
    public User getById(Long id) {
        return User.randomOne(id);
    }

    @Override
    public User randomOne() {
        return User.randomOne();
    }
}
