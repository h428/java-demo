package com.hao.demo.config;

import com.hao.demo.condition.GpCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
// 容器中存在 HaoBean 的实例才会装配当前 Bean
@ConditionalOnBean(HaoBean.class)
// 如果没有配置 hao.bean.enable 属性或者配置了属性值为 randomValue 都会装配，若配置为其他值则不装配
@ConditionalOnProperty(value = "hao.bean.enable", havingValue = "randomValue", matchIfMissing = true)
// classpath 存在 hao.properties 文件才会装配当前 Bean
@ConditionalOnResource(resources = "hao.properties")
public class ConditionConfig {

    public static class AnotherBean {}

    @Bean
    @Conditional(GpCondition.class)
    public AnotherBean anotherBean() {
        return new AnotherBean();
    }

}
