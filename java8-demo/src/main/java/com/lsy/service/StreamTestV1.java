package com.lsy.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTestV1 {
    /**
     * filter
     */
    @Test
    public void filterTest() {
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        list.forEach((x)-> System.out.println(x));
        list.stream().filter(element -> element.contains("王")).forEach(System.out::println);
    }

    /**
     * map、collectors、flatMap
     */
    @Test
    public void streamMap(){
        List<Map<String,Object>> list  = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("test",i);
            map.put("hello",i+"!!!");
            list.add(map);
        }
        List<List<Object>> test = Stream.of(list).map(x -> (x.stream().map(s -> (s.get("test"))).collect(Collectors.toList()))).collect(Collectors.toList());
        List<Object> test1 = Stream.of(list).map((x) -> (x.stream().map((s) ->
                (s.get("test"))).collect(Collectors.toList()))).flatMap(StreamTestV1::flatMapTest).collect(Collectors.toList());
        System.out.println(test);
        System.out.println(test1);
    }

    public static Stream<Object> flatMapTest(List<Object> list){
        List<Object> l = new ArrayList<>();
        for (Object o : list) {
            l.add(o);
        }
        return l.stream();
    }


    /**
     * collect、groupingBy、map
     */
    @Test
    public void groupTest(){
        List<Map<String,Object>> list  = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Map<String ,Object> map = new HashMap<>();
            map.put("topic","topic["+i+"]");
            map.put("classStartTime",123456+i);
            if(i%3==1){
                map.put("classOrder",10);
                list.add(map);
                continue;
            }
            map.put("classOrder",i);
            list.add(map);
        }

        List<List<String>> allTopics = new ArrayList();
        List<String> allClassStartTime = new ArrayList<>();
        List<String> allClassOrder = new ArrayList<>();
        Map<Object, List<Map<String, Object>>> classOrder = list.stream().collect(Collectors.groupingBy(x -> x.get("classOrder")));
        classOrder.forEach((x,y)->System.out.println(y));

        for (Object o : classOrder.keySet()) {
            allClassStartTime.add(classOrder.get(o).get(0).get("classStartTime").toString());
            allClassOrder.add(classOrder.get(o).get(0).get("classOrder").toString());
            allTopics.add(classOrder.get(o).stream().map(s->(String)s.get("topic")).collect(Collectors.toList()));
        }

        System.out.println("==================");
        allClassOrder.forEach(System.out::println);
        allClassStartTime.forEach(System.out::println);
        allTopics.forEach(System.out::println);


    }
}
