package com.lsy.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsy.springboot.mapper.DepartmentMapper;
import com.lsy.springboot.pojo.Department;
import com.lsy.springboot.service.MybatisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MybatisTestServiceImpl implements MybatisTestService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * PageHelper分页插件
     */
    public void findAll(){
        // pageNum 为当前页码：第一页为1
        PageHelper.startPage(2,2);
        List<Department> all = departmentMapper.findAll();
        // PageInfo传入查询结果集，  里面会有 总页码，当前页码.... 信息
        PageInfo pageInfo = new PageInfo(all);
        System.out.println(pageInfo);
    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }
}
