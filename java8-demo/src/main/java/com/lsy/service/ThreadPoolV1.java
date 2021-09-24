package com.lsy.service;

import org.junit.Assert;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolV1 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors()+20,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100)
        );
        threadPoolExecutor.execute(()->System.out.println("xxx"));
    }
}
