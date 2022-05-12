package com.hao.demo.redis;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 配置方式 2（使用）：实现 MessageListener 接口，配置 MessageListenerAdapter 无需再指定处理方法，接口方法即为消息处理方法
 */
@Component
public class Listener2 implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener2.class);

    private AtomicInteger counter = new AtomicInteger();

    /**
     * 消息处理方法
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        LOGGER.info("Listener2.onMessage 接收到消息 <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }

}
