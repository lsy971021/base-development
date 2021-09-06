package com.lsy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        /*RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("index03");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(QueryBuilders.rangeQuery("age").gte(20));

        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHit[] hits = search.getHits().getHits();
        for (int i = 0; i < hits.length; i++) {
            String id = hits[i].getId();
            Object age = hits[i].getSourceAsMap().get("age");
            System.out.println("id=="+id+"----- age="+age);
        }
        //System.out.println(search);
        //System.out.println(search.getHits());
        //System.out.println(search.getHits().getHits());
        try {
            restHighLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
