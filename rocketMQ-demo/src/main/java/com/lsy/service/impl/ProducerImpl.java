package com.lsy.service.impl;

import com.lsy.config.ProducerMessages;
import com.lsy.listener.ProducerCallBack;
import com.lsy.service.ProducerV0;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerImpl implements ProducerV0 {
    @Autowired
    private DefaultMQProducer producer;

    @Override
    public void sendMsg(String msg) throws Exception {
        producer.send(ProducerMessages.getMessage(msg.getBytes()),new ProducerCallBack());
    }
}
