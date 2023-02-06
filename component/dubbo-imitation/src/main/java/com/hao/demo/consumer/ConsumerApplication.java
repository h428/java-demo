package com.hao.demo.consumer;

import com.hao.demo.framework.ProxyFactory;
import com.hao.demo.provider.api.AnotherService;
import com.hao.demo.provider.api.HelloService;

public class ConsumerApplication {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getBean(HelloService.class);
        System.out.println(helloService.sayHello("lyh"));

        AnotherService anotherService = ProxyFactory.getBean(AnotherService.class);
        System.out.println(anotherService.randomInt());

    }

}
