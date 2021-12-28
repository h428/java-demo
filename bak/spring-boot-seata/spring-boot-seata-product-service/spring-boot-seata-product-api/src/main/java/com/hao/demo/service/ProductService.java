package com.hao.demo.service;

import com.hao.demo.pojo.dto.ProductDto;
import com.hao.demo.pojo.response.ObjectResponse;

public interface ProductService {
    /**
     * 扣减库存
     */
    ObjectResponse decreaseProduct(ProductDto productDto);
}
