package com.hao.demo;

import com.hao.demo.dao.CustomerDao;
import com.hao.demo.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class SpringBootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerDao customerDao) {
        return (args) -> {
            // save a few customers
            customerDao.save(new Customer("Jack", "Bauer"));
            customerDao.save(new Customer("Chloe", "O'Brian"));
            customerDao.save(new Customer("Kim", "Bauer"));
            customerDao.save(new Customer("David", "Palmer"));
            customerDao.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customerDao.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = customerDao.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            customerDao.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : customerDao.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            log.info("");
        };
    }

}
