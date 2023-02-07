package com.hao.bank.types;

import com.hao.bank.common.Identifier;
import lombok.Getter;

@Getter
public class AccountId implements Identifier {
    Long value;

    public AccountId(Long value) {
        this.value = value;
    }
}
