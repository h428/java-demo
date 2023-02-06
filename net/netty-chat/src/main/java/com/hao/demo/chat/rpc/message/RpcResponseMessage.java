package com.hao.demo.chat.rpc.message;

import com.hao.demo.chat.message.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RpcResponseMessage extends Message {

    /**
     * rpc 调用返回值
     */
    private Object returnValue;

    /**
     * rpc 调用异常
     */
    private Exception exceptionValue;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
