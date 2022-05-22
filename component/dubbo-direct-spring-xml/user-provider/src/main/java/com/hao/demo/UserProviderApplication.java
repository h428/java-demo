package com.hao.demo;

import org.apache.dubbo.container.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserProviderApplication {

    public static void main(String[] args) {
        // 从 xml 文件创建上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:user-provider.xml");
        // 启动上下文
        context.start();
        // 阻塞 Main 线程
        Main.main(args);
    }

}
