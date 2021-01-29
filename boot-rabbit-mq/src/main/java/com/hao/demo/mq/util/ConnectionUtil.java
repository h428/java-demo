package com.hao.demo.mq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    /**
     * 建立与 RabbitMQ 的连接
     *
     * @return 返回连接
     * @throws Exception 连接异常
     */
    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置 rabbit-mq 的服务器地址
        factory.setHost("192.168.25.42");
        // 对应端口
        factory.setPort(5672);
        // 设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/leyou"); // v-host 一般用于不同项目区分前缀
        factory.setUsername("leyou");
        factory.setPassword("leyou");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
