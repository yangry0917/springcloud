package com.cloud.oauth2authserver.config;

import com.cloud.oauth2authserver.authentication.MyAuthenticationFailureHandler;
import com.cloud.oauth2authserver.authentication.MyAuthenticationSuccessHandler;
import com.cloud.oauth2authserver.authentication.MyUsernamePasswordAuthenticationFilter;
import com.cloud.oauth2authserver.authentication.MyUsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 自定义的用户名密码认证配置类
 */
@Component
public class MyUsernamePasswordAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    MyUsernamePasswordAuthenticationProvider myUsernamePasswordAuthenticationProvider;

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MyUsernamePasswordAuthenticationFilter authenticationFilter = new MyUsernamePasswordAuthenticationFilter();

        /**
         * 自定义用户认证处理逻辑时，需要指定AuthenticationManager，否则无法认证
         */
        authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        /**
         * 自定义用户认证处理逻辑时，需要指定RememberMeServices，否则自定义用户认证的"记住我"功能异常
         */
        //authenticationFilter.setRememberMeServices(http.getSharedObject(RememberMeServices.class));

        /**
         * 指定自定义的认证成功和失败的处理器
         */
        //authenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        //authenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        /**
         * 把自定义的用户名密码认证过滤器和处理器添加到UsernamePasswordAuthenticationFilter过滤器之前
         */
        http
                .authenticationProvider(myUsernamePasswordAuthenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
