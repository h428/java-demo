package com.hao.demo.entity;

import java.io.Serializable;
import java.util.Random;

/**
 * 使用 Dubbo 通信的实体必须实现 Serializable 接口
 */
public class User implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

    private static final Random random = new Random();

    public static User randomOne() {
        long n = random.nextLong();
        return randomOne(n);
    }

    public static User randomOne(Long id) {
        return new User()
            .setId(id)
            .setName("name" + id);
    }
}
