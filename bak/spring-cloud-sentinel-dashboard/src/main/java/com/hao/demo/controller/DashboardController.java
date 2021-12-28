package com.hao.demo.controller;

import com.hao.demo.vo.MyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @GetMapping("dash")
    public MyResponse say() {
        return MyResponse.builder().code(200).msg("请求成功").build();
    }
}
