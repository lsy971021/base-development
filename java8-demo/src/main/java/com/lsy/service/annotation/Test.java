package com.lsy.service.annotation;

public class Test {
    @org.junit.Test
    public void test1(){
        for (int i = 0; i < 10; i++) {
            t1(i);
        }
    }

    public void t1(final Integer s){
        System.out.println(s);
    }
}
