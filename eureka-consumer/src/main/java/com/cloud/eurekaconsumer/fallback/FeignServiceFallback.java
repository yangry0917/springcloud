package com.cloud.eurekaconsumer.fallback;

import com.cloud.eurekaconsumer.service.HelloRemoteService;



public class FeignServiceFallback implements HelloRemoteService {


    @Override
    public String hello() {
        return"hello服务请求失败";
    }

    @Override
    public String hello(String name) {
        return "服务请求失败，请求参数为name:"+name;
    }
}
