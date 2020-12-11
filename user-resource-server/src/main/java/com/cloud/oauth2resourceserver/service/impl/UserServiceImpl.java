package com.cloud.oauth2resourceserver.service.impl;

import com.cloud.oauth2resourceserver.service.UserService;
import model.domain.QQUser;
import model.ext.QQUserAuth;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public QQUserAuth getQQUserAuthByUsername(String username) {
        if (username.equals("admin")) {
            QQUserAuth user = new QQUserAuth();
            user.setId(1L);
            user.setUsername(username);
            /**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             */
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_USER");
            user.setRoles(roles);
            return user;
        } else if (username.equals("employee")) {
            QQUserAuth user = new QQUserAuth();
            user.setId(2L);
            user.setUsername(username);
            /**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             */
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_EMPLOYEE");
            user.setRoles(roles);
            return user;
        }
        return null;
    }

    @Override
    public QQUser getQQUserByUsername(String username) {
        if (username.equals("admin")) {
            QQUser user = new QQUser();
            user.setId(1L);
            user.setUsername(username);
            /**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             */
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            user.setNickname("管理员");
            user.setName("张三");
            user.setGender(true);
            user.setCity("China");
            user.setCity("WuHan");
            user.setMobile("17371580000");
            user.setEmail("3612450000@qq.com");
            return user;
        } else if (username.equals("employee")) {
            QQUser user = new QQUser();
            user.setId(2L);
            user.setUsername(username);
            /**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             */
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            user.setNickname("普通员工");
            user.setName("李四");
            user.setGender(true);
            user.setCity("China");
            user.setCity("WuHan");
            user.setMobile("17371580000");
            user.setEmail("3612450000@qq.com");
            return user;
        }
        return null;
    }
}
