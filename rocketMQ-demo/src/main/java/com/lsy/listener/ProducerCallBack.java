package com.lsy.listener;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

@Component
public class ProducerCallBack implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        System.out.println("===Producer发送消息成功===");
        System.out.println(sendResult.toString());
    }

    @Override
    public void onException(Throwable throwable) {
        System.out.println("===Producer发送消息失败===");
        System.out.println(throwable.getMessage());

    }
}
