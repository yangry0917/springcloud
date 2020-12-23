package com.cloud.oauth2resourceserver.controller;

import model.pojo.JWT;
import model.response.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;


/*https://blog.csdn.net/qq_41896122/article/details/104023251*/
/**
 * @ClassName BlogCallBackController
 * @Description 接受认证成功的回调地址
 * @Author wsail
 * @Date 2019/12/27 15:16
 **/
@RestController
@RequestMapping("/oauth")
public class OauthCallBackController {

    @Value("${security.oauth2.client.access-token-uri}")
    private String oauth_token;

    @Value("${security.oauth2.client.registered-redirect-uri}")
    private String redirect_uri;

    @Value("${security.oauth2.client.authorized-grant-types}")
    private String grant_type;

    @Value("${security.oauth2.client.client-id}")
    private String client_id;

    @Value("${security.oauth2.client.client-secret}")
    private String client_secret;

    private final static String CONTENT_TYPE = "Content-Type";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/callback")
    public ResponseResult authorizationSuccessCallBack(String code, HttpServletRequest request){
        System.out.println(request.getMethod());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("client_id",client_id);
        map.add("client_secret",client_secret);
        map.add("code",code);
        map.add("redirect_uri",redirect_uri);
        map.add("grant_type",grant_type);
        httpHeaders.add(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(map,httpHeaders);
        ResponseEntity<JWT> responseEntity = restTemplate.postForEntity(oauth_token,httpEntity,JWT.class);
        JWT jwt = responseEntity.getBody();
        ResponseResult responseResult = new ResponseResult();
        responseResult.put(jwt);
        return responseResult;
    }
}














