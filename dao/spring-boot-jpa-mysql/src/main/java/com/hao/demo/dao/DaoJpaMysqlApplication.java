package com.hao.demo.dao;

import com.hao.demo.dao.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DaoJpaMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoJpaMysqlApplication.class, args);
    }

    @Autowired
    private UserDao userDao;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            log.info("准备查询所有用户信息...");
            System.out.println(userDao.findAll());
            log.info("所有用户信息查询完毕");
        };
    }

}
