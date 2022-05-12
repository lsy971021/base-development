package com.lsy.pojo;

import lombok.Data;

@Data
public class Subject {

    private int id;

    private int did;

    private String course;

    private int grade;

    private int ranking;
    /**
     * 多表联查时对应的department表  1对1
     */
    private Department department;
}
