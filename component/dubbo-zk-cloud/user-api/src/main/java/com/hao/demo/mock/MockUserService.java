package com.hao.demo.mock;

import com.hao.demo.api.UserService;
import com.hao.demo.entity.User;

public class MockUserService implements UserService {

    public static final User MOCK_USER = new User().setId(-1L).setName("Mock User");

    @Override
    public User getById(Long id) {
        return MOCK_USER;
    }

    @Override
    public User randomOne() {
        return MOCK_USER;
    }
}
