package com.hao.demo.shiro.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.RandomUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    private int randomInt() {
        return RandomUtil.randomInt() >>> 1;
    }

    @GetMapping
    @RequiresPermissions("data:list")
    public List<String> list() {
        final ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strList.add(String.valueOf(randomInt()));
        }
        return strList;
    }

    @GetMapping("random")
    public Integer random() {
        return randomInt();
    }

}
