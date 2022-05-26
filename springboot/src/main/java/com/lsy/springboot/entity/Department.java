package com.lsy.springboot.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

// jpa 需要加的注解：
//          @Entity：跟数据库关联为实体类     ==== 必须要加此注解
//          @Table(name = "表名字") ： 标注数据库的表名
//          @Id ： 标注表的主键id          ==== 必须要加此注解
//          @GeneratedValue(strategy = 策略)： 标注主键生成策略
//          @Column(name="数据库字段")：当实体的属性与其映射的数据库表的列不同名时需要使用（可与@Id一起使用）
//              columnDefinition： 这个属性表示该字段在数据库中的实际类型，通常 ORM 框架可以根据属性类型自动判断数据库中字段的类型,但是对于Date类型仍无法确定数据库中字段类型究竟是DATE,TIME还是TIMESTAMP.此外,String的默认映射类型为VARCHAR, 如果要将 String 类型映射到特定数据库的 BLOB 或TEXT 字段类型.
//          @Transient : 表示不需要映射的列，ORM框架将忽略该属性.
@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    private int age;

    private int gender;

    private String team;

    private Date createTime;

    @Transient
    private String other;
}
