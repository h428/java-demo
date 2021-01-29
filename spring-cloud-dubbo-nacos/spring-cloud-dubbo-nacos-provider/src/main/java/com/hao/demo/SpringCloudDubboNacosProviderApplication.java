package com.hao.demo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@DubboComponentScan
@SpringBootApplication
public class SpringCloudDubboNacosProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDubboNacosProviderApplication.class, args);
    }
}
