package com.hao.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @SentinelResource(value = "hello", blockHandler = "blockHandlerHello")
    @GetMapping("say")
    public String hello() {
        return "hello, Mic";
    }

    public String blockHandlerHello(BlockException e) {
        return "被限流了";
    }

}
