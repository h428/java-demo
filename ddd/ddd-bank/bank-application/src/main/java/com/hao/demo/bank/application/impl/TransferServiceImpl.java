package com.hao.demo.bank.application.impl;

import com.hao.bank.types.*;
import com.hao.demo.bank.application.TransferService;
import com.hao.demo.bank.domain.entity.Account;
import com.hao.demo.bank.domain.service.AccountTransferService;
import com.hao.demo.bank.domain.types.AuditMessage;
import com.hao.demo.bank.external.ExchangeRateService;
import com.hao.demo.bank.messaging.AuditMessageProducer;
import com.hao.demo.bank.repository.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;
    private final AuditMessageProducer auditMessageProducer;
    private final ExchangeRateService exchangeRateService;
    private final AccountTransferService accountTransferService;

    public TransferServiceImpl(AccountRepository accountRepository, AuditMessageProducer auditMessageProducer, ExchangeRateService exchangeRateService, AccountTransferService accountTransferService) {
        this.accountRepository = accountRepository;
        this.auditMessageProducer = auditMessageProducer;
        this.exchangeRateService = exchangeRateService;
        this.accountTransferService = accountTransferService;
    }

    @Transactional
    @Override
    public boolean transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) {
        // 参数校验
        Money targetMoney = new Money(targetAmount, new Currency(targetCurrency));
        UserId userId = new UserId(sourceUserId);
        // 读数据
        Account sourceAccount = accountRepository.find(userId);
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        // 业务逻辑
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        // 保存数据
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(userId, sourceAccount, targetAccount, targetMoney);
        auditMessageProducer.send(message);

        return true;
    }
}
