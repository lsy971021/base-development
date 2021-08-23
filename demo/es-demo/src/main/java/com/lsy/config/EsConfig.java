package com.lsy.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class EsConfig {
    
    @Value("${es.host}")
    private String hostName;
    @Value("${es.port}")
    private int port;
    @Bean
    public RestHighLevelClient getClient(){
        log.info("ES进行初始化.....");
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(hostName, port, "http")));
    }
}
