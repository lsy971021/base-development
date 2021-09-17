package com.lsy.listener;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ConsumerListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        System.out.println("===Consumer收到消息===");
        System.out.println(list.toString());
        System.out.println(consumeConcurrentlyContext.toString());
        System.out.println("===============");
        for (MessageExt messageExt : list) {
            System.out.println("messageExt:"+messageExt.getQueueId());
            System.out.println("messageExt:"+new String(messageExt.getBody()));
            System.out.println("----------------");
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
