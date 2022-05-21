package com.lsy.springboot.mapper;

import com.lsy.springboot.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 在启动类上加mapperScan就可不用在添加@Mapper注解
 */
//@Mapper
public interface DepartmentMapper {
//    @Select("select * from department where id=#{id}")
    Department findById(Integer id);

    @Select("select * from department")
    List<Department> findAll();
}
