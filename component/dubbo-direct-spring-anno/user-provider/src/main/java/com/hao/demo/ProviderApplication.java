package com.hao.demo;

import com.hao.demo.api.UserService;
import com.hao.demo.config.ProviderConfig;
import org.apache.dubbo.container.Main;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProviderApplication {

    public static void main(String[] args) {
        // 从注解类文件创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfig.class);
        // 启动上下文
        context.start();
        // 阻塞 Main 线程
        Main.main(args);
    }

}
