package com.lsy.utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * httpg工具类
 */
public class HttpUtils {
    private static final Log logger = LogFactory.getLog(HttpUtils.class);
    public static final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .build();


    /**
     * okhttp3
     */
    public static String okHttpPost(String url,String body){
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType, body);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", requestBody)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            logger.error("请求异常");
        }
        return null;
    }

    public static String okHttpDelete(String url){
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("DELETE", body)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            logger.error("请求异常");
        }
        return null;
    }
}
