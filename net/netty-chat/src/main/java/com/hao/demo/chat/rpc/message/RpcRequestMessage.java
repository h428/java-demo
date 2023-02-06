package com.hao.demo.chat.rpc.message;

import com.hao.demo.chat.message.Message;
import lombok.Getter;

@Getter
public class RpcRequestMessage extends Message {

    /**
     * rpc 接口名，服务端根据接口找到对应实现
     */
    private String interfaceName;

    /**
     * rpc 调用的方法名
     */
    private String methodName;

    /**
     * 调用方法的返回类型
     */
    private Class<?> returnType;

    /**
     * 调用方法的参数列表
     */
    private Class<?>[] parameterTypes;

    /**
     * 调用方法的参数值
     */
    private Object[] parameterValues;

    public RpcRequestMessage(int seqId, String interfaceName, String methodName, Class<?> returnType,
            Class<?>[] parameterTypes, Object[] parameterValues) {
        super.setSeqId(seqId);
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_REQUEST;
    }
}
