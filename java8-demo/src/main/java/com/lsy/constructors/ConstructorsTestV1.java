package com.lsy.constructors;

import com.lsy.annotation.CustomAnnotations;
import com.lsy.stream.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class ConstructorsTestV1 {
    private String name;
    private String age;

    @Test
    public void getAll() throws Exception{
        //获取class
//        Class<? extends User> aClass = new User("lsy",24).getClass();
        Class aClass = Class.forName("com.lsy.constructors.User");
        //获取实例
        User instance = (User) aClass.newInstance();
        //调用实例方法
        System.out.println(instance.getAge());
        //获取实例的成员变量
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println("获取实例的成员变量declaredField=="+declaredFields[i].getName());
        }
        System.out.println();

        //获取age成员变量
        Field age = aClass.getDeclaredField("age");
//        System.out.println(age.get("age"));
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            System.out.println("methodName="+method.getName());
            System.out.println("methodParameter="+method.getParameters());
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (int u = 0; u < annotations.length; u++) {
                Annotation annotation = annotations[u];
                System.out.println("methodAnnotations="+annotation.annotationType());
                CustomAnnotations customAnnotations = (CustomAnnotations) annotation;
                System.out.println(customAnnotations.newMethod());
            }
            System.out.println();
        }

        //获取构造器
        Constructor[] declaredConstructors = aClass.getDeclaredConstructors();
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
