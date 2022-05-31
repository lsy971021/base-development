package com.lsy.thread;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolV2 {

    public static class MyThreadPool extends ThreadPoolExecutor{

        public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                            BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

        }

        /**
         * 钩子函数，在任务执行前会被调用
         * @param t the thread that will run task {@code r}
         * @param r the task that will be executed
         */
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            System.out.println("任务开始执行啦！！");
        }

        /**
         * 钩子函数。在任务执行完后会调用
         * 若任务在执行时抛出异常会由该函数去处理,虽然会报错，但会继续执行任务
         * @param r the runnable that has completed
         * @param t the exception that caused termination, or null if
         * execution completed normally
         */
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            System.err.println(t);
//            int i = 10 /0;
            System.out.println("任务执行完成啦！！");
        }
    }

    @Test
    public void threadPoolPlus(){
        MyThreadPool myThreadPool = new MyThreadPool(1, 2, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    /**
                     * 未捕获异常处理
                     * 在线程出现异常时能通过回调该方法来处理异常，这样的好处是可以在线程代码边界之外(Thread.run()) 有一个地方能处理未捕获异常
                     */
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("糟糕  有异常");
                        System.out.println(e);
                    }
                });
                return thread;
            }
        }, new ThreadPoolExecutor.AbortPolicy());

        myThreadPool.execute(()->{
            System.out.println("hello,world");
            // 模拟报错，会在afterExecute()中处理，和在uncaughtException（）中处理未捕获异常
            int i = 10 /0;
        });

        myThreadPool.execute(()->{
            System.out.println("hello,world");
            int i = 10 /0;
        });
    }
}
