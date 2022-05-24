package com.hao.demo;

import com.hao.demo.api.AnotherService;
import java.net.InetAddress;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * spring Cloud 版本的 dubbo-zk 和 spring boot 版本的 dubbo-zk 基本一致，只有配置参数的前缀有所区别；
 * 因为 spring boot 版本是 dubbo 提供的启动器并注册到 zookeeper；
 * spring cloud 版本则是 spring cloud 提供的 zookeeper 连接启动器；
 * 以及 spring cloud alibaba 提供的 cloud 版本的 dubbo 启动器（对 spring cloud 做了整合&兼容）
 *
 * 和 spring boot 版本不同的是，spring cloud alibaba 版本的 dubbo 注册到 zookeeper 中时，是将整个应用作为
 * 一个 ZNode 节点注册到 zookeeper 中的，即当前 user-provider 不管有多少个 @DubboService 注解，其在 zookeeper
 * 中统一创建一个 /services/user-provider/4fbd2afe-5687-480a-baad-c49c6b505d82 节点，其中末级节点为临时节点，
 * 名称为随机的 uuid，值是一个包含了服务提供信息的 json 结构（但是没有具体服务的信息，应该是需要进一步请求）
 *
 * 而 spring boot 版本 dubbo 在 zookeeper 中的数据结构是按接口创建的，即 /dubbo/接口包全名/providers/服务连接 url
 * 详细可参考 dubbo-zk-boot 工程中 UserServiceImpl 中的描述；
 */
@SpringBootApplication
@DubboComponentScan
public class UserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(AnotherService anotherService) {
        return args -> {
            System.out.println(anotherService.getAppName() + " started success~~~~~~~~~~~~~~~~");
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("current ip is : " + localHost.getHostAddress());
        };
    }

}
