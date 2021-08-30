package com.lsy.test;


@FunctionalInterface
public interface FuctionDemo<T> {

    void test(T t);
   /* default FuctionDemo<T> test2(FuctionDemo<T> fuctionDemo){
        return fuctionDemo.test(T);
    }*/
}
