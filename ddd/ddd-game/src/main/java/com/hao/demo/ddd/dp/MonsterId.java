package com.hao.demo.ddd.dp;

public record MonsterId(Long value) {

    public MonsterId {
        if (value < 0) {
            throw new IllegalArgumentException("ID 值小于 0");
        }
    }
}
