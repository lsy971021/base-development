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
 * 负数：反码+1  （如反码除去高位为0的后四位是 1101/1100，则补码为1110/1101）
 * 0的反码和补码都是0
 */
public class Bit {

    @Test
    public void bit() {
        //int : 4byte  =  32 bit
        int a = 2;
        int b = -2;
        //输出的为补码
        //10
        System.out.println(Integer.toBinaryString(a));
        //11～10  -> 反码  11～101 -> 源码 10～10
        System.out.println(Integer.toBinaryString(b));

        // ~ 按位非(NOT)操作。每位取反。即原来是0的变成1，原来是1的变成0。
        // 对a的补码进行取反 = c的补码
        Integer c = ~a;
        //-3
        System.out.println(c);
        //11～101 -> 反码 11～100 -> 源码 10～011
        System.out.println(Integer.toBinaryString(c));

    }
}
