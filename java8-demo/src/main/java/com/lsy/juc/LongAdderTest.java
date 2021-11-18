package com.lsy.juc;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {
    //longadder 内部维护多个cell元素(一个动态的cell数组)来分担对单个变量进行争夺的开销
    //longadder 真实值其实是base的值与cell数组里面所有cell元素中的value值的累加，base是基础值默认为0
    //cellBusy用来实现自旋锁，状态值只有0和1
    static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        longAdder.add(1);
        longAdder.add(2);
        longAdder.add(3);
        longAdder.add(4);
        //sum()累加所有cell内部的value值后在累加base，计算总和时没有对cell数组进行加锁，所以累加过程可能有线程安全问题
        System.out.println(longAdder.sum());
    }

}
