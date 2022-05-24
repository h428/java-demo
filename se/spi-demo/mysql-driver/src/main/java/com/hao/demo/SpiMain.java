package com.hao.demo;

import com.hao.demo.api.Driver;
import java.util.ServiceLoader;

public class SpiMain {

    public static void main(String[] args) {
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        for (Driver driver : serviceLoader) {
            System.out.println(driver);
        }
    }

}
