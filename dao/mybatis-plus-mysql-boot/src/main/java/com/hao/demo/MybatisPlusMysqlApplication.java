package com.hao.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hao.demo.generate.mapper")
public class MybatisPlusMysqlApplication {

}
