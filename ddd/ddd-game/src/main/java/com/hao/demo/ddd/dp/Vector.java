package com.hao.demo.ddd.dp;


public record Vector(long x, long y) {
    public static final Vector ZERO = new Vector(0, 0);
}
