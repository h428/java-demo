package com.hao.demo;

import com.hao.demo.config.ConditionConfig.BeanClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AssembleConditionalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(
            AssembleConditionalApplication.class, args);
        BeanClass beanClass = context.getBean(BeanClass.class);
        System.out.println(beanClass);
    }

}
