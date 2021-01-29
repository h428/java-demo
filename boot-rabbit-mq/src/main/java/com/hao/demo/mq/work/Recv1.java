package com.hao.demo.mq.work;

import com.hao.demo.mq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv1 {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] argv) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();

        // 获取通道
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 设置每个消费者同时只能处理一条消息，避免均分
        channel.basicQos(1);

        // 定义队列的消费者
        DefaultConsumer comsumer = new DefaultConsumer(channel) {
            // 消费消息并做处理，类似监听事件，有消息就会自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("[消费者1] received :" + msg + "!");

                try {
                    // 模拟耗时任务
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                // 手动 Ack
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 设置消费者并监听消息
        channel.basicConsume(QUEUE_NAME, false, comsumer);
    }

}
