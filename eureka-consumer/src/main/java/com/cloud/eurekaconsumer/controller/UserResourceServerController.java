package com.cloud.eurekaconsumer.controller;


import com.cloud.eurekaconsumer.service.HelloRemoteService;
import com.cloud.eurekaconsumer.service.UserResourceServerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import model.request.QQUserQuery;
import model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserResourceServerController {
    @Autowired
    private UserResourceServerService userResourceServerService;


    @HystrixCommand(fallbackMethod = "serviceFallback")
    @RequestMapping("/getQQUserAuth")
    public String index() {
        QQUserQuery qqUserQuery = new QQUserQuery();
        ResponseResult responseResult = userResourceServerService.getQQUserAuth(qqUserQuery);
        return responseResult.toString();
    }

    public String serviceFallback() { // fallback方法
        return "error";
    }

}
