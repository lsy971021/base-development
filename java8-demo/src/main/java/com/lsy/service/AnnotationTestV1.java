package com.lsy.service;


import java.lang.annotation.Documented;

public class AnnotationTestV1 {
    String name;
    int age;

    public AnnotationTestV1() {
    }

    public AnnotationTestV1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //可生成javadoc
    @Documented
    public @interface DocumentedAnnotation {
    }
    @DocumentedAnnotation
    public void annTest(){
        System.err.println(11111);
    }

    public void test(){
        System.out.println("got!!");
    }
}
