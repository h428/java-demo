package com.hao.demo.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * 1. Dubbo SPI 扩展点，首先需要对接口标记 @SPI 注解
 * 2. 然后创建需要载入的实现类，并在 resources/META-INF/dubbo 下创建以接口的包全名为名称的文件，内容即为要加载的实现类
 * 3. SPI 文件格式和标准的 JAVA SPI 文件有所不同，其为键值对的格式，每一对，格式为：xxx=实现类包全名（注意必须是文件名对应接口的实现类）
 * 4. 在代码中使用 Dubbo 提供的 ExtensionLoader 工具类加载扩展点相关内容
 */
@SPI("test")
public interface Driver {
    String connect();
}
