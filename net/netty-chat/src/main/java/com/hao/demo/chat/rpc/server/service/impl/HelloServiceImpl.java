package com.hao.demo.chat.rpc.server.service.impl;

import com.hao.demo.chat.rpc.server.service.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello, " + name;
    }

}
