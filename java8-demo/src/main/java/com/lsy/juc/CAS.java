package com.lsy.juc;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CAS {
    static Unsafe unsafe;
    private static volatile long state = 0;
    static long stateOffset;

    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        unsafe = (Unsafe)theUnsafe.get(null);
        //使用在静态属性
        stateOffset = unsafe.staticFieldOffset(CAS.class.getDeclaredField("state"));
        //使用在非静态属性
        //stateOffset = unsafe.objectFieldOffset(CAS.class.getDeclaredField("state"));
        CAS cas = new CAS();
        boolean b = unsafe.compareAndSwapInt(cas, stateOffset, 0, 1);
        System.out.println(b);
        System.out.println(state);
    }
}
