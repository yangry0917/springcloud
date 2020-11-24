package com.cloud.eurekaconsumer.controller;


import com.cloud.eurekaconsumer.service.HelloRemoteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsumerController {
    @Autowired
    private HelloRemoteService helloRemote;

    @HystrixCommand(fallbackMethod = "serviceFallback")
    @RequestMapping("/hello")
    public String index() {
        return helloRemote.hello();
    }

    @HystrixCommand(fallbackMethod = "serviceFallback")
    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    public String serviceFallback() { // fallback方法
        return "error";
    }

}
