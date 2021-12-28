package com.hao.demo.dao;

import com.hao.demo.entity.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("from User where name like ?1")
    List<User> test(String pattern);

}
