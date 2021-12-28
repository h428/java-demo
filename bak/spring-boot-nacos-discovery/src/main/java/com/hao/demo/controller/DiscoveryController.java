package com.hao.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @GetMapping("test")
    public Integer test() {
        return new Random().nextInt() >>> 1;
    }

    @GetMapping("discovery")
    public List<Instance> discovery(@RequestParam String serviceName) throws NacosException {
        return this.namingService.getAllInstances(serviceName);
    }
}
