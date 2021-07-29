package com.hao.demo.controller;


import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Random RANDOM = new Random();

    @GetMapping("test")
    public String test() {
        return String.valueOf(RANDOM.nextInt() >>> 1);
    }
}
