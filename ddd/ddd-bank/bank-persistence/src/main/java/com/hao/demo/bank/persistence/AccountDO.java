package com.hao.demo.bank.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="account")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDO {
    @Id
    @GeneratedValue
    private Long id;
    private String accountNumber;
    private Long userId;
    private BigDecimal available;
    private BigDecimal dailyLimit;
    private String currency;
}
