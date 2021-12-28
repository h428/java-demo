package com.hao.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
