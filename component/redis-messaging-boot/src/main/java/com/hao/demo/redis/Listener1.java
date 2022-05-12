package com.hao.demo.redis;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置方式 1（推荐）：Pojo 类型的消息监听器，在配置 MessageListenerAdapter 时指定处理方法，完全无侵入
 */
public class Listener1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener1.class);

    private AtomicInteger counter = new AtomicInteger();

    /**
     * 接收消息的处理方法，在配置 MessageListenerAdapter 指定方法名称
     * @param message
     */
    public void receiveMessage(String message) {
        LOGGER.info("Listener1.receiveMessage 接收到消息 <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }

}
