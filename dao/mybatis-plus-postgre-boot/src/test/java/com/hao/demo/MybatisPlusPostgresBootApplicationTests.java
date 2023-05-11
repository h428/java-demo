package com.hao.demo;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hao.demo.entity.BaseUser;
import com.hao.demo.mapper.BaseUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
public class MybatisPlusPostgresBootApplicationTests {

    @Autowired
    private BaseUserMapper baseUserMapper;
    @Test
    public void contextLoads() {
        List<BaseUser> baseUsers = baseUserMapper.selectList(Wrappers.lambdaQuery());
        System.out.println(baseUsers);
    }

}
