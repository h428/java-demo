package com.hao.demo.dao.dao;

import com.hao.demo.dao.base.BaseDao;
import com.hao.demo.dao.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User, Long> {

}
