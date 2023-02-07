package com.hao.demo.bank.external.impl;

import com.hao.bank.types.Currency;
import com.hao.bank.types.ExchangeRate;
import com.hao.demo.bank.external.ExchangeRateService;
import com.hao.demo.bank.external.YahooForExService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ExchangeRateServiceImpl implements ExchangeRateService {

    // 使用雅虎的实现
    private final YahooForExService yahooForexService;

    public ExchangeRateServiceImpl(YahooForExService yahooForexService) {
        this.yahooForexService = yahooForexService;
    }

    @Override
    public ExchangeRate getExchangeRate(Currency source, Currency target) {
        if (source.equals(target)) {
            return new ExchangeRate(BigDecimal.ONE, source, target);
        }
        BigDecimal foreignExchange = yahooForexService.getExchangeRate(source, target);
        return new ExchangeRate(foreignExchange, source, target);
    }
}
