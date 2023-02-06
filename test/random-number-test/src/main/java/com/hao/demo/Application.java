package com.hao.demo;

import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hao
 * @date 2023/2/6
 */
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static Random RANDOM = new Random();

    @RequestMapping
    public int random() {
        return RANDOM.nextInt() >>> 1;
    }

}
