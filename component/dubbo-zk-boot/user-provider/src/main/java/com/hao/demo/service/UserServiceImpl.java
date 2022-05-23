package com.hao.demo.service;

import com.hao.demo.api.UserService;
import com.hao.demo.entity.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

/**
 * `@DubboService` 注解将该服务标记为服务提供者，并会在 Zookeeper 中创建对应的 ZNode 节点，对应的层级结构为：
 * /dubbo/com.hao.demo.api.UserService/providers/dubbo://192.168.25.1:20880/com.hao.demo.api.UserService?anyhost=true&application=user-provider&deprecated=false&dubbo=2.0.2&dynamic=true&generic=false&interface=com.hao.demo.api.UserService&metadata-type=remote&methods=getById,randomOne&pid=5608&release=2.7.8&side=provider&timestamp=1653342472721
 * 其中，最后一层为临时节点，其节点值为服务提供者的 ip，节点名称为 dubbo 服务的连接地址共消费者调用（包含大量相关参数）
 * 由于临时节点的特性，若服务提供者宕机与 zookeeper 断开连接，则临时节点会自动被删除从而达到自动剔除无效服务的效果
 */
@DubboService
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User getById(Long id) {
        return User.randomOne(id);
    }

    @Override
    public User randomOne() {
        return User.randomOne();
    }
}
