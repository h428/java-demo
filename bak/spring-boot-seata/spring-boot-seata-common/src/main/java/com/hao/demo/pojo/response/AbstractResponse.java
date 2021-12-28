package com.hao.demo.pojo.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class AbstractResponse implements Serializable {
    private int code;
    private String msg;
}
