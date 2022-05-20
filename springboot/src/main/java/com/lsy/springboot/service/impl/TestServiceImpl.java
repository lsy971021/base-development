package com.lsy.springboot.service.impl;

import com.lsy.springboot.entity.Department;
import com.lsy.springboot.repository.DepartmentRepository;
import com.lsy.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public void test(){
        //自带方法
        List<Department> all = departmentRepository.findAll();
//        System.out.println(departmentRepository.getDepartmentByAge(18));
        //自定义方法，要符合jpa的自定义命名规范，会自动生成sql，只需在repository中新增一个方法就可以
        departmentRepository.findByIdBetween(1,3);
        //手动创建sql
        departmentRepository.findAllByAgeBetween(18);
        System.out.println(all);
    }
}
