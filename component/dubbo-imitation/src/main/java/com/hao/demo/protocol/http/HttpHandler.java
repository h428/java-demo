package com.hao.demo.protocol.http;

import com.hao.demo.framework.Invocation;
import com.hao.demo.provider.storage.BeanFactory;
import com.hao.demo.provider.storage.LocalRegister;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpHandler {

    public void write(Object data, OutputStream outputStream) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 核心处理请求以及返回结果
     */
    public void handle(HttpServletRequest req, HttpServletResponse resp) {

        try {
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) ois.readObject();

            Class<?> implClass = LocalRegister.get(invocation.getInterfaceName());
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            // 执行方法并获得结果
            Object result = method.invoke(BeanFactory.get(implClass), invocation.getParams());

            // 把结果返回给服务提供者
            write(result, resp.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
