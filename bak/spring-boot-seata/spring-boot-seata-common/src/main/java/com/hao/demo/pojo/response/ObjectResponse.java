package com.hao.demo.pojo.response;

import lombok.Data;

@Data
public class ObjectResponse<T> extends AbstractResponse {
    private T data;
}
