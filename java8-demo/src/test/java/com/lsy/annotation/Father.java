package com.lsy.annotation;

public class Father {
    private String name;
    private int age;

    public Father() {
        System.out.println("father空参构造");
    }

    public Father(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("father全参构造");
    }

}
