package com.hao.demo.framework;

import java.io.Serializable;

public class Invocation implements Serializable {

    private String interfaceName;

    private String methodName;

    private Class<?>[] paramTypes;

    private Object[] params;

    public Invocation(String interfaceName, String methodName, Class<?>[] paramTypes,
        Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public Invocation setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public Invocation setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public Invocation setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
        return this;
    }

    public Object[] getParams() {
        return params;
    }

    public Invocation setParams(Object[] params) {
        this.params = params;
        return this;
    }
}
