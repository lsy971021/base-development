package com.lsy.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            new LockSupportTest().runnable();
        });
        thread.setName("child thread");
        thread.start();
        Thread.sleep(1000);
        //获取thread线程状态
        System.out.println(thread.getState());
        TimeUnit.MINUTES.sleep(1);
        LockSupport.unpark(thread);
    }

    public void runnable(){
        System.out.println("before....");
        //支持有参数， blocker对象会记录到线程内部
        //使用诊断工具可观察线程阻塞的原因，诊断工具通过调用getBlocker(Thread)来获取blocker对象
        // jstack pid ： 在state为Waiting时  会输出this
        LockSupport.park(this);
        // 相比于park(Object blocker)多了个超时时间，nanos是从当前时间等待nanos秒后返回
        LockSupport.parkNanos(this,1000);
        // 相比于parkNanos的nanos，deadline的时间单位时ms，该时间是从1970年到现在某一个时间点的毫秒值，
        LockSupport.parkUntil(this,1000);
        System.out.println("after...");
    }

}
