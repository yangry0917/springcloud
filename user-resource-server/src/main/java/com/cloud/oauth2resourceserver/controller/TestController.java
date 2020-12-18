package com.cloud.oauth2resourceserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/getExtraInfo")
    public Map<String,Object> getExtraInfo(Authentication auth){
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails)auth.getDetails();
        Map<String,Object> map = (Map<String,Object>) oauthDetails.getDecodedDetails();
        return map;
    }
}
