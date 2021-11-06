package com.lsy.controller;

import com.lsy.annotation.ProducerV0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private ProducerV0 producerV0;


    @GetMapping("/sendMsg")
    public void sendMessages(String msg) throws Exception {
        producerV0.sendMsg(msg);
    }
}
