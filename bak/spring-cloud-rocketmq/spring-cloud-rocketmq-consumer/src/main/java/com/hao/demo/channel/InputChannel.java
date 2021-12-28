package com.hao.demo.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannel {

    String INPUT1 = "input1";
    String INPUT2 = "input2";
    String ORDER_INPUT = "orderInput";

    @Input(INPUT1)
    SubscribableChannel input1();
    @Input(INPUT2)
    SubscribableChannel input2();
    @Input(ORDER_INPUT)
    SubscribableChannel orderInput();
}
