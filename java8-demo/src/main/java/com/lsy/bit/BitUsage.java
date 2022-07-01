package com.lsy.bit;

import org.junit.Test;

/**
 * 位运算场景
 */
public class BitUsage {

    /**
     * 奇偶性判断
     */
    @Test
    public void odevity() {
        int number = 1;
        System.out.println("1源码=" + Integer.toBinaryString(number));
        //奇数
        int odd = 3;
        System.out.println("3源码=" + Integer.toBinaryString(odd));
        //偶数
        int even = 4;
        System.out.println("4源码=" + Integer.toBinaryString(even));

        //将一个数 于 1 进行 与运算。   奇数则输出1  偶数输出0
        System.out.println(odd & number);
        System.out.println(even & number);
    }

    /**
     * 两个数交换值，不借助中间变量
     */
    @Test
    public void exchange() {
        int a = 3;
        int b = 134;
        System.out.println(a + "源码=" + Integer.toBinaryString(a));
        System.out.println(b + "源码=" + Integer.toBinaryString(b));
        System.out.println("a交换结果=" + (a ^ a ^ b));
        System.out.println("b交换结果=" + (b ^ b ^ a));

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("交换后的a="+a);
        System.out.println("交换后的b="+b);


    }
}
