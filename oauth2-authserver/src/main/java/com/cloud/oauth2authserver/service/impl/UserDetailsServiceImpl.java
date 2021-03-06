package com.cloud.oauth2authserver.service.impl;

import com.alibaba.fastjson.JSON;
import com.cloud.oauth2authserver.service.UserService;
import com.cloud.oauth2authserver.util.RedisUtil;
import model.domain.Role;
import org.springframework.security.core.userdetails.User;
import model.request.UserQuery;
import model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 根据用户名获取认证用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("UserDetailsService没有接收到用户账号");
        } else {
            UserQuery query = new UserQuery();
            query.setUsername(username);

            /**
             * 根据用户名，查询用户信息
             */
            ResponseResult result = userService.getUserAuth(query);
            if (result == null) {
                throw new InternalAuthenticationServiceException("UserDetailsService查询用户账号信息失败");
            } else if (result.get("code").equals("300")) {
                throw new UsernameNotFoundException(String.format("用户'%s'不存在", username));
            } else {
                model.domain.User user = JSON.parseObject(JSON.toJSONString(result.get("UserAuth")), model.domain.User.class);
                Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
                for (Role role : user.getRoles()) {
                    String roleName = role.getName();
                    grantedAuthorities.add(new SimpleGrantedAuthority(roleName));
                }
                /**
                 * 将查询到用户的信息保存进redis,可以供gateway等其他组件获取信息
                 */
                Map<String,Object> redisMap = new HashMap<String,Object>(16);
                redisMap.put("authority",grantedAuthorities);
                redisUtil.setCacheMap(username,redisMap);

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