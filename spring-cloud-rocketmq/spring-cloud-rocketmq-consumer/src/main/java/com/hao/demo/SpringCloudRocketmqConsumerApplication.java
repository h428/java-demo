package com.hao.demo;

import com.hao.demo.channel.InputChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
// Sink 为 spring 帮你定义好的名称为 input 的通道
@EnableBinding({Sink.class, InputChannel.class})
public class SpringCloudRocketmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRocketmqConsumerApplication.class, args);
    }

}
