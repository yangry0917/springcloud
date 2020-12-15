package com.cloud.oauth2resourceserver.service;

import model.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    /**
     * 根据用户名获取认证用户信息
     */
    User getUserAuthByUsername(String username);

    /**
     * 根据用户名获取用户详细信息
     */
    User getUserByUsername(String username);
}
