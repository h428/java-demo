package com.hao.demo.provider;

import com.hao.demo.framework.ProviderURL;
import com.hao.demo.protocol.http.HttpServer;
import com.hao.demo.provider.api.AnotherService;
import com.hao.demo.provider.api.HelloService;
import com.hao.demo.provider.impl.AnotherServiceImpl;
import com.hao.demo.provider.impl.HelloServiceImpl;
import com.hao.demo.provider.storage.LocalRegister;
import com.hao.demo.register.storage.RegisterCenter;

public class ProviderApplication {

    public static void main(String[] args) {

        // 1. 本地注册：存储当前暴露出去的服务名和对应的实现类 {服务名:实现类}
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        LocalRegister.register(AnotherService.class.getName(), AnotherServiceImpl.class);

        // 2. 远程注册（注册到注册中心）
        // 需要存储服务名以及对应的服务提供者机器地址 {服务名:List<URL>}
        ProviderURL providerUrl = new ProviderURL("localhost", 8080);
        RegisterCenter.register(HelloService.class.getName(), providerUrl);
        RegisterCenter.register(AnotherService.class.getName(), providerUrl);

        // 3. 启动 Tomcat（暴露服务）
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);

    }

}
