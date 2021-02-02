package com.hao.demo.service;

import com.hao.demo.constant.ResCode;
import com.hao.demo.convert.OrderConvert;
import com.hao.demo.dal.entity.Order;
import com.hao.demo.dal.mapper.OrderMapper;
import com.hao.demo.pojo.dto.AccountDto;
import com.hao.demo.pojo.dto.OrderDto;
import com.hao.demo.pojo.response.ObjectResponse;
import io.seata.core.context.RootContext;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DubboService
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderConvert orderConvert;
    @DubboReference
    private AccountService accountService;

    @Override
    public ObjectResponse<OrderDto> createOrder(OrderDto orderDto) {
        log.info("全局事务ID：" + RootContext.getXID());
        ObjectResponse response = new ObjectResponse();
        try {
            //账户扣款
            AccountDto accountDto = new AccountDto();
            accountDto.setUserId(orderDto.getUserId());
            accountDto.setBalance(orderDto.getOrderAmount());
            ObjectResponse accountRes = accountService.decreaseAccount(accountDto);
            //创建订单
            Order order = orderConvert.dto2Order(orderDto);
            order.setOrderNo(UUID.randomUUID().toString());
            orderMapper.createOrder(order);
            //判断扣款状态(判断可以前置）
            if (accountRes.getCode() != ResCode.SUCCESS.getCode()) {
                response.setMsg(ResCode.FAILED.getMessage());
                response.setCode(ResCode.FAILED.getCode());
                return response;
            }
            response.setMsg(ResCode.SUCCESS.getMessage());
            response.setCode(ResCode.SUCCESS.getCode());
        } catch (Exception e) {
            log.error("createOrder Occur Exception:" + e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage() + "-" + e.getMessage());
        }
        return response;
    }
}
