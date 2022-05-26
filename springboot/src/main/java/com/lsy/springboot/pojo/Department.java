package com.lsy.springboot.pojo;

import lombok.Data;

import java.util.Date;

/**
 * mybatis用
 */
@Data
public class Department {
    private int id;

    private String name;

    private int age;

    private int gender;

    private String team;

    private Date creatTime;

}
