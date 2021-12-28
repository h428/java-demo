package com.hao.demo.dal.entity;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String productCode;
    private String name;
    private Integer count;
}
