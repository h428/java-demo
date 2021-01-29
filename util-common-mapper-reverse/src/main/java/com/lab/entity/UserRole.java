package com.lab.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "user_role")
public class UserRole implements Serializable {
    /**
     * 用户 id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色 id
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole() {
        super();
    }

    /**
     * 获取用户 id
     *
     * @return user_id - 用户 id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户 id
     *
     * @param userId 用户 id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取角色 id
     *
     * @return role_id - 角色 id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色 id
     *
     * @param roleId 角色 id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}