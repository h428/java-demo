package com.hao.demo.dao;

import static org.junit.Assert.*;

import com.hao.demo.BaseTest;
import com.hao.demo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;


    @Test
    @Transactional
    @Rollback(value = false)
    public void save() {
        User user = User.builder()
            .name("hhh")
            .build();
        this.userDao.save(user);
    }

    @Test
    public void list() {
        System.out.println(this.userDao.findAll());
    }

    @Test
    public void test() {

        System.out.println(userDao.test("J% or 1 = 1"));
    }

}