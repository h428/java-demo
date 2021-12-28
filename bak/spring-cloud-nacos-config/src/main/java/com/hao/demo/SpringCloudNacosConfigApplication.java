package com.hao.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringCloudNacosConfigApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication
            .run(SpringCloudNacosConfigApplication.class, args);
        while (true) {
            String info = context.getEnvironment().getProperty("info");
            System.out.println(info);
            Thread.sleep(2000);
        }
    }

}
