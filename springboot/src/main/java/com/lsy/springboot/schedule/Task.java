package com.lsy.springboot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * 启动类需加：  @EnableScheduling 、@Async 、@EnableAsync
 * @EnableAsync : 支持异步执行，springboot 会用异步线程池执行任务
 * @EnableScheduling ： 支持定时任务
 */
@Slf4j
@EnableAsync
@Configuration
@EnableScheduling
public class Task {


    /**
     * 默认Scheduled 为单线程执行，当容器启动时第一个定时任务没有完成当前任务时 ，会阻塞其他定时任务的执行
     *      initialDelay ： 容器启动后，默认延迟多久执行任务
     *      fixedDelay： 从当前任务执行完成时刻开始，过多久触发下次任务的执行
     * @Async： 为异步执行，当前任务将扔给springboot线程池来执行，此时就不减少出现因 @Scheduled 为单线程而造成阻塞的可能
     * @throws InterruptedException
     */
//    @Async
    @Scheduled(initialDelay = 1000,fixedDelay = 1 * 5 * 1000)
    public void test() throws InterruptedException {

        while (true){
            Thread.sleep(3000);
            System.out.println("===");
        }
    }

    /**
     * 模拟阻塞场景
     * @throws InterruptedException
     */
    @Scheduled(initialDelay = 2000,fixedDelay = 1 * 5 * 1000)
    public void test1() {
        System.out.println(111);
    }
}
