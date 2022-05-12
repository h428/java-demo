package com.hao.demo.config;

import com.hao.demo.condition.GpCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    public static class BeanClass {}

    @Bean
    @Conditional(GpCondition.class)
    public BeanClass beanClass() {
        return new BeanClass();
    }

}
