package com.hao.demo.bank.external.impl;

import com.hao.bank.types.Currency;
import com.hao.demo.bank.external.YahooForExService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 模拟外部雅虎服务
 */
@Component
public class YahooForExServiceImpl implements YahooForExService {

    @Override
    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        return BigDecimal.ONE;
    }
}
