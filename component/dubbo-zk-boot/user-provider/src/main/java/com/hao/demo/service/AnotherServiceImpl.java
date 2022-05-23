package com.hao.demo.service;

import com.hao.demo.api.AnotherService;
import java.util.Random;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class AnotherServiceImpl implements AnotherService {

    static Random random = new Random();

    @Override
    public int getInt() {
        return random.nextInt() >>> 1 % 1000;
    }
}
