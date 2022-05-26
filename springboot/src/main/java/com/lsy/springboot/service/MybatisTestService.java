package com.lsy.springboot.service;


import com.github.pagehelper.PageInfo;
import com.lsy.springboot.pojo.Department;

public interface MybatisTestService {

    PageInfo findAll();

    Department findById(Integer id);

    void insert();
}
