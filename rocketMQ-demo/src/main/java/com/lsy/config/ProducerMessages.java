package com.lsy.config;

import com.lsy.content.MQInfo;
import org.apache.rocketmq.common.message.Message;

public class ProducerMessages {

    public static Message getMessage(byte[] body){
        return new Message(MQInfo.TOPIC, MQInfo.TAG, body);
    }
}
