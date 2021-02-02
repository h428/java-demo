package com.hao.demo.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderRequest implements Serializable {
    private String userId;
    private String productCode;
    private String name;
    private Integer count;
    private BigDecimal amount;
}
