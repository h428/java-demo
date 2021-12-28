package com.hao.demo.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountDto implements Serializable {
    private Integer id;
    private String userId;
    private BigDecimal balance;
}
