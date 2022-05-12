package com.hao.demo;

import com.hao.demo.assemble.EnableGroupConfiguration;
import com.hao.demo.copy.EnableMyAutoConfiguration;
import com.hao.outer.FirstConfiguration;
import com.hao.outer.SecondConfiguration;
import com.hao.outer.ThirdConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = SecondConfiguration.class)
@EnableMyAutoConfiguration
//@EnableGroupConfiguration
public class AutoAssembleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AutoAssembleApplication.class, args);
//        FirstConfiguration firstConfiguration = context.getBean(FirstConfiguration.class);
        SecondConfiguration secondConfiguration = context.getBean(SecondConfiguration.class);
        ThirdConfiguration thirdConfiguration = context.getBean(ThirdConfiguration.class);
//        System.out.println(firstConfiguration);
        System.out.println(secondConfiguration);
        System.out.println(thirdConfiguration);
    }

}
