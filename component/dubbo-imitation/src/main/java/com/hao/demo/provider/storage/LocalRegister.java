package com.hao.demo.provider.storage;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {

    private static final Map<String, Class<?>> map = new HashMap<>();

    public static void register(String interfaceName, Class<?> clazz) {
        map.put(interfaceName, clazz);
    }

    public static Class<?> get(String interfaceName) {
        return map.get(interfaceName);
    }

}
