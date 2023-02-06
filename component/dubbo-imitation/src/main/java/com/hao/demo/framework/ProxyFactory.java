package com.hao.demo.framework;

import com.hao.demo.protocol.http.HttpClient;
import com.hao.demo.register.storage.RegisterCenter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 模拟服务消费者端的 Spring Context，获取接口对应的代理类，进行远程调用
 */
public class ProxyFactory {

    private static final HttpClient httpClient = new HttpClient();

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> interfaceClass) {

        Object proxy = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
            new Class[]{interfaceClass}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Invocation invocation = new Invocation(interfaceClass.getName(),
                        method.getName(),
                        method.getParameterTypes(), args);
                    ProviderURL providerUrl = RegisterCenter.getProviderUrl(
                        interfaceClass.getName());

                    if (providerUrl == null) {
                        throw new RuntimeException("未在注册中心找到服务提供者");
                    }

                    return httpClient.send(providerUrl.getHostname(), providerUrl.getPort(),
                        invocation);
                }
            });

        return (T) proxy;
    }

}
