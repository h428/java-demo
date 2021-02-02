package com.hao.demo.dal.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private String orderNo;
    private String userId;
    private String productCode;
    private Integer count;
    private Double amount;
}
