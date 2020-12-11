package com.cloud.oauth2authserver.service.hystrix;

import com.cloud.oauth2authserver.service.UserService;
import model.request.QQUserQuery;
import model.response.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * 使用OpenFeign获取用户信息接口的服务熔断、降级实现类
 */
@Service
public class UserServiceFallbackImpl implements UserService {

    @Override
    public ResponseResult getQQUserAuth(QQUserQuery query) {
        System.out.println("这里进行相应的记录");
        return null;
    }

}
