package com.hao.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hao.demo.mapper")
public class MybatisPlusH2Application {

}
