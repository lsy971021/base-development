package com.lsy.constructors;

import com.lsy.annotation.AnnotationTestV1;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ConstructorsTestV1 {
    private String name;
    private String age;

    @Test
    public void getAll() throws Exception{
        //获取class
        Class<? extends AnnotationTestV1> aClass = new AnnotationTestV1("lsy",23).getClass();
        //获取构造器
        Constructor[] declaredConstructors = aClass.getDeclaredConstructors();
        //获取实例
        AnnotationTestV1 instance = aClass.newInstance();
        //调用实例方法
        instance.test();
        //获取实例的成员变量
        Field[] declaredFields = aClass.getDeclaredFields();
        //获取age成员变量
        Field age = aClass.getField("age");
        age.set(new AnnotationTestV1(),11);
        System.out.println(age.getInt("age"));
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println("获取实例的成员变量declaredField=="+declaredFields[i].getName());
        }



        for (int i = 0; i < declaredConstructors.length; i++) {
            //构造器修饰符
            System.out.println("构造器修饰符Modifiers=="+ Modifier.toString(declaredConstructors[i].getModifiers()));
            //构造器参数
            Parameter[] parameters = declaredConstructors[i].getParameters();
            //构造器参数类型
            Class[] parameterTypes = declaredConstructors[i].getParameterTypes();
            for (int p = 0; p < parameters.length; p++) {
                System.out.println("构造器参数parameter=="+parameters[p]);
            }
            for (int p = 0; p < parameterTypes.length; p++) {
                System.out.println("构造器参数类型parameterTypes=="+parameterTypes[p].getName());
            }
        }
    }

    public String test(String name){
        return "hello "+name+"~";
    }
}
