package com.hao.demo.bank.application;

import java.math.BigDecimal;

public interface TransferService {
    boolean transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) ;
}
