package com.hao.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.demo.builder.ObjectMapperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
public class RedisCacheApplication {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisCacheApplication.class);

    @Bean
    @Order(10)
    public ApplicationRunner setter() {
        return args -> {
            for (int i = 0; i < 10; i++) {
                int num = (int)(Math.random() * 100);
                redisTemplate.opsForValue().set("name:" + num, num);
                log.info("put name{} with value {}", num, num);
            }
        };
    }

    /**
     * 配置 RedisTemplate，主要是配置序列化方式；
     * 特别注意值采用 json 序列化方式后，对于值类型为 long 类型时有个大坑，存储为 long，反序列化时会被看做 int
     *
     * @param factory redis 配置工厂
     * @param <V>     值类型泛型
     * @return redis 配置模板
     */
    @Bean
    public <V> RedisTemplate<String, V> redisTemplate(RedisConnectionFactory factory) {

        // 参照 StringRedisTemplate 的构造器设置配置自定义的 RedisTemplate

        RedisTemplate<String, V> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        // 使用 Jackson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值（默认使用 JDK 的序列化方式）
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 全部采用 ObjectMapperBuilder 默认值的 builder
        ObjectMapper objectMapper = ObjectMapperBuilder.builder().saveType(true).build();
        serializer.setObjectMapper(objectMapper);

        // key 使用 StringRedisSerializer 来序列化和反序列化
        template.setKeySerializer(new StringRedisSerializer());

        // 值采用 json 序列化
        template.setValueSerializer(serializer);

        // 设置 hash key 和 value 序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();

        return template;
    }


    public static void main(String[] args) {
        SpringApplication.run(RedisCacheApplication.class, args);
    }


}
