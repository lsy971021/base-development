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
     * ^:按位异或，不同为1，相同为0
     */
    @Test
    public void exchange() {
        int a = 3;
        int b = 134;
        System.out.println(a + "源码=" + Integer.toBinaryString(a));
        System.out.println(b + "源码=" + Integer.toBinaryString(b));
        System.out.println("a交换结果=" + (a ^ a ^ b));
        System.out.println("b交换结果=" + (b ^ b ^ a));

        a = a ^ b; // a ^= b
        b = a ^ b; // b ^= b
        a = a ^ b; // a ^= b
        System.out.println("交换后的a=" + a);
        System.out.println("交换后的b=" + b);
    }


    /**
     * 判断两个数正负号是否相同
     */
    @Test
    public void isSameSymbol() {
        int a = 10;
        int b = -11;
        boolean same = (a ^ b) > 0;
        System.out.println(same);
    }


    /**
     * 判断两个数中较大的数
     */
    @Test
    public void compare() {
        //2147483647
        int a = Integer.MAX_VALUE;
        //-2147483648
        int b = Integer.MIN_VALUE;
        int c = a- b;
        //todo 会发生溢出
        int sign = sign(c);
        //1：正 0：负
        System.out.println(sign);
        //int最大值 - int最小值 = -1 （溢出则为-1）
        System.out.println(a+b);
    }

    /**
     * 判断正负号
     *
     * @param i 输入的数
     * @return 返回正负号 1：正 0：负
     */
    public int sign(int i) {
        return flip((i >> 31) & 1);
    }

    /**
     * 判断是0还是1
     *
     * @param i 输入的数
     * @return 如果i是0 则返回1 如果i是1 则返回0
     */
    public int flip(int i) {
        return i ^ 1;
    }
}
