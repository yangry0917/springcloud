package com.cloud.oauth2authserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.oauth2authserver.entity.CustomUser;
import com.cloud.oauth2authserver.service.UserService;
import model.ext.QQUserAuth;
import model.request.QQUserQuery;
import model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    /**
     * 根据用户名获取认证用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("UserDetailsService没有接收到用户账号");
        } else {
            QQUserQuery query = new QQUserQuery();
            query.setUsername(username);

            /**
             * 根据用户名，查询用户信息
             */
            ResponseResult result = userService.getQQUserAuth(query);
            if (result == null) {
                throw new InternalAuthenticationServiceException("UserDetailsService查询用户账号信息失败");
            } else if (result.get("code").equals("300")) {
                throw new UsernameNotFoundException(String.format("用户'%s'不存在", username));
            } else {
                QQUserAuth user = JSON.parseObject(JSON.toJSONString(result.get("QQUserAuth")), QQUserAuth.class);
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for (String role : user.getRoles()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role));
                }

                /**
                 * 创建认证用户对象的第一种方式：
                 * 使用Spring Security自带的User类创建一个只包含用户名、密码、权限的认证用户对象
                 * 如果创建JWT令牌时，不需要在JwtTokenEnhancer中往生成的令牌中添加用户的其它额外信息，则可以使用这种方式即可
                 */
                return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

                /**
                 * 创建认证用户对象的第二种方式：
                 * 使用自定义的CustomUser类创建一个包含用户名、密码、权限、用户其它额外信息的认证用户对象
                 * 如果创建JWT令牌时，需要在JwtTokenEnhancer中往生成的令牌中添加用户的其它额外信息，则可以使用这种方式即可
                 */
                /*Map<String, Object> additionalUserInfo = new HashMap<>();
                additionalUserInfo.put("userId", user.getId());
                additionalUserInfo.put("username", user.getUsername());
                return new CustomUser(user.getUsername(), user.getPassword(), grantedAuthorities, additionalUserInfo);*/
            }
        }
    }
}