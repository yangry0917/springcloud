package com.cloud.oauth2authserver.authentication;

import com.cloud.oauth2authserver.authentication.MyUsernamePasswordAuthenticationToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现获取用户认证信息、封装AuthenticationToken对象、调用AuthenticationManager对AuthenticationToken进行认证等逻辑
 */
public class MyUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private boolean postOnly = true;

    public MyUsernamePasswordAuthenticationFilter() {
        /**
         * 设置该过滤器对POST请求/login进行拦截
         */
        super(new AntPathRequestMatcher("/login", "POST"));
    }
    //https://blog.csdn.net/weixin_44516305/article/details/88868791
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(this.postOnly && !request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }else{
            /**
             * 从http请求中获取用户输入的用户名和密码信息
             * 这里接收的是form形式的参数，如果要接收json形式的参数，修改这里即可
             */
            String username = this.obtainUsername(request);
            String password = this.obtainPassword(request);
            if(StringUtils.isEmpty(username) && StringUtils.isEmpty(password)){
                throw new UsernameNotFoundException("MyUsernamePasswordAuthenticationFilter获取用户认证信息失败");

            }
            /**
             * 使用用户输入的用户名和密码信息创建一个未认证的用户认证Token
             */
            MyUsernamePasswordAuthenticationToken authRequest = new MyUsernamePasswordAuthenticationToken(username, password);
            /**
             * 设置一些详情信息
             */
            this.setDetails(request, authRequest);
            /**
             * 通过AuthenticationManager调用相应的AuthenticationProvider进行用户认证
             */
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }

    protected void setDetails(HttpServletRequest request, MyUsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public String getUsernameParameter() {
        return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }
}
