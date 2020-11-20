package com.cloud.eurekaclient.test;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client")
public class HelloWorldController {

    @Value("${server.port}")
    String port;

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    public String index(){
        StringBuilder sb = new StringBuilder(" client Hello World "+port+" 端口号为您服务！<br>");
        return sb.toString();
    }

    @RequestMapping("/hello2")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is first message";
    }
}
