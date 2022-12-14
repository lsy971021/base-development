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
     * 协程的切换是就在用户态，基本不会到内核态里面，一个线程里面的协程之间是串行执行的，不涉及内核态用户态的切换。所以他的开销是很小的，这种优势在你线程协程个数越大的时候越明显
     * 一个线程内的多个协程是串行执行的，不能利用多核，所以，显然，协程不适合计算密集型的场景。协程适合I/O 阻塞型。
     * I/O本身就是阻塞型的（相较于CPU的时间世界而言）。就目前而言，无论I/O的速度多快，也比不上CPU的速度，所以一个I/O相关的程序，当其在进行I/O操作时候，CPU实际上是空闲的
     * 如：1个线程有5个I/O的事情（子程序）要处理。如果我们绝对的串行化，那么当其中一个I/O阻塞时，其他4个I/O并不能得到执行，因为程序是绝对串行的，5个I/O必须一个一个排队等待处理，当一个I/O阻塞时，其它4个也得等着。
     * 而协程能比较好地处理这个问题，当一个协程（特殊子进程）阻塞时，它可以切换到其他没有阻塞的协程上去继续执行，这样就能得到比较高的效率
     * 当已经达到了“I/O密集型”的程度，而“I/O密集型”确实是协程无法应付的，因为它没有利用多核的能力。这个时候的解决方案就是“多进程+协程”了。
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
