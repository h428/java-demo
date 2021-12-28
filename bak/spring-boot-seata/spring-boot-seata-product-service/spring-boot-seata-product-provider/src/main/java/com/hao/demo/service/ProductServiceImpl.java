package com.hao.demo.service;

import com.hao.demo.constant.ResCode;
import com.hao.demo.dal.mapper.ProductMapper;
import com.hao.demo.pojo.dto.ProductDto;
import com.hao.demo.pojo.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ObjectResponse decreaseProduct(ProductDto productDto) {
        ObjectResponse response=new ObjectResponse();
        try {
            int repo = productMapper.decreaseProduct(productDto.getProductCode(), productDto.getCount());
            if(repo>0){
                response.setMsg(ResCode.SUCCESS.getMessage());
                response.setCode(ResCode.SUCCESS.getCode());
                return response;
            }
            response.setMsg(ResCode.FAILED.getMessage());
            response.setCode(ResCode.FAILED.getCode());
        }catch (Exception e){
            log.error("decreaseProduct Occur Exception:"+e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage()+"-"+e.getMessage());
        }
        return response;
    }
}
