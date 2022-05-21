package com.hao.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hao.redisson")
public class RedissonProperties {
    private String host = "localhost";
    private String password;
    private int port = 6379;
    private int timeout;
    private boolean ssl;

    public String getHost() {
        return host;
    }

    public RedissonProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RedissonProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getPort() {
        return port;
    }

    public RedissonProperties setPort(int port) {
        this.port = port;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public RedissonProperties setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public boolean isSsl() {
        return ssl;
    }

    public RedissonProperties setSsl(boolean ssl) {
        this.ssl = ssl;
        return this;
    }
}
