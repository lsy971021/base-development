package com.lsy.model;

import lombok.Data;

/**
 * 必须与数据库字段一一对应
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
