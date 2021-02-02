package com.hao.demo.service;

import com.hao.demo.pojo.dto.AccountDto;
import com.hao.demo.pojo.response.ObjectResponse;

public interface AccountService {
    ObjectResponse decreaseAccount(AccountDto accountDto);
}
