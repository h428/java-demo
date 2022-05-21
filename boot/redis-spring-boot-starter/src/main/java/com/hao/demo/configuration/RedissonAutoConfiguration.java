package com.hao.demo.configuration;

import com.hao.demo.properties.RedissonProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * 基于 Spring Boot 自动装配的原理（详细分析参考 boot-assemble-auto 工程），我们可以按照下述步骤编写自己的 starter：
 * 1. 编写自己 starter 的 Configuration 类，该配置类一般有一个对应的 Properties 类
 * 2. 在 META-INF/spring.factories 下，将当前配置类添加到 @EnableAutoConfiguration 候选装配集合中
 * 3. 配置当前类的条件装配（可选但最好做），该步骤有两种方式：
 *  方式一：直接使用 Spring Boot 提供的 @ConditionalOnClass 注解（简单，推荐），处于测试需要，本次未选择该方案
 *  方式二：模仿 Spring Boot Autoconfigure 的原理，编写 spring-boot-autoconfigure-metadata.properties 文件
 *      在配置文件中编写，要求 classpath 存在 com.hao.demo.entity.Switcher 才加载当前配置
 */
@Configuration
//@ConditionalOnBean(Redisson.class)
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties) {
        Config config = new Config();
        String prefix = "redis://";
        if (redissonProperties.isSsl()) {
            prefix = "rediss://";
        }

        String address = prefix + redissonProperties.getHost() + ":" + redissonProperties.getPort();

        SingleServerConfig singleServerConfig = config.useSingleServer()
            .setAddress(address)
            .setConnectTimeout(redissonProperties.getTimeout());

        if (!StringUtils.isEmpty(redissonProperties.getPassword())) {
            singleServerConfig.setPassword(redissonProperties.getPassword());
        }

        return Redisson.create(config);
    }

}
