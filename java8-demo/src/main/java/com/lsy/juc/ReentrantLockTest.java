package com.lsy.juc;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * wait 和 await 区别：
 * await是ConditionObject类里面的方法，ConditionObject实现了Condition接口；
 * 而ReentrantLock里面默认有实现newCondition()方法，新建一个条件对象。
 * 该方法就是用在ReentrantLock中根据条件来设置等待。唤醒方法也是由专门的Signal()或者Signal()来执行。
 * 另外await会导致当前线程被阻塞，会放弃锁，这点和wait是一样的。
 *
 * wai()是Object类提供的，一般与synchronized联合使用。调用wait之后会释放锁，导致线程等待。唤醒进程使用notify()或者notifyAll()
 * await()Condition类是当中的，一般与Lock联合使用。
 */
public class ReentrantLockTest {
    // 一个锁lock 对应一个AQS队阻塞队列，对应多个条件变量，每个条件变量有自己的一个条件队列
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    @Test
    public void test() throws InterruptedException {
        Thread t1 = new Thread(() -> test2("t1"));
        Thread t2 = new Thread(() -> test2("t2"));
        Thread t3 = new Thread(() -> test3("t3"));
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t3.start();
    }

    public void test2(String threadName){
        //独占锁  （countdownlunch 或 Semaphore 等为共享锁）
        //当一个线程获取到锁后，在AQS内部会首先使用CAS操作把state状态值从0变为1，然后设置当前锁的持有者为当前线程，当线程再次获取锁时发现
        //他就是锁的持有者，则state 比状态值从1 变为2 ，也就是可重入次数，当另外一个线程获取锁发现不是该锁的持有者就会放入AQS阻塞队列后挂起
        //当应用于condition.await()时 ，类似于wait()的监视器锁 synchronized
        lock.lock();
        try {
            System.out.println(threadName + " in lock ....");
            //当调用条件变量await()时会释放当前线程获取到的锁，并被转换为node节点插入到条件变量对应的条件队列里面
            //释放当前线程获得的锁后还需要别的线程调用条件变量的signal() 或 signalAll()时才可能抢到锁继续执行
            condition.await();
            System.out.println(threadName + " out lock ...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void test3(String threadName){
        lock.lock();
        try {
            System.out.println(threadName + " start signal ...");
            // 把条件队列里面的一个node节点移动到AQS的阻塞队列里面，等待时机获取锁
            condition.signal();
            System.out.println(threadName + " end signal ...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
