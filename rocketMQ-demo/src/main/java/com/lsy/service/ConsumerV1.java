package com.lsy.service;

import com.lsy.content.MQInfo;
import com.lsy.listener.MessageListenerV1;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;


public class ConsumerV1 {
//    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQInfo.COMSUMERGROUP);
    /*@Autowired
    private MessageListenerV1 listener;*/


    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQInfo.COMSUMERGROUP);
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe(MQInfo.TOPIC,"*");

        consumer.registerMessageListener(new MessageListenerV1());
        consumer.start();
        TimeUnit.MILLISECONDS.sleep(1);
    }
}
