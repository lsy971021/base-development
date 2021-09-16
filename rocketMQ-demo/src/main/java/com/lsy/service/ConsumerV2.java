package com.lsy.service;

import com.lsy.content.MQInfo;
import com.lsy.listener.MessageListenerV1;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ConsumerV2 {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(MQInfo.COMSUMERGROUP);
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe(MQInfo.TOPIC,"*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println("222222");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        TimeUnit.MILLISECONDS.sleep(1);

    }
}
