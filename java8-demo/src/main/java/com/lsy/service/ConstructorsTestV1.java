package com.lsy.service;

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
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println("declaredField=="+declaredFields[i]);
            declaredFields[i].setAccessible(true);
            declaredFields[i].set(1,22);
            System.out.println(declaredFields[i].get(1));
        }


        for (int i = 0; i < declaredConstructors.length; i++) {
            //构造器修饰符
            System.out.println("Modifiers=="+ Modifier.toString(declaredConstructors[i].getModifiers()));
            //构造器参数
            Parameter[] parameters = declaredConstructors[i].getParameters();
            //构造器参数类型
            Class[] parameterTypes = declaredConstructors[i].getParameterTypes();
            for (int p = 0; p < parameters.length; p++) {
                System.out.println("parameter=="+parameters[p]);
            }
            for (int p = 0; p < parameterTypes.length; p++) {
                System.out.println("parameterTypes=="+parameterTypes[p].getName());
            }
        }
    }

    public String test(String name){
        return "hello "+name+"~";
    }
}
