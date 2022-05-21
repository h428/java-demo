package com.hao.demo;

import com.hao.demo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserConsumerApplication {

    @DubboReference(url = "dubbo://localhost:20880/com.hao.demo.api.UserService")
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            System.out.println(userService.randomOne());
        };
    }


}
