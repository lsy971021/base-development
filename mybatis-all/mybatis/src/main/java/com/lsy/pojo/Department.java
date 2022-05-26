package com.lsy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Department implements Serializable {

    private int id;

    private String name;

    private int age;

    private int gender;

    private String team;

    private Date createTime;

}
