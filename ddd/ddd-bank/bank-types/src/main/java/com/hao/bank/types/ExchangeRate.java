package com.hao.bank.types;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class ExchangeRate {
    private final BigDecimal foreignExchange;
    private final Currency source;
    private final Currency target;

    public ExchangeRate(BigDecimal foreignExchange, Currency source, Currency target) {
        this.foreignExchange = foreignExchange;
        this.source = source;
        this.target = target;
    }


    public Money exchangeTo(Money targetMoney) {
        BigDecimal sourceAmount = targetMoney.getValue().divide(foreignExchange, RoundingMode.DOWN);
        return new Money(sourceAmount, target);
    }
}
