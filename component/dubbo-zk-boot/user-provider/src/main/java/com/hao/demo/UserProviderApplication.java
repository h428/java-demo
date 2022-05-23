package com.hao.demo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DubboComponentScan 会扫描所有 DubboService 注解，注册 Dubbo 服务为 ZNode 节点
 */
@SpringBootApplication
@DubboComponentScan
public class UserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }

}
