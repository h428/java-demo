package com.demo.service;

import com.demo.entity.Product;
import com.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    // create index

    // delete index

    // init data

    // clear data

    // get a row
    public Product get(Long id) {
        return this.productRepository.findById(id).get();
    }

    // update row

    // delete row

    // search by key

    // search by key with page

    // other find methods

}
