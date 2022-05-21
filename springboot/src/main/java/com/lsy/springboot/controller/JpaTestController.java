package com.lsy.springboot.controller;

import com.lsy.springboot.service.JpaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 加入spring容器管理，单例
 */
@RestController
@RequestMapping("/jpa")
public class JpaTestController {

    @Autowired
    private JpaTestService jpaTestService;

    @GetMapping("/test")
    public String test(@RequestParam(name = "param",required = false,defaultValue = "default") String param){
        System.out.println("接收请求，参数为："+param);
        jpaTestService.test();
        return param;
    }
}
