package com.lsy.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.lsy.springboot.pojo.Department;
import com.lsy.springboot.service.MybatisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mybatis")
public class MybatisTestController {

    @Autowired
    private MybatisTestService mybatisTestService;

    @GetMapping("/test/{id}")
    public Department findAll(@PathVariable(value = "id") Integer id){
        return mybatisTestService.findById(id);
    }

    @GetMapping("/test/findAll")
    public PageInfo findAll(){
        return mybatisTestService.findAll();
    }

    @GetMapping("/test/insert")
    public void insert(){
        mybatisTestService.insert();
    }


}
