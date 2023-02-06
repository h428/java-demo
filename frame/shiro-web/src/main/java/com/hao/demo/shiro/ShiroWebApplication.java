package com.hao.demo.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hao.demo.shiro.mapper")
public class ShiroWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroWebApplication.class, args);
    }

}
