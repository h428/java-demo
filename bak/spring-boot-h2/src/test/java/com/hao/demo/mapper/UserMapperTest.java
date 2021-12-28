package com.hao.demo.mapper;

import static org.junit.Assert.*;

import com.hao.demo.BaseTest;
import com.hao.demo.entity.User;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        List<User> users = this.userMapper.selectList(null);
        Assert.assertEquals(5, users.size());
    }

}