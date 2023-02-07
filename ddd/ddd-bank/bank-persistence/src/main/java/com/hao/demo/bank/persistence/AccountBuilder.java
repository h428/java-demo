package com.hao.demo.bank.persistence;

import com.hao.bank.types.*;
import com.hao.demo.bank.domain.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountBuilder {
    public Account toAccount(AccountDO accountDO) {
        Currency currency = new Currency(accountDO.getCurrency());
        return new Account(new AccountId(accountDO.getId()), new AccountNumber(accountDO.getAccountNumber()),
                new UserId(accountDO.getId()), new Money(accountDO.getAvailable(), currency),
                new Money(accountDO.getDailyLimit(), currency));
    }

    public AccountDO fromAccount(Account account) {
        return new AccountDO(account.getId().getValue(), account.getAccountNumber().getValue(),
                account.getUserId().getId(), account.getAvailable().getValue(), account.getDailyLimit().getValue(), account.getCurrency().getValue());
    }
}
