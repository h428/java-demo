package com.hao.demo.ddd.dp;

public record Health(int value) {
    public static final Health Zero = new Health(0);

    public static Health of(int value) {
        return new Health(value);
    }
}
