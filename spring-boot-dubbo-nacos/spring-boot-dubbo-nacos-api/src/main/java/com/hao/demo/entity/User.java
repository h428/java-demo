package com.hao.demo.entity;

import java.io.Serializable;
import java.util.Random;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {



    private Long id;
    private String username;
    private String password;


    private static final Random random = new Random();

    public static User generate(Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("user" + id);
        user.setPassword("pass" + id);
        return user;
    }

}
