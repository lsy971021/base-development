package com.lsy.service;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueV1 {
    static BlockingQueue<String> b = new ArrayBlockingQueue(10);


    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                b.put("aaa");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(b.take());

    }

}
