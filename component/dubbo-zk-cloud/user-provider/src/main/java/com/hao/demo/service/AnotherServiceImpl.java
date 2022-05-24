package com.hao.demo.service;

import com.hao.demo.api.AnotherService;
import com.hao.demo.api.UserService;
import com.hao.demo.entity.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@DubboService
@Component
public class AnotherServiceImpl implements AnotherService {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getAppName() {
        return appName;
    }
}
