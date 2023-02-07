package com.hao.demo.bank.domain.service;

import com.hao.bank.types.ExchangeRate;
import com.hao.bank.types.Money;
import com.hao.demo.bank.domain.entity.Account;

public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate);
}
