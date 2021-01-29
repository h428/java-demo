package com.demo.redis.service;

import com.demo.base.entity.User;
import com.demo.base.util.EntityUtil;
import com.demo.redis.BootRedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootRedisApplication.class)
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void setBean() {
        User user = EntityUtil.generateRandomOne(User.class);
        assert this.redisService.setBean("a", user);
    }
}