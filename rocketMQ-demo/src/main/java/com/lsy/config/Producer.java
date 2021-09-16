package com.lsy.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Producer {
    @Value("${rocketmq.address}")
    private String ADDR;
    @Value("${rocketmq.producer.group}")
    private String GROUP;
    DefaultMQProducer producer;

    @Bean
    public DefaultMQProducer DefaultMQProducer() throws MQClientException {
        producer=new DefaultMQProducer(GROUP);
        producer.setNamesrvAddr(ADDR);
        producer.start();
        System.err.println(ADDR);
        return producer;
    }
}
