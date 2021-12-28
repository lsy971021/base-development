package com.lsy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.model.User;
import com.lsy.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MybatisPlusController {
    @Autowired
    MybatisService mybatisService;

    @GetMapping("/test")
    public void test(){
        mybatisService.test();
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        mybatisService.save(user);
    }

    @PostMapping("/find")
    public void findBySomething(@RequestParam("something") String something){
        mybatisService.findBySomething(something);
    }

    @PostMapping("/update")
    public void updateBySomething(@RequestBody User user){
        mybatisService.updateBySomething(user);
    }

    @PostMapping("/delete")
    public void deleteBySomething(@RequestParam String something){
        mybatisService.deleteBySomething(something);
    }

    @PostMapping("/page")
    public Page<User> deleteBySomething(){
        return mybatisService.page();
    }

    @GetMapping("/findBySql")
    public void findBySql(){
        mybatisService.findBySql();
    }


}
