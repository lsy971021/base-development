package com.lsy.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class EsServiceImpl implements EsService{

    private final String index="index1";

    @Autowired
    private RestHighLevelClient client;

    /**
     * 创建新的索引
     */
    @Override
    public void createIndex() {
        IndexRequest request = new IndexRequest(index);
        request.id("2");
        Map<String,Object> source = new HashMap<>();
        source.put("name","刘思远");
        source.put("age",23);
        source.put("gender",1);
        source.put("high",178);
        source.put("interest","reading books,trip,study");
        request.source(source, XContentType.JSON);
        try {
            //异步发送
//            client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
//                @Override
//                public void onResponse(IndexResponse indexResponse) {
//                    //操作成功 。。。。。
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//                    //操作执行失败 。。。。。
//                }
//            });
            //同步发送
            IndexResponse index = client.index(request, RequestOptions.DEFAULT);

            System.out.println(index.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     */
    @Override
    public void deleteDocV1() {
        DeleteRequest deleteRequest = new DeleteRequest(index);
        deleteRequest.id("1");
        DeleteResponse delete = null;
        try {
            delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(delete);
    }

    /**
     * 判断某个id的数据是否存在
     */
    @Override
    public void exitDocV1() {
        GetRequest getRequest = new GetRequest(index,"2");
        //Disable fetching _source.
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        //Disable fetching stored fields.
        getRequest.storedFields("_none_");
        boolean exists=false;
        try {
            exists = client.exists(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(exists);
    }

    @Override
    public void getSourceV1() {
        GetSourceRequest getSourceRequest = new GetSourceRequest(index,"2");
        // includes 、excludes   默认是全部返回
        getSourceRequest.fetchSourceContext(new FetchSourceContext(true,new String[]{"name"},null));
        GetSourceResponse source = null;
        try {
            source = client.getSource(getSourceRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(source);
    }
}
