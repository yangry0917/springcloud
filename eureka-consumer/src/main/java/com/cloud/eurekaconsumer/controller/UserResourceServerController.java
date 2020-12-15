package com.cloud.eurekaconsumer.controller;


import com.cloud.eurekaconsumer.service.UserResourceServerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import model.request.UserQuery;
import model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserResourceServerController {
    @Autowired
    private UserResourceServerService userResourceServerService;


    @HystrixCommand(fallbackMethod = "serviceFallback")
    @RequestMapping("/getUserAuth")
    public String index() {
        UserQuery qqUserQuery = new UserQuery();
        ResponseResult responseResult = userResourceServerService.getUserAuth(qqUserQuery);
        return responseResult.toString();
    }

    public String serviceFallback() { // fallback方法
        return "error";
    }

}
