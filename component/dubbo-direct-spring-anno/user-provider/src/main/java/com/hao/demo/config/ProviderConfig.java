package com.hao.demo.config;

import com.hao.demo.api.UserService;
import com.hao.demo.service.UserServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置类，采用注解驱动方式的配置，一定加加上 @EnableDubbo 注解，否则 dubbo 不会生效
 */
@Configuration
@EnableDubbo
public class ProviderConfig {

    /**
     * 对应 dubbo:application 标签的配置
     * @return dubbo:application 配置类
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("user-provider");
        return applicationConfig;
    }

    /**
     * 对应 dubbo:registry 标签的配置
     * @return dubbo:registry 配置类
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(RegistryConfig.NO_AVAILABLE);
        return registryConfig;
    }

    /**
     * 对应 dubbo:protocol 标签的配置
     * @return dubbo:protocol 配置类
     */
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }

    /**
     * 对应 xml 配置的 bean 标签
     * @return 创建 bean，id 默认为 方法名 userService
     */
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    /**
     * 对应 xml 配置的 dubbo:service 标签
     * @return dubbo:service 对应的 Bean 配置对象
     */
    @Bean
    public ServiceConfig<UserService> userServiceServiceConfig(UserService userService) {
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        // 对应 interface 属性配置
        serviceConfig.setInterface(UserService.class);
        // 对应 ref 属性配置，可以直接调用方法，也可以利用 Spring 的自动注入
        serviceConfig.setRef(userService);
        // 返回配置对象
        return serviceConfig;
    }


}
