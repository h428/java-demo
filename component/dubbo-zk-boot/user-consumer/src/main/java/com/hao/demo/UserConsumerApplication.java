package com.hao.demo;

import com.hao.demo.api.AnotherService;
import com.hao.demo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@DubboComponentScan
public class UserConsumerApplication {

    /**
     * 采用注册中心后，则无需配置 url，会自动从注册中心获取；
     * dubbo 会根据接口的包全名，到 zookeeper 对应的 ZNode 节点 /dubbo/com.hao.demo.api.UserService/providers
     * 下扫描节点的孩子（临时类型的 ZNode）并注册 Watcher 监听，孩子数量即为服务提供者数量，并基于服务提供者自动做负载均衡和服务调用；
     * 由于注册了监听，且孩子为临时类型的 ZNode，当服务提供者宕机时，ZNode 会被删除，
     * 而消费者的 Watcher 回调可以感知到服务的下线并剔除无效服务；
     * 同时，@DubboReference 注解还会将所在的应用注册到对应接口的 consumer 节点下，即作为服务的消费者；
     * 例如该引用对应的 ZNode 节点路径为： /dubbo/com.hao.demo.api.UserService/consumers/consumer://192.168.61.1/com.hao.demo.api.UserService?application=user-consumer&category=consumers&check=false&dubbo=2.0.2&init=false&interface=com.hao.demo.api.UserService&metadata-type=remote&methods=getById,randomOne&pid=15148&qos.enable=false&release=2.7.8&side=consumer&sticky=false&timestamp=1653342396005
     * 记录 consumer 节点的好处是：可以 zookeeper 监控平台中看到某一服务正在被哪些服务调用
     */
    @DubboReference
    private UserService userService;

    @DubboReference
    private AnotherService anotherService;

    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            System.out.println(userService.randomOne());
        };
    }


}
