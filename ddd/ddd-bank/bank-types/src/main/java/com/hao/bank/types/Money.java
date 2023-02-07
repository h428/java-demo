package com.hao.bank.types;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Money implements Comparable<Money> {
    private final BigDecimal value;
    private final Currency currency;

    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Money add(Money money) {
        return new Money(value.add(money.value), currency);
    }

    @Override
    public int compareTo(Money o) {
        return value.compareTo(o.getValue());
    }

    public Money subtract(Money money) {
        return new Money(value.subtract(money.value), currency);
    }
}
