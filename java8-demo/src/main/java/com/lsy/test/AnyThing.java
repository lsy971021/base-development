package com.lsy.test;


import com.lsy.juc.CAS;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public class AnyThing implements Cloneable{
    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    int i = 10;
    CAS cas = new CAS();
    @Test
    public void tt() throws InterruptedException {
        new Thread(()->{
            if(threadLocal.get()==null){
                i++;
                threadLocal.set(i);
                System.out.println("1111:::"+threadLocal.get());
            }else {
                System.out.println("===="+threadLocal.get());
            }
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            if(threadLocal.get()==null){
                i++;
                threadLocal.set(i);
                System.out.println("2222:::"+threadLocal.get());
            }else {
                System.out.println(threadLocal.get());
            }
        }).start();

    }
    public static void main(String[] args) throws InterruptedException {
        List<ClassLoadingTest> a = new ArrayList<>();
        ClassLoadingTest aa = new ClassLoadingTest();
        aa.setA(1);
        ClassLoadingTest ss = new ClassLoadingTest();
        ss.setA(1);
        ClassLoadingTest dd = new ClassLoadingTest();
        dd.setA(1);
        ClassLoadingTest ee = new ClassLoadingTest();
        ee.setA(1);
        ClassLoadingTest ff = new ClassLoadingTest();
        ff.setA(1);
        a.add(ff);
        System.out.println(a);
        System.out.println(a.size());
        /*for (ClassLoadingTest classLoadingTest : a) {

        }*/
        for (int i = 0; i < a.size(); i++) {
            if (i == 0)
                a.remove(a.get(i));
        }
        System.out.println(a);
        System.out.println(a.size());
    }

    private volatile ReentrantLock lock = new ReentrantLock();

    @Test
    public void t() throws IOException {
        /*Integer a = 999;
        Integer b = 999;
        System.out.println(a==b);
        System.out.println(a.equals(b));
        Integer c = 99;
        Integer d = 99;
        System.out.println(c==d);*/
        ClassLoadingTest c1 = new ClassLoadingTest();
        ClassLoadingTest c2 = c1;
        System.out.println(c1 == c2);
        final ClassLoadingTest c3 = c1;
        System.out.println(c1 == c3);
    }

    public void t4() throws InterruptedException {
        //Thread.sleep(2000);
        System.out.println(9999);
    }

    public void t2() {
        lock.lock();
        try {
            System.out.println("1111");
            //TimeUnit.SECONDS.sleep(2);
            //Thread.sleep(1000);
            Thread t4 = new Thread(() -> {
                try {
                    t4();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t4.start();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        System.out.println(x.equals(y));
    }

    public void t3() {
        lock.lock();
        try {
            System.out.println("2222");
            //TimeUnit.SECONDS.sleep(2);
            //Thread.sleep(1000);
            System.out.println("end   ... ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
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

    @Test
    public void test4(){
        Set set = new HashSet();
        String a = "重地";
        String b = "通话";
        System.out.println(a.hashCode()==b.hashCode());
        System.out.println(a.equals(b));
        set.add(a);
        set.add(b);
        set.forEach(x-> System.out.println(x));
    }
    @Test
    public void test5() throws CloneNotSupportedException {
        AnyThing anyThing = new AnyThing();
        AnyThing a =(AnyThing) anyThing.clone();
        System.out.println(anyThing==a);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Test
    public void test11(){
        String s = "[sssss]";
        System.out.println(s.substring(1, s.length() - 1));
    }
}
