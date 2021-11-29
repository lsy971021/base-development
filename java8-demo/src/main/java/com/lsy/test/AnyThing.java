package com.lsy.test;


import org.junit.Test;

public class AnyThing {
    public static void main(String[] args) throws InterruptedException {
        Sort ss = new Sort();
        Sort sss = new Sort();
    }
    @Test
    public void t(){
        test(90,10);
    }


    public void test(int n,int m){
        int s = (n/m) + ((((n/m) * m)/n)-1)*((((n/m) * m)/n)-1) ;
        // n 总数  m 每页个数
        System.out.println(s);
    }
}
