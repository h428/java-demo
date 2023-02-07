package com.hao.demo.bank.persistence;

import com.hao.bank.types.*;
import com.hao.demo.bank.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AccountDAO extends JpaRepository<AccountDO, Long> {
    Optional<AccountDO> findById(Long value);

    AccountDO findByAccountNumber(String value);

    AccountDO findByUserId(Long id);

    AccountDO saveAndFlush(AccountDO accountDO);

}
