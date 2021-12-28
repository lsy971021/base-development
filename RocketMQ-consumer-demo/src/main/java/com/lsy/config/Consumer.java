package com.lsy.config;

import com.lsy.listener.ConsumerListener;
import lombok.Getter;
import lombok.Setter;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Component
//或取消这个 用@Value
//此注解需要getter 和 setter
@ConfigurationProperties(prefix = "mq")
public class Consumer {
//    @Value("${rocketmq.address}")
    private String ADDR;
//    @Value("${rocketmq.consumer.group}")
    private String GROUP;
//    @Value("${rocketmq.topic}")
    private String TOPIC;
//    @Value("${rocketmq.tag}")
    @NotEmpty(message = "TAG不能为空")
    private String TAG;
    DefaultMQPushConsumer consumer;

    @Autowired
    ConsumerListener consumerListener;

    @PostConstruct
    public void consume() throws Exception {
        consumer = new DefaultMQPushConsumer(GROUP);
        consumer.setNamesrvAddr(ADDR);
        consumer.subscribe(TOPIC,"*");
        /**
         * 注册监听器
         */
        consumer.registerMessageListener(consumerListener);
        consumer.start();
    }
}
