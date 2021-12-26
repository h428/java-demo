package com.hao.demo.mapper;

import com.hao.demo.BaseTest;
import com.hao.demo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        assertNull(userMapper.selectById(6));
        User user = User.builder().id(6L).name("hao").age(24).email("lyh@126.com").build();
        this.userMapper.insert(user);
        assertNotNull(userMapper.selectById(6));
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


}