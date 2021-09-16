package com.lsy.listener;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;


//@Component
public class MQListenerV1 implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        System.out.println("=====消息发送成功=====");
        System.out.println("MessageQueue:"+sendResult.getMessageQueue());
        System.out.println("MsgId:"+sendResult.getMsgId());
        System.out.println("SendStatus:"+sendResult.getSendStatus());
        System.out.println("QueueOffset:"+sendResult.getQueueOffset());
        System.out.println("OffsetMsgId:"+sendResult.getOffsetMsgId());
        System.out.println("RegionId:"+sendResult.getRegionId());
    }

    @Override
    public void onException(Throwable throwable) {
        System.out.println("=====消息发送失败=====");
    }

}
