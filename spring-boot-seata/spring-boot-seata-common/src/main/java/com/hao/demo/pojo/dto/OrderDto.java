package com.hao.demo.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderDto implements Serializable {
    private String orderNo;
    private String userId;
    private String productCode;
    private Integer orderCount;
    private BigDecimal orderAmount;
}
