package com.lsy.test;


import com.lsy.juc.CAS;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnyThing {
    CAS cas = new CAS();

    public static void main(String[] args) throws InterruptedException {
        Sort ss = new Sort();
        Sort sss = new Sort();
    }

    @Test
    public void t() throws ClassNotFoundException {
//        CAS a = this.cas;
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        System.out.println(x.equals(y));
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
//        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        List<List<String>> all = new ArrayList<>();
        List<String> foodName = new ArrayList<>();
        foodName.add("Table");
        foodName.add("Beef Burrito");
        foodName.add("Ceviche");
        foodName.add("Fried Chicken");
        foodName.add("Water");
        all.add(foodName);
        Map<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            List<String> list = orders.get(i);
            Integer table = Integer.valueOf(list.get(1));
            String food = list.get(2);
            if (map.get(table) != null) {
                List<String> l = map.get(table);
                order(l, food);
            } else {
                List<String> one = new ArrayList<>();
                one.add(table + "");
                one.add("0");
                one.add("0");
                one.add("0");
                one.add("0");
                order(one, food);
                map.put(table, one);
                all.add(one);
            }
        }
        return all;
    }

    public void order(List<String> list, String food) {
        switch (food) {
            case "Beef Burrito":
                list.set(1, String.valueOf(Integer.valueOf(list.get(1)) + 1));
                break;
            case "Ceviche":
                list.set(2, String.valueOf(Integer.valueOf(list.get(2)) + 1));
                break;
            case "Fried Chicken":
                list.set(3, String.valueOf(Integer.valueOf(list.get(3)) + 1));
                break;
            case "Water":
                list.set(4, String.valueOf(Integer.valueOf(list.get(4)) + 1));
                break;
            default:
                break;
        }
    }


}
