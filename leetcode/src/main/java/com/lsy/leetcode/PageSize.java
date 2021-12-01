package com.lsy.leetcode;

import org.junit.Test;

/**
 * 定义两个变量  n 数据总是， m 每页展示数
 * 求出总页数（用一行代码，且只能用加减乘除）
 */
public class PageSize {

    @Test
    public void invoke() {
        run(202,10);
    }
    public int run(int n,int m){

        return (n/m) + ((((n/m) * m)/n)-1)*((((n/m) * m)/n)-1) ;
        // n 总数  m 每页个数
    }

}
