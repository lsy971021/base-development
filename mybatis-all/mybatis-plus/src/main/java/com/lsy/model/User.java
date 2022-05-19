package com.lsy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
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
