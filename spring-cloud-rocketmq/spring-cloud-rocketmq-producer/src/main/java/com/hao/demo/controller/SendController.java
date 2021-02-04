package com.hao.demo.controller;

import com.hao.demo.entity.Order;
import com.hao.demo.source.OrderSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("send")
public class SendController {

    @Autowired
    private Source source;

    @Autowired
    private OrderSource orderSource;

    @GetMapping("output/{msg}")
    public String sendOutput(@PathVariable String msg) {
        MessageBuilder<String> messageBuilder = MessageBuilder.withPayload(msg);
        Message<String> message = messageBuilder.build();
        source.output().send(message);
        return "send " + msg + " to TopicOutput success";
    }

    @GetMapping("order/{name}/{cnt}")
    public String sendOrder(@PathVariable String name, @PathVariable Integer cnt) {
        Order order = Order.builder().name(name).cnt(cnt).build();
        MessageBuilder<Order> messageBuilder = MessageBuilder.withPayload(order);
        Message<Order> message = messageBuilder.build();
        orderSource.output().send(message);
        return "send " + order + " to TopicOrder success";
    }
}
