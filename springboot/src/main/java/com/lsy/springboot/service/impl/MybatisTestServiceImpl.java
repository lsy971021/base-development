package com.lsy.springboot.service.impl;

import com.lsy.springboot.mapper.DepartmentMapper;
import com.lsy.springboot.pojo.Department;
import com.lsy.springboot.service.MybatisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisTestServiceImpl implements MybatisTestService {

    @Autowired
    private DepartmentMapper departmentMapper;


    public void findAll(){

    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }
}
