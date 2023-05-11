package com.hao.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hao.demo.mapper.BaseUserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hao.demo.mapper")
public class MybatisPlusPostgresBootApplication implements ApplicationRunner {

    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(this.baseUserMapper.selectList(Wrappers.lambdaQuery()));
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusPostgresBootApplication.class, args);
    }

}
