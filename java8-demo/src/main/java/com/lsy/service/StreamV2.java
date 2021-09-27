package com.lsy.service;

import com.lsy.model.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamV2 {
    List<Person> personList = new ArrayList<>();
    List<Map<String, String>> lists = new ArrayList<>();

    {
        personList.add(new Person("Tom", 8900, 12, "male", "New York"));
        personList.add(new Person("Jack", 7000, 13, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 14, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 15, "female", "New York"));
        personList.add(new Person("Owen", 9500, 16, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 18, "female", "New York"));
        for (int i = 0; i < 8; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name", "lsy:" + i);
            map.put("age", (10 + i) + "");
            map.put("id", i + "");
            lists.add(map);
        }
    }

    /**
     * 筛选/遍历/匹配（filter/foreach/find/match）
     */
    @Test
    public void test1() {
        personList.stream().filter(x -> x.getAge() < 15).forEach(System.out::println);
        Optional<Person> first = personList.stream().filter(x -> x.getAge() < 14).findFirst();
        Optional<Person> any = personList.stream().filter(x -> x.getAge() < 14).findAny();
        boolean b = personList.stream().anyMatch(x -> x.getAge() > 14);
        System.out.println("first:" + first.get());
        System.out.println("any:" + any.get());
        System.out.println("anyMatch:" + b);
    }

    /**
     * 收集/并发操作 (collect/parallel)
     */
    @Test
    public void test2() {
        List<Integer> collect = personList.stream().map(Person::getAge).collect(Collectors.toList());
        List<Integer> parallelCollect = personList.stream().parallel().map(Person::getAge).collect(Collectors.toList());
        System.out.println("collect所有年龄：" + collect);
        System.out.println("parallelCollect所有年龄：" + parallelCollect);
    }

    /**
     * flatMap
     */
    @Test
    public void test3() {
        List<String> collect = lists.stream().flatMap(x ->
                Arrays.stream(x.get("name").split(":"))
        ).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 元素求和/乘积  (reduce)
     */
    @Test
    public void test4() {
        Integer sum = personList.stream().map(x -> x.getAge()).reduce(Integer::sum).get();
        System.out.println(sum);
        Integer max = personList.stream().map(x -> x.getAge()).reduce((x, y) -> x > y ? x : y).get();
        System.out.println(max);
    }

    /**
     * 收集(collect)
     */
    @Test
    public void test5() {
        Map<String, Integer> map = personList.stream().filter(x -> x.getAge() > 12).collect(Collectors.toMap(x -> x.getName(), y -> y.getAge()));
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + "::" + stringIntegerEntry.getValue());
        }
    }

    /**
     * 统计(count/averaging)
     */
    @Test
    public void test6() {
        //总数
        Long count = personList.stream().collect(Collectors.counting());
        //年龄平均值
        Double avg = personList.stream().collect(Collectors.averagingInt(Person::getAge));
        //年龄总和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getAge));
        //年龄所有计算值
        DoubleSummaryStatistics all = personList.stream().collect(Collectors.summarizingDouble(Person::getAge));
        System.out.println("count:" + count);
        System.out.println("avg:" + avg);
        System.out.println("sum:" + sum);
        System.out.println("all:" + all);
    }

    /**
     * 分组(partitioningBy/groupingBy)
     */
    @Test
    public void test7() {
        //将年龄是否大于15进行分组
        Map<Boolean, List<Person>> map = personList.stream().collect(Collectors.partitioningBy(x -> x.getAge() > 15));
        //按姓名分组后再按年龄分组
        Map<String, Map<Integer, List<Person>>> collect = personList.
                stream().collect(Collectors.groupingBy(Person::getName, Collectors.groupingBy(Person::getAge)));
        for (Map.Entry<Boolean, List<Person>> booleanListEntry : map.entrySet()) {
            System.out.println("key:" + booleanListEntry.getKey() + "  value:" + booleanListEntry.getValue());
        }
        System.out.println("----------------------------------------------------------------------------------");
        for (Map.Entry<String, Map<Integer, List<Person>>> stringMapEntry : collect.entrySet()) {
            Map<Integer, List<Person>> value = stringMapEntry.getValue();
            for (Map.Entry<Integer, List<Person>> integerListEntry : value.entrySet()) {
                System.out.println(
                        "bigKey:" + stringMapEntry.getKey() + "  smallKey:" + integerListEntry.getKey() + "  value:" + integerListEntry.getValue());
            }
        }
    }

    /**
     * 接合(joining)
     */
    @Test
    public void test8() {
        //将姓名以'-'进行拼接
        String sub = personList.stream().map(Person::getName).collect(Collectors.joining("-"));
        System.out.println(sub);
    }

    /**
     * 归约(reducing)
     */
    @Test
    public void test9(){
        Integer all = personList.stream().map(Person::getSalary).collect(Collectors.reducing((x, y) -> x + y)).get();
        System.out.println(all);
        Integer allSalary = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 2000)));
        System.out.println(allSalary);
    }

    /**
     * 排序(sorted)
     */
    @Test
    public void test10(){
        //先按年龄倒序排列，再按薪资排序
        List<Person> l = personList.stream().
                sorted(Comparator.comparing(Person::getAge).reversed().
                        thenComparing(Person::getSalary)).collect(Collectors.toList());
        System.out.println(l);
    }

    /**
     * 提取/组合
     */
    @Test
    public void test11(){
        Integer[] s1 = {1,2,3,4,5,6};
        Integer[] s2 = {5,6,7,8,9,0};
        Stream<Integer> a = Stream.of(s1);
        Stream<Integer> b = Stream.of(s2);
        //组合后去重
        List<Integer> distinct = Stream.concat(a,b).distinct().collect(Collectors.toList());
        System.out.println(distinct);
    }
}
