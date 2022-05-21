package com.hao.demo;

import com.hao.demo.config.ConditionConfig.AnotherBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AssembleConditionalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(
            AssembleConditionalApplication.class, args);
        AnotherBean anotherBean = context.getBean(AnotherBean.class);
        System.out.println(anotherBean);
    }

}
