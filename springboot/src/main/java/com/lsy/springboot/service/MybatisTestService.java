package com.lsy.springboot.service;


import com.lsy.springboot.pojo.Department;

public interface MybatisTestService {

    void findAll();

    Department findById(Integer id);
}
