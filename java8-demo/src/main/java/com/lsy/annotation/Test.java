package com.lsy.annotation;

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

    /**
     * 注解核心用途是在反射上
     */
    @CustomAnnotations(newMethod = "AA")
    public void annTest(){
        System.err.println(11111);

    }


    /**
     * 用来修辞方法，属性，类。表示不鼓励使用的程序元素，通常是因为它是危险的，或者因为存在更好的替代方法。
     */
    @Deprecated
    public void deprecated(){
        System.out.println("got!!");
    }

    @org.junit.Test
    public void test(){
        deprecated();
    }
}
