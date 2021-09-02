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

    @PostMapping("/createDocV1")
    public String createDocV1(){
        esService.createIndex();
        return "ok";
    }

    @PostMapping("/deleteDocV1")
    public String deleteDocV1(){
        esService.deleteDocV1();
        return "ok";
    }

    @PostMapping("/exitDocV1")
    public String exitDocV1(){
        esService.exitDocV1();
        return "ok";
    }

    @PostMapping("/getSourceV1")
    public String getSourceV1(){
        esService.getSourceV1();
        return "ok";
    }
}
