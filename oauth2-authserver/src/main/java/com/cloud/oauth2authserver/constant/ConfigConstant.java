package com.cloud.oauth2authserver.constant;

/**
 * 配置常量
 */
public class ConfigConstant {

    /**
     * 请求登录页面的URL
     */
    public static final String REQUEST_LOGIN_PAGE_URL = "/login";

    /**
     * 登录表单的URL
     */
    public static final String LOGIN_FORM_SUBMIT_URL = "/login";

    /**
     * 默认登录成功请求的URL
     */
    public static final String DEFAULT_LOGIN_SUCCESSFUL_REQUEST_URL = "/toHome";

    /**
     * 是否总是使用默认登录成功请求的URL
     */
    public static final boolean ALWAYS_USE_DEFAULT_LOGIN_SUCCESSFUL_REQUEST_URL = true;

    /**
     * 用户认证成功后返回结果类型：
     * JSON：返回Json类型结果
     * REDIRECT：页面跳转类型
     */
    public static final String LOGIN_RESPONSE_TYPE = "REDIRECT";

    /**
     * 退出登录的URL
     */
    public static final String LOGOUT_URL = "/logout";

    /**
     * 退出登录成功后请求的URL
     */
    public static final String LOGOUT_SUCCESSFUL_REQUEST_URL = "/login";

    /**
     * "记住我"功能的有效时间
     */
    public static final int REMEMBER_ME_SECOND = 3600;

    /**
     * 不需要认证就允许访问的URL(通常是一些公共资源和静态资源)
     */
    public static final String[] PERMIT_ALL_REQUEST_URL_ARRAY = {"/toHome", "/toAbout"};

}
