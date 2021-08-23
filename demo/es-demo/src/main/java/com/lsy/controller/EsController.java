package com.lsy.controller;

import com.lsy.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1")
public class EsController {
    @Autowired
    private EsService esService;

    @PostMapping("/esTest")
    public String esTest(){
        esService.createIndex();
        return "ok";
    }
}
