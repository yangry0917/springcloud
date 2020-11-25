package com.cloud.springcloudconfigclient.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RefreshScope
class HelloController {
    @Value("${neo.hello}")
    private String port;

    @RequestMapping("/hello")
    public String from() {
        return "this server:"+this.port;
    }
}
