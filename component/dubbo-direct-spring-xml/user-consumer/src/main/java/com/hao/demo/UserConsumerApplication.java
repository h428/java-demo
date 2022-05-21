package com.hao.demo;

import com.hao.demo.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserConsumerApplication {

    public static void main(String[] args) {
        // 从 xml 文件创建上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:user-consumer.xml");
        // 从上下获取 Bean，这个 Bean 的声明来自于 xml 的 dubbo:reference 标签
        UserService userService = context.getBean(UserService.class);
        // 调用 Bean 方法
        System.out.println(userService.randomOne());
    }

}
