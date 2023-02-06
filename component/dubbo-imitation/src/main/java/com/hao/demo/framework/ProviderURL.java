package com.hao.demo.framework;

import java.io.Serializable;

/**
 * 描述服务提供者的 url
 */
public class ProviderURL implements Serializable {

    private String hostname;

    private int port;

    public ProviderURL(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public ProviderURL setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public int getPort() {
        return port;
    }

    public ProviderURL setPort(int port) {
        this.port = port;
        return this;
    }
}
