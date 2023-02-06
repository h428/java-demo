package com.hao.demo.chat.message;

import com.hao.demo.chat.rpc.message.RpcRequestMessage;
import com.hao.demo.chat.rpc.message.RpcResponseMessage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * 抽象消息类型，不同类型的具体消息都继承该类并自定义消息内容
 */
@Data
public abstract class Message implements Serializable {

    /** 请求号 */
    private int seqId;

    /** 消息类型 */
    private int messageType;

    public abstract int getMessageType();

    public static final int LoginRequestMessage = 0;
    public static final int LoginResponseMessage = 1;
    public static final int ChatRequestMessage = 2;
    public static final int ChatResponseMessage = 3;
    public static final int GroupCreateRequestMessage = 4;
    public static final int GroupCreateResponseMessage = 5;
    public static final int GroupJoinRequestMessage = 6;
    public static final int GroupJoinResponseMessage = 7;
    public static final int GroupQuitRequestMessage = 8;
    public static final int GroupQuitResponseMessage = 9;
    public static final int GroupChatRequestMessage = 10;
    public static final int GroupChatResponseMessage = 11;
    public static final int GroupMembersRequestMessage = 12;
    public static final int GroupMembersResponseMessage = 13;
    public static final int PingMessage = 14;
    public static final int PongMessage = 15;

    /** 请求类型 byte 值 */
    public static final int RPC_MESSAGE_TYPE_REQUEST = 101;

    /** 响应类型 byte 值 */
    public static final int RPC_MESSAGE_TYPE_RESPONSE = 102;

    private static final Map<Integer, Class<? extends Message>> messageClassMap = new HashMap<>();

    /**
     * 根据消息类型，获取对应的 Class
     *
     * @param messageType 消息类型
     * @return 返回消息类型对应的 Class
     */
    public static Class<? extends Message> getMessageClass(int messageType) {
        return messageClassMap.get(messageType);
    }

    static {
        messageClassMap.put(LoginRequestMessage, LoginRequestMessage.class);
        messageClassMap.put(LoginResponseMessage, LoginResponseMessage.class);
        messageClassMap.put(ChatRequestMessage, ChatRequestMessage.class);
        messageClassMap.put(ChatResponseMessage, ChatResponseMessage.class);
        messageClassMap.put(GroupCreateRequestMessage, GroupCreateRequestMessage.class);
        messageClassMap.put(GroupCreateResponseMessage, GroupCreateResponseMessage.class);
        messageClassMap.put(GroupJoinRequestMessage, GroupJoinRequestMessage.class);
        messageClassMap.put(GroupJoinResponseMessage, GroupJoinResponseMessage.class);
        messageClassMap.put(GroupQuitRequestMessage, GroupQuitRequestMessage.class);
        messageClassMap.put(GroupQuitResponseMessage, GroupQuitResponseMessage.class);
        messageClassMap.put(GroupChatRequestMessage, GroupChatRequestMessage.class);
        messageClassMap.put(GroupChatResponseMessage, GroupChatResponseMessage.class);
        messageClassMap.put(GroupMembersRequestMessage, GroupMembersRequestMessage.class);
        messageClassMap.put(GroupMembersResponseMessage, GroupMembersResponseMessage.class);
        messageClassMap.put(RPC_MESSAGE_TYPE_REQUEST, RpcRequestMessage.class);
        messageClassMap.put(RPC_MESSAGE_TYPE_RESPONSE, RpcResponseMessage.class);
    }


}
