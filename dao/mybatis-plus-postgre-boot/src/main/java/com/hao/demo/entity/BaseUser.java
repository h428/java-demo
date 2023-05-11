package com.hao.demo.entity;

import lombok.Data;

@Data
public class BaseUser {
    private Long id;
    private String email;
    private String userName;
    private String userPass;
    private String salt;
    private String name;
    private String phone;
    private String wechatNo;
    private String avatar;
    private Long createTime;
    private Long updateTime;
    private Long lastLoginTime;
    private Integer status;
    private Boolean allowAdd;
    private Boolean allowAuth;
    private Boolean allowMessage;
    private Boolean deleted;
    private Long deleteTime;
}
