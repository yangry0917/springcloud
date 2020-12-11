package com.cloud.oauth2authserver.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义的认证用户实体类
 */
public class CustomUser extends User {

    /**
     * 用户的其它额外信息
     */
    private Map<String, Object> additionalUserInfo;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Map<String, Object> additionalUserInfo) {
        super(username, password, authorities);
        this.additionalUserInfo = additionalUserInfo;
    }

    public Map<String, Object> getAdditionalUserInfo() {
        return additionalUserInfo;
    }

    public void setAdditionalUserInfo(Map<String, Object> additionalUserInfo) {
        this.additionalUserInfo = additionalUserInfo;
    }
}
