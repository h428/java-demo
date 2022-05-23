package demo;

import com.hao.demo.api.AnotherService;
import com.hao.demo.api.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnotherConsumerApplication {

    /**
     * 采用注册中心后，则无需配置 url，会自动从注册中心获取
     */
    @DubboReference
    private AnotherService anotherService;

    public static void main(String[] args) {
        SpringApplication.run(AnotherConsumerApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            System.out.println(anotherService.getInt());
        };
    }


}
