package com.hao.demo.bank.external;

import com.hao.bank.types.Currency;
import com.hao.bank.types.ExchangeRate;

public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);
}
