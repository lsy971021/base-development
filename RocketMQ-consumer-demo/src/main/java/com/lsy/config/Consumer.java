package com.lsy.config;

import com.lsy.listener.ConsumerListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class Consumer {
    @Value("${rocketmq.address}")
    private String ADDR;
    @Value("${rocketmq.consumer.group}")
    private String GROUP;
    @Value("${rocketmq.topic}")
    private String TOPIC;
    @Value("${rocketmq.tag}")
    private String TAG;
    DefaultMQPushConsumer consumer;

    @Autowired
    ConsumerListener consumerListener;

    @PostConstruct
    public void consume() throws Exception {
        System.out.println(ADDR);
        System.out.println(consumerListener);
        System.out.println(GROUP);
        consumer = new DefaultMQPushConsumer(GROUP);
        consumer.setNamesrvAddr(ADDR);
        consumer.subscribe(TOPIC,"*");
        consumer.registerMessageListener(consumerListener);
        consumer.start();
    }
}
