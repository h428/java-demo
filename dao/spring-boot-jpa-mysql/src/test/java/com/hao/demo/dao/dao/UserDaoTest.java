package com.hao.demo.dao.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.hao.demo.dao.BaseTest;
import com.hao.demo.dao.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseTest {


    @Autowired
    private UserDao userDao;

    private static Random random = new Random();

    // CrudRepository 接口

    private static long nextLong() {
        return random.nextLong() >>> 1;
    }

    private static int nextInt() {
        return random.nextInt() >>> 1;
    }

    private static User randomOne() {
        long id = nextLong();
        return User.builder()
            .id(id)
            .userName("name" + id)
            .userPass("pass" + id)
            .email("email" + id)
            .salt("salt" + id)
            .build();
    }

    @Test
    public void save() {
        // 增加
        assertEquals(7, this.userDao.count());
        this.userDao.save(randomOne());
        assertEquals(8, this.userDao.count());

        // 修改
        User cat = this.userDao.getOne(1L);
        assertEquals("cat", cat.getUserName());
        cat.setUserName("dog");
        assertEquals("dog", this.userDao.getOne(1L).getUserName());
    }

    @Test
    public void saveAll() {
        assertEquals(7, this.userDao.count());

        int cnt = random.nextInt(10) + 1;

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            userList.add(randomOne());
        }
        this.userDao.saveAll(userList);
        assertEquals(7 + cnt, this.userDao.count());
    }


    @Test
    public void test() {
        System.out.println((13.10 + 72.28) / 2);
    }



    @Test
    public void findAll() {
        assertEquals(7, this.userDao.findAll().size());
    }

    @Test
    public void getOne() {
        assertNotNull(this.userDao.getOne(1L));
        assertNotNull(this.userDao.getOne(2L));
    }

}