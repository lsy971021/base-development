package com.lsy.service;

import com.lsy.content.MQInfo;
import com.lsy.listener.MQListenerV1;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProducerV1 {
    //    DefaultMQProducer producer = new DefaultMQProducer(MQInfo.PRODUCERGROUP);
    @Autowired
    private MQListenerV1 mqListenerV1;

    public static void main(String[] args) throws RemotingException, InterruptedException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer(MQInfo.PRODUCERGROUP);
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        //producer.createTopic("nameserver",MQInfo.TOPIC,2);
        String body = "this is fist use it";
        Message message = new Message(MQInfo.TOPIC, MQInfo.TAG, body.getBytes());
        //MessageQueue messageQueue = new MessageQueue(MQInfo.TOPIC,"liusiyuandeMacBook-Pro.local",);

        //producer.send(message, new MQListenerV1());
        producer.send(message, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                return list.get((int)o);
            }
        },1);
//        TimeUnit.MILLISECONDS.sleep(1);
    }
}
