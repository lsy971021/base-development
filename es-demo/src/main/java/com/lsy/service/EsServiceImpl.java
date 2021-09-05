package com.lsy.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.GetSourceRequest;
import org.elasticsearch.client.core.GetSourceResponse;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
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
    private RestHighLevelClient client; //default timeout=1m

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

    @Override
    public void termVectorsV1() {
        TermVectorsRequest termVectorsRequest = new TermVectorsRequest(index,"2");
        termVectorsRequest.setFields("name");
        /**
         * 设置fieldStatistics为false（默认为true）以省略文档计数、文档频率总和、总词频总和。
         * 设置termStatistics为true（默认为false）以显示总词频和文档频率。
         * 设置positions为false（默认为true）以省略仓位的输出。
         * 设置offsets为false（默认为true）以省略偏移量的输出。
         * 设置payloads为false（默认为true）以省略有效负载的输出。
         * 设置filterSettings过滤可以根据它们的 tf-idf 分数返回的术语。
         * 设置perFieldAnalyzer为指定与字段所具有的分析器不同的分析器。
         * 设置realtime为false（默认为true）以接近实时地检索术语向量。
         * 设置路由参数
         */
        termVectorsRequest.setFieldStatistics(false);
//        termVectorsRequest.setFilterSettings();
        TermVectorsResponse termvectors = null;
        try {
            termvectors = client.termvectors(termVectorsRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(termvectors);
    }

    @Override
    public void updateV1() {
        UpdateRequest updateRequest = new UpdateRequest(index,"2");
        Map<String,Object> map = new HashMap<>();
        map.put("name","lyf");
        updateRequest.doc(map);
        UpdateResponse update = null;
        try {
            update = client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(update);
    }

    @Override
    public void bulkV1() {
        BulkRequest bulkRequest = new BulkRequest("index3");
        for (int i = 0; i < 10; i++) {
            IndexRequest request = new IndexRequest();
            Map<String, Object> map = new HashMap<>();
            map.put("name","lsy-00"+i);
            map.put("age",i);
            map.put("gender",1);
            map.put("high",178);
            map.put("interest","reading books,trip,study");
            request.id(i+"");
            request.source(map);
            bulkRequest.add(request);
        }
        BulkResponse bulk = null;
        try {
            bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bulk.status());
    }

    @Override
    public void deleteByQueryV1() {
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest("index3");
        deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery()).setConflicts("proceed"); //防止版本冲突引起操作失败
        BulkByScrollResponse deleteByQuery = null;
        try {
            deleteByQuery = client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(deleteByQuery.getBulkFailures());
        System.out.println(deleteByQuery.getTotal());
        System.out.println(deleteByQuery.getDeleted());
        System.out.println(deleteByQuery.getStatus());
        System.out.println(deleteByQuery.getCreated());
    }
}
