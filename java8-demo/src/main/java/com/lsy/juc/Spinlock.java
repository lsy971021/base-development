package com.lsy.juc;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自旋锁: CPU空转消耗并且时刻关注该所是否已经被释放，直到自己获取该锁为止。
 * 因此，在等待时间较长的时候是不适用自旋锁的，这会白白消耗大量的CPU性能。
 */
public class Spinlock {

    private AtomicLong atomicLong = new AtomicLong(1);

    /**
     * 优化前
     */
    public void beforeOptimizer() throws InterruptedException {
        stop:
        for(;;){
            if(atomicLong.incrementAndGet()>=100000000L)
                break stop;
        }
    }

    /**
     * 优化后
     */
    public void afterOptimizer() throws InterruptedException {
        stop:
        for(;;){
            if(atomicLong.incrementAndGet()>=100000000L)
                break stop;
            Thread.yield();
        }
    }

    @Test
    public void invoke() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            executorService.execute(()-> {
                try {
//                    beforeOptimizer();
                    afterOptimizer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        //关闭线程池。不再执行新任务，但以执行的任务会执行完成
        executorService.shutdown();
        //在指定时间内线程池达到了终止状态才返回true
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

}
