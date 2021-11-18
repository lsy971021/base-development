package com.lsy.gc;

public class MinorGc {

    private static final int _1MB = 1024*1024;

    /**
     *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *  -Xmn10M  :  10M分给新生代，剩下的分给老年代
     *  -XX:SurvivorRatio=8  :  Eden区和一个survivor区的空间比例时8:1
     */
    public static void main(String[] args) {
        byte[] allocation1 = new byte[2*_1MB];
        byte[] allocation2 = new byte[2*_1MB];
        byte[] allocation3 = new byte[2*_1MB];
        //当运行到这时候发现allocation4在年轻代分配不下了，会放到老年代
        byte[] allocation4 = new byte[4*_1MB];
    }
}
