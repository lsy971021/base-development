package com.lsy.thread;

import org.junit.Test;

import java.util.concurrent.*;

public class CreateThread {

    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("runnable2");
        }
    }
    /**
     * runnable
     */
    @Test
    public void t1(){
        Thread thread = new Thread(()->{
            System.out.println("runnable");
        });
        thread.start();
        //------------------------
        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
    }


    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("thread");
        }
    }

    /**
     * thread
     */
    @Test
    public void t2(){
        MyThread myThread = new MyThread();
        myThread.start();
        //------------------------
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread2");
            }
        };
        thread2.start();
    }

    /**
     * threadPool
     */
    @Test
    public void t3(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            System.out.println("runnable");
        });
        // ------------------
        //submit往线程池里仍一个任务，然后程序继续往下执行，即移步。若程序执行完之后，会将返回值(callable有返回值)装在future(未来的，将来的，将来可能拿到的返回值)里面
        Future<String> submit = executorService.submit(new MyCallable());
        Future<?> submit1 = executorService.submit(new MyRunnable());
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
     * callable 相对于 runnable 可指定返回类型
     */
    static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("睡觉中，持续2s");
            Thread.sleep(2000);
            return "myCallable";
        }
    }

    /**
     * callable(futureTask)
     */
    @Test
    public void t4(){
        //futureTask 是一个将来能产生值的任务，即 有返回值，实现了runnableFuture  ->  Runnable, Future<V>
        FutureTask<String> stringFutureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        String s = null;
        try {
            // get() 阻塞等待返回结果
            s = stringFutureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }

}
