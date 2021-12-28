package com.hao.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ResCode {
    SUCCESS(200, "请求成功"),
    SYSTEM_EXCEPTION(500,"系统异常"),
    FAILED(999, "系统错误");

    private int code;
    private String message;
}
