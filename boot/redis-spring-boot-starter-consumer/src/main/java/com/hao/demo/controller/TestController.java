package com.hao.demo.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("put/{val}")
    @ResponseBody
    public Boolean put(@PathVariable String val) {
        RBucket<String> rBucket = this.redissonClient.getBucket("k");
        rBucket.set(val);
        return true;
    }

    @GetMapping("get")
    @ResponseBody
    public String put() {
        RBucket<String> rBucket = this.redissonClient.getBucket("k");
        return rBucket.get();
    }

}
