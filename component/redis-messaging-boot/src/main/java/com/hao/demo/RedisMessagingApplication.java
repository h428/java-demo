package com.hao.demo;


import com.hao.demo.redis.Listener1;
import com.hao.demo.redis.Listener2;
import com.hao.demo.redis.Listener3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 使用 Spring 提供的 RedisTemplate 和 Messaging 机制实现消息订阅（类似 MQ）
 */
@SpringBootApplication
public class RedisMessagingApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessagingApplication.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter1,
        MessageListenerAdapter listenerAdapter2,
        MessageListenerAdapter listenerAdapter3
        ) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        // 订阅了一个叫 chat1 的通道，这个 container 可以添加多个 messageListener
        container.addMessageListener(listenerAdapter1, new PatternTopic("chat1"));
        container.addMessageListener(listenerAdapter2, new PatternTopic("chat2"));
        container.addMessageListener(listenerAdapter3, new PatternTopic("chat3"));

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter1(Listener1 listener) {
        // 配置消息监听适配器时指定处理消息的方法为 receiveMessage
        return new MessageListenerAdapter(listener, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(Listener2 listener) {
        return new MessageListenerAdapter(listener);
    }

    @Bean
    MessageListenerAdapter listenerAdapter3(Listener3 listener) {
        // 配置消息监听适配器时指定处理消息的方法为 receiveMessage
        return new MessageListenerAdapter(listener);
    }

    @Bean
    Listener1 receiver() {
        return new Listener1();
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext ctx = SpringApplication.run(RedisMessagingApplication.class, args);

        StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);


        //
        Listener1 listener1 = ctx.getBean(Listener1.class);
        while (listener1.getCount() == 0) {
            LOGGER.info("Sending message...");
            // 使用 RedisTemplate 往指定通道发送一条消息
            template.convertAndSend("chat1", "Hello from Redis!");
            Thread.sleep(500L);
        }

        Listener2 listener2 = ctx.getBean(Listener2.class);
        while (listener2.getCount() == 0) {
            LOGGER.info("Sending message...");
            // 使用 RedisTemplate 往指定通道发送一条消息
            template.convertAndSend("chat2", "Hello from Redis!");
            Thread.sleep(500L);
        }

        Listener3 listener3 = ctx.getBean(Listener3.class);
        while (listener3.getCount() == 0) {
            LOGGER.info("Sending message...");
            // 使用 RedisTemplate 往指定通道发送一条消息
            template.convertAndSend("chat3", "Hello from Redis!");
            Thread.sleep(500L);
        }

        System.exit(0);
    }

}
