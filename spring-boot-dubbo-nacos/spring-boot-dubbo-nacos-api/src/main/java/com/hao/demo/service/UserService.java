package com.hao.demo.service;

import com.hao.demo.entity.User;

public interface UserService {
    User get(Long id);

    String VERSION = "1.0.0";

}
