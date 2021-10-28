package com.lsy.service;

public class Father implements Person{
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

    @Override
    public void happy() {
        System.out.println("father is happy");
    }
}
