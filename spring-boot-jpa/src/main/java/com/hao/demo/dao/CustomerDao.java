package com.hao.demo.dao;

import com.hao.demo.entity.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);

}
