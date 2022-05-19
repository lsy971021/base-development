package com.lsy.springboot.service.impl;

import com.lsy.springboot.entity.Department;
import com.lsy.springboot.repository.DepartmentRepository;
import com.lsy.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public void test(){
        List<Department> all = departmentRepository.findAll();
//        System.out.println(departmentRepository.getDepartmentByAge(18));
        System.out.println(all);
    }
}
