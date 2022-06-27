package com.lsy.bit;

import org.junit.Test;

/**
 * 位运算:
 * 计算机中存储的都是补码，计算机的运算的时候，都是将原码转成补码进行运算的
 * <p>
 * Java数值运算过程中都是先将十进制转换为二进制然后再进行运算，再把二进制数据转换为十进制展现给用户。二进制运算规则如下：
 * 　　对于有符号的而言，
 * 最高位为符号位，0表示正数，1表示负数
 * 原码:
 * 正数：二进制
 * 负数：二进制
 * 反码：
 * 正数：二进制
 * 负数：符号位保持不变，其他位取反
 * 补码：
 * 正数：二进制
 * 负数：反码+1  （如反码除去高位为0的后四位是 1101/1100/1111，则补码为1110/1101/1100）
 * 0的反码和补码都是0
 */
public class Bit {

    @Test
    public void bit() {
        //int : 4byte  =  32 bit
        int a = 2;
        int b = -2;
        //输出的为补码
        //源码、反码、补码 都是10
        System.out.println("a补码=" + Integer.toBinaryString(a));
        //补码 11～10  -> 反码  11～101 -> 源码 10～010
        System.out.println("b补码=" + Integer.toBinaryString(b));

        //& ： 1和1为1，1和0为0，0和0为0
        //源码： 10 （符号位0 是正数，则反码，补码都一样）
        int q = a & b;
        System.out.println("按位与=" + q);
        System.out.println(Integer.toBinaryString(q));

        //| ：1和1为1，1和0为1，0和0为0
        // 源码 10～010 —> 反码 11~101 -> 补码 11~110
        int w = a | b;
        System.out.println("按位或=" + w);
        System.out.println(Integer.toBinaryString(w));

        //^:按位异或，不同为1，相同为0
        //源码 10~0 -> 反码 11~1 -> 补码 11～100
        int e = a ^ b;
        System.out.println("按位异或=" + e);
        System.out.println(Integer.toBinaryString(e));

        //算数右移（除符号位），值相当于 a/2^1  （根据补码右移）
        //1
        int r = a >> 1;
        System.out.println("算数右移=" + r);
        System.out.println(Integer.toBinaryString(r));

        //逻辑右移（除符号位）,符号位也算，低位舍弃，高位补0 （根据补码右移）
        // 010~01
        int y = b >>> 1;
        System.out.println("逻辑右移=" + y);
        System.out.println(Integer.toBinaryString(y));

        //逻辑左移，相当于 a*2^2 （根据补码左移）
        //1000
        int u = a << 2;
        System.out.println("逻辑左移=" + u);
        System.out.println(Integer.toBinaryString(u));

        // ~ 按位非(NOT)操作。每位取反。即原来是0的变成1，原来是1的变成0。
        // 对a的补码进行取反 = c的补码
        Integer c = ~a;
        //-3
        System.out.println(c);
        //11～101 -> 反码 11～100 -> 源码 10～011
        System.out.println(Integer.toBinaryString(c));
    }

    @Test
    public void commonly() {
        System.out.println("1的源码=" + Integer.toBinaryString(1));
        System.out.println("2的源码=" + Integer.toBinaryString(2));
        System.out.println("4的源码=" + Integer.toBinaryString(4));
        System.out.println("0的源码=" + Integer.toBinaryString(0));
        System.out.println("-1的源码=" + Integer.toBinaryString(-1));
        System.out.println("-2的源码=" + Integer.toBinaryString(-2));
        System.out.println("-4的源码=" + Integer.toBinaryString(-4));
    }
}
