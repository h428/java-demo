package com.hao.demo.assemble;

import com.hao.outer.FirstConfiguration;
import com.hao.outer.SecondConfiguration;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 创建一个 ImportSelector 实现类用于批量装配
 */
public class GroupConConfigurationImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // Spring Boot 自动装配的核心：Spring 不是像下行代码一样只是自动返回固定的多个类，而是通过两个配置文件完成自动装配
        // 1. 其会预先读取 META-INF/spring-autoconfigure-metadata.properties 作为过滤条件（默认 684 个）
        // 该读取是通过 AutoConfigurationImportSelector.AutoConfigurationGroup 内部类的 process() 调用
        // getAutoConfigurationMetadata() 读取 spring-autoconfigure-metadata.properties 文件，
        // 创建并返回 AutoConfigurationMetadata
        // 2. 之后读取 META-INF/spring.factories 下的候选装配类（117 个），记作 candidates
        // 3. 读取 SpringBootApplication 注解上的 exclude 和 excludeName 过滤 candidates
        // 4. 之后，分别循环 OnClassCondition, OnWebApplicationCondition 和 OnBeanCondition 过滤条件，
        // 计算 ConditionOutcome[] outcomes 数组（数组长度和 candidates 一致），其中 outcome[i] 为 null 时
        // 表示对于当前候选类，在 spring-autoconfigure-metadata.properties 没有过滤条件，或者过滤条件被满足，
        // 因此其 outcome[i] 为 null 时等价于 match=true，否则其指向一条 ConditionOutcome 记录，其 match 为 false
        // 目前 outcome[i] 只有为 null 和 match 字段为 false 的情况，没有 match 为 true 的情况（起码 OnClassCondition 的实现下未找到）
        // 之后根据 outcome 数组计算 boolean[] match 数组，outcome[i] 为 null，则表示 true，否则 false
        // 之后根据 match 数组计算 boolean[] skip，其值和 match 数组相反，即 skip[i] = match[i]
        // 其中 OnClassCondition 为大多数 Spring 自动装配过滤条件的核心，由于需要过滤的类比较大，Spring 额外开了一个线程，
        // main 和 thread-1 俩线程各自处理并返回一半的 outcome 数组，并由 main 线程 join 进来，得到完整的 outcome 数组，
        // 之后根据 outcome 数组进一步计算 match[] 和 skip[] 数组
        // 5. 最后使用 skip 数组过滤 candidates，将未被过滤的类记录到 List 并返回

        // 基于该原理，我们可以在自己工程的 META-INF 目录编写自己的 spring.factories
        // 和 spring-autoconfigure-metadata.properties，Spring 一样会自动读取相关文件并进行自动装配，详情可
        // 样例请参考 assemble-outer 工程下的对应配置
        //return new String[]{FirstConfiguration.class.getName(), SecondConfiguration.class.getName()};
        return new String[]{};
    }

}
