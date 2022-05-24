package com.hao.demo;

import com.hao.demo.api.AnotherService;
import com.hao.demo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserConsumerApplication {

    /**
     * 经过 debug 推断：服务调用时，由于 ZNode 中存储的是较为上层的数据结构描述（是一个 json），并不存储具体的调用方 url，
     * 因此，调用方在负载均衡确定调用实例后，会先调用 DubboMetadataService#getExportedURLs 根据接口拿到可用的 url
     * 然后再进一步做方法调用（调用的是默认实现类 IntrospectiveDubboMetadataService 的方法）
     * ZNode 节点的 payload.metadata 字段中存储了 DubboMetadataService 接口描述和端口，详细可查看 ZNode 节点
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
            if (userService == null) {
                System.out.println("userService is null...");
                return;
            }
            System.out.println("found provider: " + anotherService.getAppName());
            System.out.println(userService.randomOne());
        };
    }


}
