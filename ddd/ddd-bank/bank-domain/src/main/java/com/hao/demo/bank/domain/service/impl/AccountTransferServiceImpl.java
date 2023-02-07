package com.hao.demo.bank.domain.service.impl;

import com.hao.bank.types.ExchangeRate;
import com.hao.bank.types.Money;
import com.hao.demo.bank.domain.entity.Account;
import com.hao.demo.bank.domain.service.AccountTransferService;
import com.hao.demo.bank.external.ExchangeRateService;
import org.springframework.stereotype.Component;

@Component
public class AccountTransferServiceImpl implements AccountTransferService {

    private final ExchangeRateService exchangeRateService;

    public AccountTransferServiceImpl(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) {
        Money sourceMoney = exchangeRate.exchangeTo(targetMoney);
        sourceAccount.withdraw(sourceMoney);
        targetAccount.deposit(targetMoney);
    }
}
