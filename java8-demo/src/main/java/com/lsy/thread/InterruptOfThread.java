package com.lsy.thread;

public class InterruptOfThread {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (;;){

            }
        });
        t.setDaemon(true); //设置为守护线程，等主线程执行完后jvm进程马上结束；默认为用户线程
        t.start();
        System.out.println(t.isInterrupted());  //获取t线程中断标志，但不会重置中断标志
        t.interrupt(); //设置t线程中断标志
        //会返回是否中断 并重置 当前 线程的中断状态 变为非中断状态
        System.out.println(Thread.interrupted());
        System.out.println(t.interrupted()); // 同上
        Thread.currentThread().interrupt(); //设置当前线程中断标志
        System.out.println(Thread.currentThread().isInterrupted());//等同于上面的两个获取当前线程中断标志
        System.out.println(t.isInterrupted());//获取t线程中断标志
    }
}
