package com.hao.demo.ddd.dp;

public record Transform(long x, long y) {
    public static final Transform ORIGIN = new Transform(0, 0);
}
