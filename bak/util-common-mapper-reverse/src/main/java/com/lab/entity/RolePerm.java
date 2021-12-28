package com.lab.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "role_perm")
public class RolePerm implements Serializable {
    /**
     * 角色 id
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限 id
     */
    @Id
    @Column(name = "perm_id")
    private Integer permId;

    private static final long serialVersionUID = 1L;

    public RolePerm(Integer roleId, Integer permId) {
        this.roleId = roleId;
        this.permId = permId;
    }

    public RolePerm() {
        super();
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

    /**
     * 获取权限 id
     *
     * @return perm_id - 权限 id
     */
    public Integer getPermId() {
        return permId;
    }

    /**
     * 设置权限 id
     *
     * @param permId 权限 id
     */
    public void setPermId(Integer permId) {
        this.permId = permId;
    }
}