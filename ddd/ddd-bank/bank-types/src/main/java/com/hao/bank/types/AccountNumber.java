package com.hao.bank.types;

import lombok.Getter;

@Getter
public class AccountNumber {
    private final String value;

    public AccountNumber(String value) {
        this.value = value;
    }

}
