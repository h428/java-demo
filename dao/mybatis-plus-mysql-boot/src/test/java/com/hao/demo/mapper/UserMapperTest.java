package com.hao.demo.mapper;

import static org.junit.Assert.*;

import com.hao.demo.BaseTest;
import com.hao.demo.generate.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void get() {
        assertNotNull(this.userMapper.selectById(1L));
        assertEquals("cat", this.userMapper.selectById(1L).getUserName());
        assertNotNull(this.userMapper.selectById(2L));
        assertNull(this.userMapper.selectById(1112L));
    }

}