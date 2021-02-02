package com.hao.demo.dal.mapper;

import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    int decreaseAccount(@Param("userId") String userId, @Param("balance") Double balance);
}
