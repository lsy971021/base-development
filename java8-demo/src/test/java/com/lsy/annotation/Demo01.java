package com.lsy.annotation;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class Demo01 implements Cloneable {
    @Test
    public void t1(){
        final String coursewareCode ="T1C02L02U01F1Lesson05";
        String a = coursewareCode.substring(coursewareCode.indexOf("C"), coursewareCode.indexOf("L"));
        System.out.println(a);

//        List<Map<String,Object>> list = new ArrayList<>();
       /* Map<String,List<String>> map = new HashMap<>();
        int e = 1;
        for (int i = 0; i < 4; i++) {
            ArrayList l = new ArrayList();
            l.add(i+e++);
            map.put(i+"",l);
        }
        Set<Map.Entry<String, List<String>>> entries = map.entrySet();
        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
            System.out.println(stringListEntry.getKey());
            System.out.println(stringListEntry.getValue().toString());
        }*/
    }

    @Test
    public void t2(){
        String s1 = "exist";
        String s2 = "exist";
        System.out.println(s1==s2); //true
        System.out.println((s1.intern()==s1)); //true
        String s3 = new StringBuilder("!!").append("exist").toString();
        System.out.println(s3.intern()==s3); //true
        System.out.println(s2.intern()==s2); //true
        String s4 = new String("aaa");
        String s5 = "aaa";
        System.out.println(s4.intern()==s5);  //true
    }

    public static void main(String[] args) {
        String str = "hello";
        test(str);
        System.out.println(str);
    }

    public static void test(String str) {
        str = str + "world";
        System.out.println(str);
    }
    @Test
    public void tt(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
    }
}
