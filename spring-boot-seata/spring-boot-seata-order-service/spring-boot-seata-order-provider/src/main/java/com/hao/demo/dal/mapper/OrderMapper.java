package com.hao.demo.dal.mapper;

import com.hao.demo.dal.entity.Order;

public interface OrderMapper {

    /**
     * 创建订单
     */
    void createOrder(Order order);
}
