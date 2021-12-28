package com.hao.demo.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SpringBootWebApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner：启动成功");
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> log.info("commandLineRunner：启动成功");
    }

}
