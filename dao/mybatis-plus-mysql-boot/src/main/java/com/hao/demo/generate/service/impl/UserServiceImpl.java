package com.hao.demo.generate.service.impl;

import com.hao.demo.generate.entity.User;
import com.hao.demo.generate.mapper.UserMapper;
import com.hao.demo.generate.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao
 * @since 2022-05-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
