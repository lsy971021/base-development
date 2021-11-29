package com.lsy.leetcode;

import org.junit.Test;

/**
 * 给定一数组 数组前n个元素一次递减，后m个元素依次递增，求出最小值
 */
public class SmallNum {
    @Test
    public void getSmallNum(){
        long start = System.currentTimeMillis();
        doSmallNum(new int[]{7, 6, 5, 3, 2, 1, 7});
        long end = System.currentTimeMillis();
        System.out.println("时间=="+ (end-start));
    }

    public void doSmallNum(int[] all) {
        //至少有3个数
        int i = smallNum(all, 0, all.length);
        System.out.println(all[i]);
    }

    public int smallNum(int[] all, int left, int right) {
        if (left >= right) return left;
        int midIdx = ((left + right) / 2);
        if (midIdx == left || midIdx == right) return right;
        //斜率小于 0 递减
        if (all[midIdx + 1] - all[midIdx] < 0) {
            return smallNum(all, midIdx, right);
        } else { //斜率大于于 0 递增
            return smallNum(all, left, midIdx);
        }
    }
}
