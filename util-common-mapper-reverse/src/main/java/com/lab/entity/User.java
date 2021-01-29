package com.lab.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "user_pass")
    private String userPass;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 身高
     */
    private Double height;

    /**
     * 头像
     */
    private String profile;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 注册时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 上次更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 上次登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 删除标记
     */
    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public User(Long id, String email, String userName, String userPass, String salt, Integer age, Double height, String profile, Integer status, Date registerTime, Date lastUpdateTime, Date loginTime, Boolean deleted) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.userPass = userPass;
        this.salt = salt;
        this.age = age;
        this.height = height;
        this.profile = profile;
        this.status = status;
        this.registerTime = registerTime;
        this.lastUpdateTime = lastUpdateTime;
        this.loginTime = loginTime;
        this.deleted = deleted;
    }

    public User() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return user_pass - 密码
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * 设置密码
     *
     * @param userPass 密码
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    /**
     * 获取盐值
     *
     * @return salt - 盐值
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐值
     *
     * @param salt 盐值
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取身高
     *
     * @return height - 身高
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置身高
     *
     * @param height 身高
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取头像
     *
     * @return profile - 头像
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 设置头像
     *
     * @param profile 头像
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取注册时间
     *
     * @return register_time - 注册时间
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置注册时间
     *
     * @param registerTime 注册时间
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取上次更新时间
     *
     * @return last_update_time - 上次更新时间
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置上次更新时间
     *
     * @param lastUpdateTime 上次更新时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取上次登录时间
     *
     * @return login_time - 上次登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置上次登录时间
     *
     * @param loginTime 上次登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取删除标记
     *
     * @return deleted - 删除标记
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标记
     *
     * @param deleted 删除标记
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}