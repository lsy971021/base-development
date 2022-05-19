package com.lsy.springboot.repository;

import com.lsy.springboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<T,ID>
 * T: 对应实体类
 * ID: 主键id类型
 *
 * 会自动加入容器中，因为继承了JpaRepository
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department getDepartmentByAge(Integer age);
}
