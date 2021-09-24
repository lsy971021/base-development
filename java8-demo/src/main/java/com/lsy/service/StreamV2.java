package com.lsy.service;

import com.lsy.model.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamV2 {
    List<Person> personList = new ArrayList<>();
    List<Map<String,String>> lists = new ArrayList<>();
    {
        personList.add(new Person("Tom", 8900, 12,"male", "New York"));
        personList.add(new Person("Jack", 7000, 13,"male", "Washington"));
        personList.add(new Person("Lily", 7800, 14,"female", "Washington"));
        personList.add(new Person("Anni", 8200, 15,"female", "New York"));
        personList.add(new Person("Owen", 9500, 16,"male", "New York"));
        personList.add(new Person("Alisa", 7900,18, "female", "New York"));
        for (int i = 0; i < 8; i++) {
            Map<String,String> map = new HashMap<>();
            map.put("name","lsy"+i);
            map.put("age",(10+i)+"");
            map.put("id",i+"");
        }
    }

    /**
     * 筛选/遍历/匹配（filter/foreach/find/match）
     */
    @Test
    public void test1(){
        personList.stream().filter(x->x.getAge()<15).forEach(System.out::println);
        Optional<Person> first = personList.stream().filter(x -> x.getAge() < 14).findFirst();
        Optional<Person> any = personList.stream().filter(x -> x.getAge() < 14).findAny();
        boolean b = personList.stream().anyMatch(x -> x.getAge() > 14);
        System.out.println("first:"+first.get());
        System.out.println("any:"+any.get());
        System.out.println("anyMatch:"+b);
    }

    /**
     * 收集 (collect)
     */
    @Test
    public void test2(){
        List<Integer> collect = personList.stream().map(Person::getAge).collect(Collectors.toList());
        List<Integer> parallelCollect = personList.stream().parallel().map(Person::getAge).collect(Collectors.toList());
        System.out.println("collect所有年龄："+collect);
        System.out.println("parallelCollect所有年龄："+parallelCollect);
    }

    /*@Test
    public void test3(){
        List<String> collect = lists.stream().flatMap(x ->
            Arrays.stream(x.get("age").toCharArray())
        ).collect(Collectors.toList());
        System.out.println(collect);
    }*/
}
