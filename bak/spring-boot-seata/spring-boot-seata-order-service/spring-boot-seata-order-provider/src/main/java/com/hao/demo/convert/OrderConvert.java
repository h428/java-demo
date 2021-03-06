package com.hao.demo.convert;


import com.hao.demo.dal.entity.Order;
import com.hao.demo.pojo.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderConvert {
    @Mappings({
        @Mapping(source = "orderCount",target = "count"),
        @Mapping(source = "orderAmount",target = "amount")
    })
    Order dto2Order(OrderDto orderDto);
}
