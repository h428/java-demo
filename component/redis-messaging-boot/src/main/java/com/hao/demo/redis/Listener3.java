package com.hao.demo.redis;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 配置方式 3（不推荐）：利用 MessageListenerAdapter 默认构造器中的默认处理方法名为 handleMessage；
 * 配置相同的方法名，从而无需再指定处理方法，但方法名称必须固定为 handleMessage 否则会异常
 */
@Component
public class Listener3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener3.class);

    private AtomicInteger counter = new AtomicInteger();

    /**
     * 消息处理方法
     * @param message
     */
    public void handleMessage(String message) {
        LOGGER.info("Listener3.handleMessage 接收到消息 <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }

}
