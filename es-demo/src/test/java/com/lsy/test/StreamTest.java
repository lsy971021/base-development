package com.lsy.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        list.forEach((x)-> System.out.println(x));
        Stream<String> stream = list.stream().filter(element -> element.contains("王"));
//        FuctionDemo<String> fuctionDemo = (x)->(System.out::println);
        test(x->{
            return -1;
        },(x)->{
            x.toUpperCase();
        });
        stream.forEach(System.out::println);
        Supplier supplier = ()->{
          return "aaa";
        };
        System.out.println(supplier.get()+"-----");
    }
    public static void test(Comparable comparable, Consumer<String> consumer){
        System.out.println("=====");

        Supplier<StreamTest> testSupplier = StreamTest::new;
        Supplier<String> s = ()-> "ss";
        System.err.println(testSupplier.get());
    }

//    @Test
    public void streamFilter(){
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        list.forEach((x)-> System.out.println(x));
        System.out.println("----------------");
        list.stream().filter((x)->(x.equals("王力宏"))).forEach(System.out::println);
    }

//    @Test
    public void streamMap(){
        List<Map<String,Object>> list  = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("test",i);
            map.put("hello",i+"!!!");
            list.add(map);
        }
        List<List<Object>> test = Stream.of(list).map((x) -> (x.stream().map((s) -> (s.get("test"))).collect(Collectors.toList()))).collect(Collectors.toList());
        List<Object> test1 = Stream.of(list).map((x) -> (x.stream().map((s) ->
                (s.get("test"))).collect(Collectors.toList()))).flatMap(StreamTest::flatMapTest).collect(Collectors.toList());
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
}
