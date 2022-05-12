package com.hao.demo.dao;

import static org.junit.Assert.*;

import com.hao.demo.BaseTest;
import com.hao.demo.entity.Customer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerDaoTest extends BaseTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void findAll() {
        int cnt = 0;
        for (Customer customer : customerDao.findAll()) {
            ++cnt;
        }
        assertEquals(5, cnt);
    }

    @Test
    public void test() {


    }

}