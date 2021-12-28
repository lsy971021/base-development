package com.lsy.test;

import org.junit.Test;

public class ClassLoadingTest {
    int a ;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

   /* public static void main(String[] args) {
        String l = ClassResolveTest.aa;
        int ss = ClassResolveTest.dd;
        String d = ClassResolveTest.d;


    }*/

    @Override
    public boolean equals(Object obj) {
        return this==obj;
    }
}
