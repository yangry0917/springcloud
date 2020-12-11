package com.cloud.eurekaconsumer.service;

import com.cloud.eurekaconsumer.fallback.FeignServiceFallback;
import model.request.QQUserQuery;
import model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "user-resource-server",fallback = FeignServiceFallback.class)
public interface UserResourceServerService {

    /**
     * 调用USER-RESOURCE-SERVER微服务的getQQUserAuth接口查询用户信息
     */
    @RequestMapping(value = "/getQQUserAuth")
    ResponseResult getQQUserAuth(@RequestBody QQUserQuery query);


}


