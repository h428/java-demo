package com.hao.demo.generate.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao
 * @since 2022-05-11
 */
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String userPass;

    @ApiModelProperty("盐值")
    private String salt;

    @ApiModelProperty("身高")
    private Double height;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("评分")
    private Integer score;

    @ApiModelProperty("出生日期")
    private LocalDate birthday;

    @ApiModelProperty("注册时间")
    private LocalDateTime createTime;

    @ApiModelProperty("上次更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("上次登录时间")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("角色 id")
    private Long roleId;

    @ApiModelProperty("删除标记")
    private Boolean deleted;

    @ApiModelProperty("删除时间")
    private LocalDateTime deleteTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", email=" + email +
        ", userName=" + userName +
        ", userPass=" + userPass +
        ", salt=" + salt +
        ", height=" + height +
        ", avatar=" + avatar +
        ", score=" + score +
        ", birthday=" + birthday +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", lastLoginTime=" + lastLoginTime +
        ", status=" + status +
        ", roleId=" + roleId +
        ", deleted=" + deleted +
        ", deleteTime=" + deleteTime +
        "}";
    }
}
