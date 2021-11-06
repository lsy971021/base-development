package com.lsy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitFunction {

    final static List list = new ArrayList();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->thread1()).start();
        thread2();
    }

    public static void thread1() {
        synchronized (list) {
            try {
                // 预防虚假唤醒（即使没被其他线程调用notify()或notifyAll()或
                // 其他线程调用该 **线程的interrupt()** 也可能从挂起状态变为可运行状态）
                while (list.size() == 0) {
                    // 调用wait()时该线程会被阻塞挂起，直到其他线程调用notify()或notifyAll()时会唤醒
                    // 其他线程调用该 **线程的interrupt()** 会中断，并抛出InterruptedException
                    // wait()线程应先获取 **该对象(调用wait方法的对象)** 的监视器锁，否则会抛异常
                    // 获取监视器锁：1、synchronized(该对象) 2、调用共享变量的方法用synchronized修饰
                    //wait() 会释放锁调用它的共享变量的锁，如果该线程有其他变量的锁则不会释放
                    list.wait(); // 无参数或timeout=0时需其他调用该共享变量的notify()或notifyAll()方法时唤醒
                    //list.wait(1000); //1秒后被唤醒
                    //list.wait(1000,1); // 当nanos>0时 timeout会递增1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 success!");
        }
    }


    public static void thread2() {
       synchronized (list) {
            if (list.size() == 0) {
                list.add(0);
            }
//            TimeUnit.MILLISECONDS.sleep(2000);
            // 必须获取当前共享变量的监视器锁才能调用共享变量的notifyAll()
            list.notifyAll();//执行完后还需释放共享变量锁，被唤醒的线程才 可能(可能存在别的线程争抢锁) 获取到共享对象的监视器锁
            System.out.println("success!");
        }
    }
}