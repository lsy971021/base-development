package com.lsy.test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>(2);
        map.put("a","b");
        map.put("c","d");
        map.put("e","f");
        map.put("g","h");
        map.put("i","j");
        map.entrySet().forEach(v->System.out.println(v.getKey()+"=="+v.getValue()));
    }
}
