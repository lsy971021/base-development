package com.lsy.juc;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongTest {

    static AtomicLong atomicLong = new AtomicLong(0);
    static int count = 100;
    static final CountDownLatch latch = new CountDownLatch(count);

    public static void main(String[] args) {
        for (int t = 0; t < count; t++) {
            new Thread(() -> {
                try {
                    latch.await(); //阻塞，当减为0时放行
                    //高并发下大量线程会同时竞争同一个原子变量，只有一个线程会CAS操作成功，
                    //造成大量线程竞争失败后会通过无线循环不断进行自旋尝试CAS操作，会造成白白浪费CPU资源
                    System.out.print(atomicLong.getAndIncrement() + " "); //先获取再+1
                    //System.out.println(atomicLong.incrementAndGet()); //先+1再获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            latch.countDown(); //减一
        }
    }

    int compare = 0;
    int countForCompare = 1000;
    final CountDownLatch latchForCompare = new CountDownLatch(countForCompare);
    @Test
    public void compare() {
        for (int t = 0; t < countForCompare; t++) {
            new Thread(() -> {
                try {
                    latchForCompare.await(); //阻塞，当减为0时放行
                    System.out.println(compare++); //先获取再+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            latchForCompare.countDown(); //减一
        }
    }
}
