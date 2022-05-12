package com.hao.demo.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Condition 接口为函数式接口，提供 matches 方法，用于告知 @Conditional 注解是否需要装配指定的配置类，以达到条件装配的效果；
 */
public class GpCondition implements Condition {

    /**
     * 如果当前系统是 Windows 则返回 true，否则返回 false，从而达到条件装配的效果
     * @param context
     * @param metadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        String os = context.getEnvironment().getProperty("os.name");

        // 如果 os.name 环境属性中包含 Windows 则表示位于 Windows 环境
        if (os != null && os.contains("Windows")) {
            return true;
        }

        return false;
    }
}
