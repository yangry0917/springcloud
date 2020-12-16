package com.cloud.oauth2authserver.authentication;

import com.cloud.oauth2authserver.constant.ConfigConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的用户认证成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;


    public MyAuthenticationSuccessHandler() {
        /**
         * 指定默认登录成功请求的URL和是否总是使用默认登录成功请求的URL
         * 注意：自定义的认证成功处理器，如果不指定，默认登录成功请求的URL是"/"
         */
        this.setDefaultTargetUrl(ConfigConstant.DEFAULT_LOGIN_SUCCESSFUL_REQUEST_URL);
        this.setAlwaysUseDefaultTargetUrl(ConfigConstant.ALWAYS_USE_DEFAULT_LOGIN_SUCCESSFUL_REQUEST_URL);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException, IOException, ServletException {
        /**
         * 如果配置ConfigConstant.LOGIN_RESPONSE_TYPE="JSON"，则返回JSON，否则使用页面跳转
         */
        if("JSON".equalsIgnoreCase(ConfigConstant.LOGIN_RESPONSE_TYPE)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
