package com.hao.demo.dal.mapper;

import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

    /**
     * 扣除库存
     */
    int decreaseProduct(@Param("productCode") String productCode, @Param("count") Integer count);
}
