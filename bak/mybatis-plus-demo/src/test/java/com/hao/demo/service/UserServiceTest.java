package com.hao.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hao.demo.BaseTest;
import com.hao.demo.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        assertNull(this.userService.getById(6));
        User user = User.builder().id(6L).name("hao").age(24).email("lyh@126.com").build();
        this.userService.save(user);
        assertNotNull(this.userService.getById(6));
    }

    @Test
    public void saveBatch() {
        assertNull(this.userService.getById(6));
        assertNull(this.userService.getById(7));
        User u1 = User.builder().id(6L).name("hao").age(24).email("lyh@126.com").build();
        User u2 = User.builder().id(7L).name("hao777").age(27).email("lyh@126.com").build();
        List<User> list = Lists.newArrayList(u1, u2);
        this.userService.saveBatch(list);
        assertNotNull(this.userService.getById(6));
        User u7 = this.userService.getById(7);
        assertNotNull(u7);
        assertEquals("hao777", u7.getName());
        assertEquals(7, this.userService.list().size());
    }

    /**
     * batchSize 表示分批次 insert，每批次插入 batchSize 条记录，共需要插入 size/batchSize 次
     */
    @Test
    public void saveBatchBySize() {
        assertNull(this.userService.getById(6));
        assertNull(this.userService.getById(7));
        User u6 = User.builder().id(6L).name("hao").age(24).email("lyh@126.com").build();
        User u7 = User.builder().id(7L).name("hao777").age(27).email("lyh@126.com").build();
        User u8 = User.builder().id(8L).name("hao777").age(27).email("lyh@126.com").build();
        User u9 = User.builder().id(9L).name("hao777").age(27).email("lyh@126.com").build();
        User u10 = User.builder().id(10L).name("hao777").age(27).email("lyh@126.com").build();
        List<User> list = Lists.newArrayList(u6, u7, u8, u9, u10);
        this.userService.saveBatch(list, 3);
        assertNotNull(this.userService.getById(6));
        assertNotNull(this.userService.getById(7));
        assertEquals(10, this.userService.list().size());
    }

    @Test
    public void saveOrUpdate() {

        // save
        assertNull(this.userService.getById(6));
        User u6 = User.builder().id(6L).name("hao").age(24).email("lyh@126.com").build();
        this.userService.save(u6);
        assertNotNull(this.userService.getById(6));

        // update
        User u5 = this.userService.getById(5);
        assertNotNull(u5);
        assertNotEquals("h5", u5.getName());
        User u5Update = User.builder().id(5L).name("h5").age(24).email("lyh@126.com").build();
        this.userService.saveOrUpdate(u5Update);
        assertEquals("h5", this.userService.getById(5).getName());
    }

    // 根据 updateWrapper 尝试更新，否继续执行 saveOrUpdate(T) 方法
    @Test
    public void saveOrUpdateByWrapper() {
        // saveWrapper
        QueryWrapper<User> saveWrapper = new QueryWrapper<>();
        saveWrapper.eq("name", "hao");

        // save
        assertNull(this.userService.getOne(saveWrapper));
        User user = User.builder().name("hao").age(11).build();
        this.userService.saveOrUpdate(user, saveWrapper);
        User saved = this.userService.getOne(saveWrapper);
        assertNotNull(saved);
        assertEquals(11, saved.getAge().intValue());

        // updateWrapper
        QueryWrapper<User> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("name", "Tom");
        // update
        User old = this.userService.getOne(updateWrapper);
        assertNotNull(old);
        assertEquals(28, old.getAge().intValue());
        user = User.builder().age(11).build();
        this.userService.saveOrUpdate(user, updateWrapper);
        User updated = this.userService.getOne(updateWrapper);
        assertNotNull(updated);
        assertEquals(11, saved.getAge().intValue());
    }

}