package com.lsy.controller;

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
    public void deleteBySomething(@RequestBody String something){
        mybatisService.deleteBySomething(something);
    }

}
