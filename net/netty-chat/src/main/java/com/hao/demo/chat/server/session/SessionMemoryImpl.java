package com.hao.demo.chat.server.session;

import io.netty.channel.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 普通会话的实现类
 */
public class SessionMemoryImpl implements Session {

    /** username 映射到 Channel 的 map */
    private final Map<String, Channel> usernameChannelMap = new ConcurrentHashMap<>();
    /** Channel 映射到 username 的 map */
    private final Map<Channel, String> channelUsernameMap = new ConcurrentHashMap<>();
    /** Channel 的属性集 */
    private final Map<Channel, Map<String, Object>> channelAttributesMap = new ConcurrentHashMap<>();

    @Override
    public void bind(Channel channel, String username) {
        // 双向 map 绑定
        usernameChannelMap.put(username, channel);
        channelUsernameMap.put(channel, username);
        // 同时初始化空属性集合
        channelAttributesMap.put(channel, new ConcurrentHashMap<>());
    }

    @Override
    public void unbind(Channel channel) {
        // 双向 map 解绑
        String username = channelUsernameMap.remove(channel);
        usernameChannelMap.remove(username);
        // 属性集合移除对应 Channel
        channelAttributesMap.remove(channel);
    }

    @Override
    public Object getAttribute(Channel channel, String name) {
        // 从属性集合中获取指定 channel 的指定属性
        return channelAttributesMap.get(channel).get(name);
    }

    @Override
    public void setAttribute(Channel channel, String name, Object value) {
        // 设置指定 channel 的指定属性
        channelAttributesMap.get(channel).put(name, value);
    }

    @Override
    public Channel getChannel(String username) {
        // 获取绑定的 channel
        return usernameChannelMap.get(username);
    }

    @Override
    public String toString() {
        // 打印绑定信息，取 username -> Channel 的 map 作为打印
        return usernameChannelMap.toString();
    }
}
