package com.hao.demo.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;

    private String name;

    public static User randomOne(Long id) {
        return new User().setId(id).setName("name" + id);
    }

    public static User randomOne() {
        Long id = (long)(Math.random() * 1000L);
        return randomOne(id);
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

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
}
