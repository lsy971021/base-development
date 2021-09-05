package com.lsy.controller;

import com.lsy.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1")
public class EsController {
    @Autowired
    private EsService esService;

    @GetMapping("/wanglei")
    public String test(){
        return "臭傻逼";
    }

    @PostMapping("/createDocV1")
    public String createDocV1(){
        esService.createIndex();
        return "ok";
    }

    @PostMapping("/updateDocV1")
    public String updateDocV1(){
        esService.updateV1();
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

    @PostMapping("/termVectorsV1")
    public String termVectorsV1(){
        esService.termVectorsV1();
        return "ok";
    }

    @PostMapping("/bulkV1")
    public String bulkV1(){
        esService.bulkV1();
        return "ok";
    }

    @PostMapping("/deleteByQueryV1")
    public String deleteByQueryV1(){
        esService.deleteByQueryV1();
        return "ok";
    }
}
