package com.cloud.oauth2authserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.cloud.oauth2authserver.*")
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@MapperScan("com.cloud.oauth2authserver.mapper")
public class Oauth2AuthserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthserverApplication.class, args);
    }

}
