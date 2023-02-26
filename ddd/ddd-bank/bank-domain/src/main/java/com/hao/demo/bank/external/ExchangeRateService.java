package com.hao.demo.bank.external;

import com.hao.bank.types.Currency;
import com.hao.bank.types.ExchangeRate;

/**
 * infrastructure - 外部服务接口，类似 ACL 层的接口
 */
public interface ExchangeRateService {
    ExchangeRate getExchangeRate(Currency source, Currency target);
}
