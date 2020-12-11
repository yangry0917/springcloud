package com.cloud.oauth2resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 微服务的Http安全配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 不允许跨域请求，/getQQUserAuth请求不需要认证，其它请求都需要认证
         */
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/getQQUserAuth").permitAll()
                .anyRequest()//.permitAll();
                .authenticated();
    }

    /**
     * 资源服务器的安全配置
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        /**
         * 设置令牌的存储方式
         */
        resources.tokenStore(tokenStore());
    }

    /**
     * 创建令牌存储对象
     */
    @Bean
    public TokenStore tokenStore() {
        /**
         * 使用JwtTokenStore时，必须注入一个JwtAccessTokenConverter，用于解析JWT令牌
         */
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 创建JWT令牌转换器
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        /**
         * 设置JWT令牌的签名key
         */
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("signingKey");
        return converter;
    }
}