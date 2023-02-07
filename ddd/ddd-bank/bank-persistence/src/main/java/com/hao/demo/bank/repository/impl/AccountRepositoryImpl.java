package com.hao.demo.bank.repository.impl;

import com.hao.bank.types.AccountId;
import com.hao.bank.types.AccountNumber;
import com.hao.bank.types.UserId;
import com.hao.demo.bank.domain.entity.Account;
import com.hao.demo.bank.persistence.AccountBuilder;
import com.hao.demo.bank.persistence.AccountDAO;
import com.hao.demo.bank.persistence.AccountDO;
import com.hao.demo.bank.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountDAO accountDAO;
    private final AccountBuilder accountBuilder;

    public AccountRepositoryImpl(AccountDAO accountDAO, AccountBuilder accountBuilder) {
        this.accountDAO = accountDAO;
        this.accountBuilder = accountBuilder;
    }

    @Override
    public void save(Account account) {
        AccountDO accountDO = accountBuilder.fromAccount(account);
        accountDAO.saveAndFlush(accountDO);
        // return accountBuilder.toAccount(accountDO);
    }

    @Override
    public void remove(Account aggregate) {
        accountDAO.deleteById(aggregate.getId().getValue());
    }

    @Override
    public Account find(AccountId id) {
        AccountDO accountDO = accountDAO.findById(id.getValue()).get();
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(AccountNumber accountNumber) {
        AccountDO accountDO = accountDAO.findByAccountNumber(accountNumber.getValue());
        return accountBuilder.toAccount(accountDO);
    }

    @Override
    public Account find(UserId userId) {
        AccountDO accountDO = accountDAO.findByUserId(userId.getId());
        return accountBuilder.toAccount(accountDO);
    }


}
