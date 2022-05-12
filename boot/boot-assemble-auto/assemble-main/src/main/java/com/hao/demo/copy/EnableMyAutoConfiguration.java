package com.hao.demo.copy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

/**
 * 模拟 @EnableAutoConfiguration 注解，导入 MyAutoConfigurationImportSelector 配置类；
 * MyAutoConfigurationImportSelector 拷贝自 Spring Boot 的 AutoConfigurationImportSelector
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(MyAutoConfigurationImportSelector.class)
public @interface EnableMyAutoConfiguration {

}
