package com.hao.demo.service;

import com.hao.demo.constant.ResCode;
import com.hao.demo.pojo.dto.OrderDto;
import com.hao.demo.pojo.dto.OrderRequest;
import com.hao.demo.pojo.dto.ProductDto;
import com.hao.demo.pojo.response.ObjectResponse;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestOrderService {


    @DubboReference
    private ProductService productService;

    @DubboReference
    private OrderService orderService;


    @GlobalTransactional(timeoutMills = 300000, name = "sample-rest-web")
    public ObjectResponse handleBusiness(OrderRequest orderRequest) throws Exception {
        log.info("开始全局事务:xid=" + RootContext.getXID());
        log.info("begin order: " + orderRequest);
        //1. 扣减库存
        ProductDto productDto = new ProductDto();
        productDto.setProductCode(orderRequest.getProductCode());
        productDto.setCount(orderRequest.getCount());
        ObjectResponse repoRes = productService.decreaseProduct(productDto);
        //2. 创建订单
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(orderRequest.getUserId());
        orderDto.setOrderAmount(orderRequest.getAmount());
        orderDto.setOrderCount(orderRequest.getCount());
        orderDto.setProductCode(orderRequest.getProductCode());
        ObjectResponse orderRes = orderService.createOrder(orderDto);
        if (orderRequest.getProductCode().equals("GP20200202002")) {
            throw new Exception("系统异常");
        }

        ObjectResponse response = new ObjectResponse();
        response.setMsg(ResCode.SUCCESS.getMessage());
        response.setCode(ResCode.SUCCESS.getCode());
        response.setData(orderRes.getData());
        return response;
    }

}
