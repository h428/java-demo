package com.hao.demo;

import com.hao.demo.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hao.demo.mapper")
public class SpringBootH2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootH2Application.class, args);
    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.userMapper.selectList(null));
    }
}
