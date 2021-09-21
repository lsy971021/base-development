package com.lsy.service.impl;

import com.lsy.content.MQInfo;
import com.lsy.listener.ProducerCallBack;
import com.lsy.service.ProducerV0;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerImpl implements ProducerV0 {
    @Autowired
    private DefaultMQProducer producer;

    /**
     * 消息回调
     */
    @Autowired
    private ProducerCallBack callBack;

    @Override
    public void sendMsg(String msg) throws Exception {

        for (int i = 0; i < 10; i++) {
            Message message = new Message(MQInfo.TOPIC, MQInfo.TAG,"key-lsy"+i, msg.getBytes());
            producer.send(message,callBack);
        }

        Message message = new Message(MQInfo.TOPIC, MQInfo.TAG,"key-lsy", msg.getBytes());

        /**
         * 异步发送 + 回调
         */
       // producer.send(message,callBack);

        /**
         * 选择向某个messageQueue发送 + 回调
         */
        /*producer.send(message, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                // 向固定的topic下的某个messageQueue发送消息
                return mqs.get((int) arg);
            }
        },2 ,callBack);*/

        // TODO: 2021/9/17  消息发送到messageQueue是否为轮循，待验证

        // TODO: 2021/9/17  延时发送消息
        // 延时等级："1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
    }
}
