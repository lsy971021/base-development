package com;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class test {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("g1");
        defaultMQProducer.setNamesrvAddr("localhost:9876");
        defaultMQProducer.start();

        Message message = new Message("t1", null, "aa".getBytes());

        defaultMQProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(new String(message.getBody()));
                System.out.println("失败");
            }
        });
        defaultMQProducer.shutdown();
    }
}
