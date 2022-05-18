package com.lsy.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    /**
     * 提交任务，执行完成后获取返回值（callable）
     */
    public void threadPool2(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(()->{
            System.out.println("threadPool is running");
        });

        //submit往线程池里仍一个任务，然后程序继续往下执行，即移步。若程序执行完之后，会将返回值(callable有返回值)装在future(未来的，将来的，将来可能拿到的返回值)里面
        Future<String> submit = executorService.submit(new CreateThread.MyCallable());
        Future<?> submit1 = executorService.submit(new CreateThread.MyRunnable());
        try {
            //get()方法会阻塞，什么时候把callable装好在future中什么时候执行
            String s = submit.get();
            System.out.println(s);
            System.out.println(submit1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    /**
     * 拒绝策略
     */
    @Test
    public void Rejected(){
        //默认拒绝策略
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                System.out.println("线程池已满，请别放任务了");
            }
        });
        //最大线程数 3  +  队列大小 5  等于  最多执行任务数  8
        //超过最大任务数就会执行拒绝策略


        for (int i = 0; i < 8; i++) {
            executor.execute(()->{
                try {
                    System.out.println(Thread.currentThread()+"线程开始休息10s");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        // 此时超过最大任务数，将执行拒绝策略(默认为抛异常)
        executor.execute(()->{
            try {
                System.out.println(Thread.currentThread()+"线程开始休息10s");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        System.out.println("wait");

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 模仿线程池submit后任务为执行完成，主线程return
     */
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(5));
    @Test
    public void returnTest() throws InterruptedException {
        System.out.println(asynchronization());
        threadPoolExecutor.submit(()-> System.out.println("!!!!"));
        Thread.sleep(2000);
    }

    public List<String> asynchronization() throws InterruptedException {
        List<String> list = new ArrayList<>();
        threadPoolExecutor.submit(()->{
            //模仿业务处理
            try {
                System.out.println("start");
                Thread.sleep(3000);
                System.out.println("end");
                list.add("ok");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        /**
         * 若没有awaitTermination将可能返回空list
         */
//        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(4,TimeUnit.MINUTES);
        return list;
    }
}
