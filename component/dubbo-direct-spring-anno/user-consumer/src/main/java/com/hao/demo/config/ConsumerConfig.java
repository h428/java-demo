package com.hao.demo.config;

import com.hao.demo.api.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    /**
     * 对应 dubbo:application 标签的配置
     * @return dubbo:application 配置类
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("user-consumer");
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
     * 对应 xml 配置的 dubbo:reference 标签
     * @return dubbo:reference 对应的 Bean 配置对象
     */
    @Bean
    public UserService userService() {
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        // 对应 interface 属性配置
        referenceConfig.setInterface(UserService.class);
        // 对应 id 配置
        referenceConfig.setId("userService");
        // 对应 url 属性配置
        referenceConfig.setUrl("dubbo://localhost:20880/com.hao.demo.api.UserService");
        // 返回配置对象
        return referenceConfig.get();
    }


}
