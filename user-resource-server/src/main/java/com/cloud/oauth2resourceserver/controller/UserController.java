package com.cloud.oauth2resourceserver.controller;

import com.cloud.oauth2resourceserver.service.UserService;
import model.domain.User;

import model.request.UserQuery;
import model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService UserServiceImpl;

    /**
     * 根据用户名获取认证用户信息（不要求用户已认证，即不要求有访问令牌）
     */
    @PostMapping("/getUserAuth")
    public ResponseResult getUserAuth(@RequestBody UserQuery query) {
        User user = null;
        if(query == null) {
            return ResponseResult.getFailureResult("300", "没有接收到用户信息");
        } else if(!StringUtils.isEmpty(query.getUsername())) {
            user = UserServiceImpl.getUserAuthByUsername(query.getUsername());
        }
        if(user == null) {
            return ResponseResult.getFailureResult("300", "没有查询到该用户信息");
        } else {
            return ResponseResult.getSuccessResult().put("UserAuth", user);
        }
    }

    /**
     * 已认证用户查询自己的用户信息，不允许查询他人的用户信息（要求用户已认证，即要求有合法的访问令牌）
     */
    @PostMapping("/api/getQQUser")
    @PreAuthorize("principal.equals(#query.getUsername())")
    public ResponseResult getQQUser(@RequestBody UserQuery query) {
        User user = null;
        if(query == null) {
            return ResponseResult.getFailureResult("300", "没有接收到用户信息");
        } else if(!StringUtils.isEmpty(query.getUsername())) {
            user = UserServiceImpl.getUserByUsername(query.getUsername());
        }
        if(user == null) {
            return ResponseResult.getFailureResult("300", "没有获取到该用户信息");
        } else {
            return ResponseResult.getSuccessResult().put(user);
        }
    }
}
