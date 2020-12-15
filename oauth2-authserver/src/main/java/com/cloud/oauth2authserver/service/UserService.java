package com.cloud.oauth2authserver.service;


import com.cloud.oauth2authserver.service.hystrix.UserServiceFallbackImpl;

import model.request.UserQuery;
import model.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 使用OpenFeign获取用户信息接口
 */
@FeignClient(value = "user-resource-server", fallback = UserServiceFallbackImpl.class)
public interface UserService {

    /**
     * 调用USER-RESOURCE-SERVER微服务的getQQUserAuth接口查询用户信息
     */
    @PostMapping("/getUserAuth")
    ResponseResult getUserAuth(@RequestBody UserQuery query);


}
