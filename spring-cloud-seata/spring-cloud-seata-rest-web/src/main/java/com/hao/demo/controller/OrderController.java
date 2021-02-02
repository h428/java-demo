package com.hao.demo.controller;

import com.hao.demo.pojo.dto.OrderRequest;
import com.hao.demo.pojo.response.ObjectResponse;
import com.hao.demo.service.RestOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private RestOrderService restOrderService;

    @PostMapping("/order")
    public ObjectResponse order(@RequestBody OrderRequest orderRequest) throws Exception {
        return restOrderService.handleBusiness(orderRequest);
    }

}
