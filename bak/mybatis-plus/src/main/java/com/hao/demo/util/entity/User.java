package com.hao.demo.util.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String name;

}
