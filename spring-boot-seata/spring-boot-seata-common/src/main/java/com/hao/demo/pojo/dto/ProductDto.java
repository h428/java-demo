package com.hao.demo.pojo.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ProductDto implements Serializable {
    private Integer id;
    private String productCode;
    private String name;
    private Integer count;
}
