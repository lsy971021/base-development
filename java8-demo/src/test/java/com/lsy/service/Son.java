package com.lsy.service;

public class Son extends Father{
    private String name;
    private int age;
    public Son(){
        System.out.println("son空参构造");
    }

    @Override
    public void happy() {
        System.out.println("son is happy");
    }
}
