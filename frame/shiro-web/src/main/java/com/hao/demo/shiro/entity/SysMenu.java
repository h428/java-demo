package com.hao.demo.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class SysMenu {

    private Long id;

    private Long parentId;

    private String name;

    private String url;

    private Integer type;

    private String perms;

    private String icon;

    private Integer order;
}
