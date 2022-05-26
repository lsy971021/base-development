package com.lsy.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsy.springboot.mapper.DepartmentMapper;
import com.lsy.springboot.pojo.Department;
import com.lsy.springboot.service.MybatisTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MybatisTestServiceImpl implements MybatisTestService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * PageHelper分页插件
     */
    public PageInfo findAll(){
        // pageNum 为当前页码：第一页为1
        PageHelper.startPage(1,20);
        List<Department> all = departmentMapper.findAll();
        // PageInfo传入查询结果集，  里面会有 总页码，当前页码.... 信息
        PageInfo pageInfo = new PageInfo(all);
        return pageInfo;
    }

    @Override
    public Department findById(Integer id) {
        return departmentMapper.findById(id);
    }

    @Override
    public void insert() {
        Department department = new Department() {{
            setAge(18);
            setGender(1);
            setName("亦菲");
            setTeam("Princess");
            setCreatTime(new Date());
        }};
        int insert = departmentMapper.insert(department);
        System.out.println(insert==1?"插入成功":"插入失败");
    }
}
