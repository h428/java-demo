package com.hao.demo.receiver;

import com.hao.demo.channel.InputChannel;
import com.hao.demo.entity.Order;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class BaseReceiver {

    @StreamListener(Sink.INPUT)
    public void receiveOutput(String msg) {
        System.out.println("input receive：" + msg + ", time = " + System.currentTimeMillis());
    }

    @StreamListener(InputChannel.INPUT1)
    public void receiveOutput1(String msg) {
        System.out.println("input1 receive：" + msg + ", time = " + System.currentTimeMillis());
    }

    @StreamListener(InputChannel.INPUT2)
    public void receiveOutput2(String msg) {
        System.out.println("input2 receive：" + msg + ", time = " + System.currentTimeMillis());
    }

    @StreamListener(InputChannel.ORDER_INPUT)
    public void receiveOrderOutput(Order order) {
        System.out.println("order-input receive：" + order + ", time = " + System.currentTimeMillis());
    }



}
