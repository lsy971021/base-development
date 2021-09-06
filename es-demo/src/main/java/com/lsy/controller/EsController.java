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

    /**
     * 新增数据
     * @return
     */
    @PostMapping("/createDocV1")
    public String createDocV1(){
        esService.createIndex();
        return "ok";
    }

    /**
     * 修改数据
     * @return
     */
    @PostMapping("/updateDocV1")
    public String updateDocV1(){
        esService.updateV1();
        return "ok";
    }

    /**
     * 删除数据
     * @return
     */
    @PostMapping("/deleteDocV1")
    public String deleteDocV1(){
        esService.deleteDocV1();
        return "ok";
    }

    /**
     * 判断某个数据是否存在
     * @return
     */
    @PostMapping("/exitDocV1")
    public String exitDocV1(){
        esService.exitDocV1();
        return "ok";
    }

    /**
     * 查询数据
     * @return
     */
    @PostMapping("/getSourceV1")
    public String getSourceV1(){
        esService.getSourceV1();
        return "ok";
    }

    /**
     *
     * @return
     */
    @PostMapping("/termVectorsV1")
    public String termVectorsV1(){
        esService.termVectorsV1();
        return "ok";
    }

    /**
     * 批量操作
     * @return
     */
    @PostMapping("/bulkV1")
    public String bulkV1(){
        esService.bulkV1();
        return "ok";
    }

    /**
     * 通过查询删除
     * @return
     */
    @PostMapping("/deleteByQueryV1")
    public String deleteByQueryV1(){
        esService.deleteByQueryV1();
        return "ok";
    }

    /**
     * 获取多条数据
     * @return
     */
    @PostMapping("/multiGetDocV1")
    public String multiGetDocV1(){
        esService.multiGetDocV1();
        return "ok";
    }


    @PostMapping("/updateByQueryV1")
    public String updateByQueryV1(){
        esService.updateByQueryV1();
        return "ok";
    }

    @GetMapping("/searchDocV1")
    public String searchDocV1(){
        esService.searchDocV1();
        return "ok";
    }
}
