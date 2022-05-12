package com.hao.demo.assemble;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

/**
 * `@Target` 设置当前注解用于哪些地方： ElementType.TYPE 表示用于类、接口或枚举上
 * `@Retention` 设置当前注解信息保存时长：RetentionPolicy.RUNTIME 表示在运行时也保留该注解，可以通过反射机制读取注解的信息
 * `@Documented` 表示该注解将保存在 javadoc 中
 * `@Inherited` 表示该注解允许被继承，即父类上定义该注解后，子类也自动具备该注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(GroupConConfigurationImportSelector.class)
//@Import({FirstConfiguration.class, SecondConfiguration.class}) // 在本例中和上面一行等价，但不够灵活
public @interface EnableGroupConfiguration {
    // 和 @Import(***Configuration.class) 相比，@Import(Selector.class) 的好处在于装配灵活性
    // 其可以根据配置文件实现条件装配、灵活批量装配等，而 @Import(***Configuration.class) 只能编译期写死
}
