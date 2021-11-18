package com.lsy.juc;

public class CacheLine {
    // 32k
    static long a=1L;
    static long b=2L;
    static long c=3L;
    static long d=4L;

    public static void main(String[] args) {
        new Thread(()->{
            long old = System.currentTimeMillis();
            for (int i = 0; i < 100000000; i++) {
                c=i<<2;
            }
            System.out.println("===="+(System.currentTimeMillis() - old));
        }).start();
        long old = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            a=i<<2;
        }
        System.out.println(System.currentTimeMillis() - old);
        System.out.println("---"+a+"---"+c);
    }


}
