package com.hao.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hao.demo.dal.mapper")
public class SpringBootSeataOrderProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSeataOrderProviderApplication.class, args);
    }

}
