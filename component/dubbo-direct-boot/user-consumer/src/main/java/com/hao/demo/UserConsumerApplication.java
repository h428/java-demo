package com.hao.demo;

import com.hao.demo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserConsumerApplication {

    @DubboReference
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class, args);
    }

    public ApplicationRunner runner() {
        return args -> {
            System.out.println(userService.getById(11L));
        };
    }


}
