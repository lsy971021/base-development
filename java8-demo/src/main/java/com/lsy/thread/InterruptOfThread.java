package com.lsy.thread;

public class InterruptOfThread {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (;;){

            }
        });
        t.setDaemon(true); //设置为守护线程，等主线程执行完后jvm进程马上结束；默认为用户线程
        t.start();
        System.out.println(t.isInterrupted());  //获取t线程中断标志
        t.interrupt(); //设置t线程中断标志
        System.out.println(t.interrupted()); // 虽然调用的是t的interrupted()但获取的是当前线程的中断标志
        System.out.println(Thread.interrupted()); // 等同于t.interrupted()
        Thread.currentThread().interrupt(); //设置当前线程中断标志
        System.out.println(Thread.currentThread().isInterrupted());//等同于上面的两个获取当前线程中断标志
        System.out.println(t.isInterrupted());//获取t线程中断标志
    }
}
