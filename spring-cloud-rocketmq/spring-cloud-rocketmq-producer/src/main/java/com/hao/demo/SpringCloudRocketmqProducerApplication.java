package com.hao.demo;

import com.hao.demo.source.OrderSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding({Source.class, OrderSource.class})
public class SpringCloudRocketmqProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRocketmqProducerApplication.class, args);
    }
}
