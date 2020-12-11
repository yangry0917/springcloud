package com.cloud.oauth2resourceserver.service;

import model.domain.QQUser;
import model.ext.QQUserAuth;

public interface UserService {

    /**
     * 根据用户名获取认证用户信息
     */
    QQUserAuth getQQUserAuthByUsername(String username);

    /**
     * 根据用户名获取用户详细信息
     */
    QQUser getQQUserByUsername(String username);
}
