package com.cloud.eurekaconsumer.service;

import com.cloud.eurekaconsumer.fallback.FeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "eureka-client", fallback = FeignServiceFallback.class)
public interface HelloRemoteService {
    @RequestMapping(value = "/client/hello")
    String hello();

    @RequestMapping(value = "/client/hello2")
    String hello(@RequestParam(value = "name") String name);
}


