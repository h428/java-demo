package com.hao.demo.bank.external;

import com.hao.bank.types.Currency;

import java.math.BigDecimal;

public interface YahooForExService {
    BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency);
}
