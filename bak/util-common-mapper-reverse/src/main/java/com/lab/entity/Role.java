package com.lab.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Role implements Serializable {
    @Id
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    private static final long serialVersionUID = 1L;

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
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
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }
}