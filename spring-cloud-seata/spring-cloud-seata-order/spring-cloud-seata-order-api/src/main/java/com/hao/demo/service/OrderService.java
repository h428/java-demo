package com.hao.demo.service;

import com.hao.demo.pojo.dto.OrderDto;
import com.hao.demo.pojo.response.ObjectResponse;

public interface OrderService {

    /**
     * 创建订单
     */
    ObjectResponse<OrderDto> createOrder(OrderDto orderDto);
}
