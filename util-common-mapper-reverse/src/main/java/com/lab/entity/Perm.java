package com.lab.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Perm implements Serializable {
    @Id
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public Perm(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Perm() {
        super();
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取权限名称
     *
     * @return name - 权限名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置权限名称
     *
     * @param name 权限名称
     */
    public void setName(String name) {
        this.name = name;
    }
}