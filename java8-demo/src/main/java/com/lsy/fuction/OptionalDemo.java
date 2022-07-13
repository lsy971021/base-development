package com.lsy.fuction;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * java8容器类
 * Optional.of(T t) : 创建一个 Optional 实例,t不能为null
 * Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * Optional.empty() : 创建一个空的 Optional 实例
 * isPresent() : 判断是否包含值
 * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
 * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class OptionalDemo {


    @Test
    public void test() {
        Map<String, String> map = null;
        Map<String, String> elseMap = new HashMap<>();
        elseMap.put("key", "value");

        //若 参数 不为 null,创建 Optional 实例,否则创建空实例
        Optional<Map<String, String>> optional = Optional.ofNullable(map);

        //判断是否包含值
        System.out.println(optional.isPresent());

        //如果调用对象包含值，返回该值，否则返回elseMap
        map = optional.orElse(elseMap);
        System.out.println(map);

        //如果调用对象包含值，返回该值，否则返回 s 获取的值 (同上)
        Map<String, String> same = optional.orElseGet(() -> elseMap);
        System.out.println(same);


        //创建一个 Optional 实例,参数不能为null
        optional = Optional.of(elseMap);
        System.out.println(optional.isPresent());

        Optional<String> result = optional.map(x -> {
            String key = x.get("key");
            if (StringUtils.isNotBlank(key)) {
                return "success";
            }
            return "fail";
        });

        System.out.println(result);

    }
}
