package com.lsy.thread;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor {

    @Test
    public void threadPool(){
        // 无界队列
        java.util.concurrent.ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
                new java.util.concurrent.ScheduledThreadPoolExecutor(1);

        /**
         * 一分钟后执行（只一次任务）
         */
        scheduledThreadPoolExecutor.schedule(()->{
            System.out.println("任务1开始执行");
        },1, TimeUnit.MINUTES);

        /**
         * 周期执行
         * 只依赖任务开始执行时间，不依赖于任务本身执行的时间（比如每隔2s执行一次，2s后上个任务没执行完成也会继续执行新任务）
         * command the task to execute
         * initialDelay  初始延迟（延迟多久才开始执行第一个任务）
         * period 每隔多久执行一次
         * unit  period 的 时间单位
         */
        scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
            System.out.println("任务2开始执行");
        },1,2,TimeUnit.MINUTES);

        /**
         * 周期执行
         * 任务执行完后过多久再次执行
         * command the task to execute
         * initialDelay  初始延迟（延迟多久才开始执行第一个任务）
         * period 每隔多久执行一次
         * unit  period 的 时间单位
         */
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(()->{
            System.out.println("任务3开始执行");
        },1,1,TimeUnit.MINUTES);
    }
}
