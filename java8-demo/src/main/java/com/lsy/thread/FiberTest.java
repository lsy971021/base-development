package com.lsy.thread;

import co.paralleluniverse.fibers.Fiber;
import org.junit.Test;

/**
 * 线程、协程
 */
public class FiberTest {
    // 线程/协程 数量
    static int THREAD_LENTH = 10000;

    //线程
    @Test
    public void test() {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_LENTH];
        // 给数组里面初始化线程，以及放进去任务
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                calc();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        // 在这里启动线程进行调度
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        // 主线程在这里等等结果，等其他线程结束了一起结束
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("100万个线程运算200万次时间花销为:" + (System.currentTimeMillis() - startTime));
    }


    /**
     * 协程的切换是就在用户态，基本不会到内核态里面，都是在一个线程里面串行执行的方法，不涉及内核态用户态的切换。所以他的开销是很小的，这种优势在你线程协程个数越大的时候越明显
     */
    @Test
    public void test2() {
        long startTime = System.currentTimeMillis();
        // 创建十万个协程的一个数组
        Fiber[] fibers = new Fiber[THREAD_LENTH];
        // 给数组里面初始化协程，以及放进去任务
        for (int i = 0; i < fibers.length; i++) {

            fibers[i] = new Fiber(() -> {
                calc();
                Fiber.sleep(1000);
                Thread.sleep(100);
            });
        }


        // 在这里启动线程进行调度
        for (int i = 0; i < fibers.length; i++) {
            fibers[i].start();
        }
        System.out.println(11111111);

        // 主线程在这里等等结果，等其他协程结束了一起结束
        for (int i = 0; i < fibers.length; i++) {
            try {
                fibers[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("100万个协程运算200万次时间花销为:" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 不用线程也不用协程
     */
    @Test
    public void test3(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_LENTH; i++) {
            calc();

        }
        System.out.println("1万次循环运算200万次时间花销为:" + (System.currentTimeMillis() - startTime));
    }


    public static void calc() {
        int result = 0;
        // 运算200万次，每次都加一下
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 10000; j++) {
                result += i;
            }
        }
    }
}
