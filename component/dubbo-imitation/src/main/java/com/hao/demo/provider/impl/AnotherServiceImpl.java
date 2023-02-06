package com.hao.demo.provider.impl;

import com.hao.demo.provider.api.AnotherService;
import java.util.Random;

public class AnotherServiceImpl implements AnotherService {

    static Random random = new Random();

    @Override
    public int randomInt() {
        return random.nextInt() >>> 1 % 1000;
    }
}
