package com.lsy.test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassResolveTest {
    static int a = 1;
    static int b = 2;
    static int dd = 19;
    static Map map = new HashMap(20);
    public static final String aa = "hhhhhhhhhhh";
    static String d = "sss";

    public static void main(String[] args) {
        System.out.println(d+111);
        map.put(1,1);
        testMethod();
        noReturnedMethod(b);
    }

    public static String testMethod(){
        String returnString = "qq";
        int r = 10000;
        return returnString+r;
    }

    public static void noReturnedMethod(int i){
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        List<Integer> list5 = new ArrayList<>();
        int c = a+i;
        list.add(c);
    }
}
