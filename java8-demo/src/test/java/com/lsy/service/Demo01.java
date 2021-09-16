package com.lsy.service;

import org.junit.Test;

import java.util.*;

public class Demo01 {
    @Test
    public void t1(){
//        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
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
        }
    }
}
