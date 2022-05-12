package com.hao.outer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;

/**
 * 方式一：使用 ConditionalOnClass 注解控制过滤，依赖当前 jar 的工程中若引入了 redis 则会自动装配该对象
 */
@Configuration
@ConditionalOnClass(RedisOperations.class)
public class FirstConfiguration {

}
