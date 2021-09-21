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
        for (MessageExt messageExt : list) {
            System.out.println("QueueId:"+messageExt.getQueueId()+"----QueueOffset:"+messageExt.getQueueOffset()+"----CommitLogOffset:"
                    +messageExt.getCommitLogOffset()+"----Keys:"+messageExt.getKeys());
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
