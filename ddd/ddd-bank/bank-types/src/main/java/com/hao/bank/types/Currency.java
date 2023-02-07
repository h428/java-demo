package com.hao.bank.types;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Currency {
    private final String value;

    public Currency(String value) {
        this.value = value;
    }
}
