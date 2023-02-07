package com.hao.bank.types;

import lombok.Getter;

@Getter
public class UserId {
    private final Long id;

    public UserId(Long id) {
        this.id = id;
    }
}
