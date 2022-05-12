package com.hao.demo;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HelloComponent {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void test() {
        System.out.println("---------" + this.environment.getProperty("test"));
    }
}
