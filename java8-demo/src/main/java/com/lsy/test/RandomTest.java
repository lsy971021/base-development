package com.lsy.test;

import java.util.Random;

public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Random r1 = new Random(10);
            System.out.print(r1.nextInt(10)+"=====");
            System.out.println(r1.nextInt(10));
            Random r2 = new Random(10);
            System.out.print(r2.nextInt(10)+"=====");
            System.out.println(r2.nextInt(10));
            System.out.println("-----------------------");
        }
    }
}
