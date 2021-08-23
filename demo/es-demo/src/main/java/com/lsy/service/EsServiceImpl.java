package com.lsy.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class EsServiceImpl implements EsService{

    @Autowired
    private RestHighLevelClient client;
    @Override
    public void createIndex() {
        IndexRequest request = new IndexRequest("index1");
        request.id("1");
        Map<String,Object> source = new HashMap<>();
        source.put("name","lsy");
        source.put("age",23);
        source.put("gender",1);
        source.put("high",178);
        source.put("interest","reading books,trip,study");
        request.source(source, XContentType.JSON);
        try {
            IndexResponse index = client.index(request, RequestOptions.DEFAULT);
            System.out.println(index.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
