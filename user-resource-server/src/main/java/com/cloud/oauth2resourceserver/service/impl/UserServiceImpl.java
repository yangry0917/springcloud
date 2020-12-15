package com.cloud.oauth2resourceserver.service.impl;

//import com.cloud.oauth2resourceserver.entity.User;
import com.cloud.oauth2resourceserver.dao.UserMapper;
import com.cloud.oauth2resourceserver.service.UserService;
import model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserAuthByUsername(String username) {
        int a=  userMapper.test();
        User user = userMapper.loadUserByUsername(username);
        return user;

        /*if (username.equals("admin")) {
            UserAuth user = new UserAuth();
            user.setId(1L);
            user.setUsername(username);
            *//**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             *//*
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_USER");
            user.setRoles(roles);
            return user;
        } else if (username.equals("employee")) {
            UserAuth user = new UserAuth();
            user.setId(2L);
            user.setUsername(username);
            *//**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             *//*
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            List<String> roles = new ArrayList<>();
            roles.add("ROLE_EMPLOYEE");
            user.setRoles(roles);
            return user;
        }*/
    }



    @Override
    public User getUserByUsername(String username) {
        if (username.equals("admin")) {
            User user = new User();
            user.setId(1L);
            user.setUsername(username);
            /**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             */
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            /*user.setNickname("管理员");
            user.setName("张三");
            user.setGender(true);
            user.setCity("China");
            user.setCity("WuHan");
            user.setMobile("17371580000");
            user.setEmail("3612450000@qq.com");*/
            return user;
        } else if (username.equals("employee")) {
            User user = new User();
            user.setId(2L);
            user.setUsername(username);
            /**
             * 密码为123（通过BCryptPasswordEncoderl加密后的密文）
             */
            user.setPassword("$2a$10$U6g06YmMfRJXcNfLP28TR.xy21u1A5kIeY/OZMKBDVMbn7PGJiaZS");
            /*user.setNickname("普通员工");
            user.setName("李四");
            user.setGender(true);
            user.setCity("China");
            user.setCity("WuHan");
            user.setMobile("17371580000");
            user.setEmail("3612450000@qq.com");*/
            return user;
        }
        return null;
    }
}
