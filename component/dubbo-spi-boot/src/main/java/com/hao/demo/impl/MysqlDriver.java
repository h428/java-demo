package com.hao.demo.impl;

import com.hao.demo.spi.Driver;

public class MysqlDriver implements Driver {

    @Override
    public String connect() {
        return "will connect to mysql database...";
    }
}
