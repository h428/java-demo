package com.hao.demo.provider.storage;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {

    private static final Map<Class<?>, Object> instanceMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T get(Class<T> clazz) {
        try {
            T t = (T) instanceMap.get(clazz);
            if (t == null) {
                t = clazz.newInstance();
                instanceMap.put(clazz, t);
            }
            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
