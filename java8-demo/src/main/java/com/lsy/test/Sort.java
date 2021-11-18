package com.lsy.test;

import sun.jvm.hotspot.utilities.BitMap;


public class Sort {
    int[] bit = new int[Integer.MAX_VALUE];

    public void test(int[] all){
        BitMap bitMap = new BitMap(Integer.MAX_VALUE);
        for (int i = 0; i < 100; i++) {
            bitMap.atPut(all[i],true);
        }
        for (int i =100;i < all.length; i++){
            bitMap.atPut(all[i],true);

        }
    }
}
