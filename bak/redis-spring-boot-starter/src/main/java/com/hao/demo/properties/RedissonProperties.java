package com.hao.demo.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hao.redisson")
@Data
@NoArgsConstructor
public class RedissonProperties {
    private String host = "localhost";
    private String password;
    private int port = 6379;
    private int timeout;
    private boolean ssl;
}
