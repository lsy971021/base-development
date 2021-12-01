package com.lsy.leetcode;

/**
 * 有n个阶梯，一个人每一步只能跨一个台阶或是两个台阶，问这个人一共有多少种走法
 */
public class Stair {

    public int go(int n){
        return (n-1)+(n-2);
    }

}
