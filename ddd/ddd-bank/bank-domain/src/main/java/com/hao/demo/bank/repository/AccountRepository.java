package com.hao.demo.bank.repository;

import com.hao.bank.types.AccountId;
import com.hao.bank.types.AccountNumber;
import com.hao.bank.types.UserId;
import com.hao.bank.common.Repository;
import com.hao.demo.bank.domain.entity.Account;

/**
 * infrastructure - 持久层接口
 */
public interface AccountRepository extends Repository<Account, AccountId> {
    Account find(AccountId id);
    Account find(AccountNumber accountNumber);
    Account find(UserId userId);
    // Account save(Account account);
}
