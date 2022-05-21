package com.hao.demo;

import com.hao.demo.api.UserService;
import com.hao.demo.config.ConsumerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserConsumerApplication {

    public static void main(String[] args) {
        // 从注解类创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfig.class);
        // 从上下获取 Bean，这个 Bean 的声明来自于 xml 的 dubbo:reference 标签
        UserService userService = (UserService) context.getBean("userService");
        // 调用 Bean 方法
        System.out.println(userService.randomOne());
    }

}
