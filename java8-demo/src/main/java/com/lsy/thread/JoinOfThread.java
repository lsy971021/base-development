package com.lsy.thread;

public class JoinOfThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 start");
        });
        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 start");
        });
        t1.start();
        t2.start();
        //主线程调用join()时会被阻塞
        t1.join();
        System.out.println("main");
        t2.join();
    }
}
