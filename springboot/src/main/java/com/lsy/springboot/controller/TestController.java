package com.lsy.springboot.controller;

import com.lsy.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 加入spring容器管理，单例
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test(@RequestParam(name = "param",required = false,defaultValue = "default") String param){
        System.out.println("接收请求，参数为："+param);
        testService.test();
        return param;
    }
}
