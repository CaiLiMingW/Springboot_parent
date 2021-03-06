package org.clm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;
import utils.JwtUtil;

/**
 * @author Ccc
 * @date 2018/11/15 0015 下午 7:56
 */
@SpringBootApplication
@EnableEurekaClient
public class SplitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SplitApplication.class,args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }

    @Bean
    public JwtUtil jwtUtil(){return new JwtUtil();}
}
