package com.lsy.thread;

import org.junit.Assert;

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
}
