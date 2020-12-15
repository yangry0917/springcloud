package com.cloud.oauth2resourceserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 用户信息资源服务器微服务启动类
 */
//@ComponentScan("com.cloud.oauth2resourceserver.*")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableEurekaClient
@MapperScan("com.cloud.oauth2resourceserver.dao")
@SpringBootApplication(scanBasePackages = "com.cloud.oauth2resourceserver")
public class Oauth2ResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourceServerApplication.class, args);
    }

}
