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
     *
     */
    public static final String  LOGIN_RESPONSE_TYPE="JSON";

}
