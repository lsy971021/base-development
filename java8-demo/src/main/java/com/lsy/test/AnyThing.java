package com.lsy.test;


import com.lsy.juc.CAS;
import org.junit.Test;
import sun.security.util.BitArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AnyThing {
    final CAS cas = new CAS();

    public static void main(String[] args) throws InterruptedException {
        Sort ss = new Sort();
        Sort sss = new Sort();
    }

    @Test
    public void t() throws ClassNotFoundException {
//        CAS a = this.cas;
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
        Map<Integer,List<String>> map = new HashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            List<String> list = orders.get(i);
            List<String> one = null;
            Integer table = Integer.valueOf(list.get(1));
            if (map.get(table) != null) {
                one = map.get(table);
            } else {
                one = new ArrayList<>();
                one.add(table + "");
                one.add("0");
                one.add("0");
                one.add("0");
                map.put(table, one);
                all.add(one);
            }
            String food = list.get(2);
            switch (food) {
                case "Beef Burrito":
                    one.add(1, (Integer.valueOf(one.get(1) + 1).toString()));
                    break;
                case "Ceviche":
                    one.add(2, (Integer.valueOf(one.get(1) + 1).toString()));
                    break;
                case "Fried Chicken":
                    one.add(3, (Integer.valueOf(one.get(1) + 1).toString()));
                    break;
                case "Water":
                    one.add(4, (Integer.valueOf(one.get(1) + 1).toString()));
                    break;
                default:
                    break;
            }
        }
        return all;
    }


}
