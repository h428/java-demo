package com.hao.demo.spi;

import static org.junit.Assert.*;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.junit.Test;

public class DriverTest {

    /**
     * 测试 Dubbo SPI 机制
     */
    @Test
    public void connect() {
        // 先根据接口获取扩展点加载器 ExtensionLoader，该步骤首先确定到对应的 SPI 文件级别
        ExtensionLoader<Driver> extensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
        // Dubbo SPI 是以键值对的形式出现，因此还要额外根据 key 获取到指定的实现类，该步骤精确到 SPI 文件的某一行
        Driver mysqlDriver = extensionLoader.getExtension("mysqlDriver");
        // 验证正确获取到了指定的类的对象
        System.out.println(mysqlDriver.connect());
    }

    /**
     * 测试 wrapper 部分代码
     */
    @Test
    public void testWrapper() {
        // 使用 META-INF/dubbo/internal 下的 org.apache.dubbo.rpc.Protocol 接口验证 wrapper
        // 先根据接口获取扩展点加载器 ExtensionLoader，该步骤首先确定到对应的 SPI 文件级别
        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        // Dubbo SPI 是以键值对的形式出现，因此还要额外根据 key 获取到指定的实现类，该步骤精确到 SPI 文件的某一行
        Protocol protocol = extensionLoader.getExtension("mock");
        // 验证正确获取到了指定的类的对象
        System.out.println(protocol);
    }

    /**
     * 测试 wrapper 部分代码
     */
    @Test
    public void testAdaptive() {
        // 使用 META-INF/dubbo/internal 下的 org.apache.dubbo.rpc.Protocol 接口验证 wrapper
        // 先根据接口获取扩展点加载器 ExtensionLoader，该步骤首先确定到对应的 SPI 文件级别
        ExtensionLoader<Protocol> extensionLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
        // Dubbo SPI 是以键值对的形式出现，因此还要额外根据 key 获取到指定的实现类，该步骤精确到 SPI 文件的某一行
        Protocol protocol = extensionLoader.getAdaptiveExtension();
        // 验证正确获取到了指定的类的对象
        System.out.println(protocol);
    }
}