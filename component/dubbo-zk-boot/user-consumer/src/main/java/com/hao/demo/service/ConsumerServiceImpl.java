package com.hao.demo.service;

import com.hao.demo.api.ConsumerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

//@DubboService
public class ConsumerServiceImpl implements ConsumerService {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getConsumerAppName() {
        return appName;
    }
}
