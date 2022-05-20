package com.lsy.springboot.repository;

import com.lsy.springboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * JpaRepository<T,ID>
 * T: 对应实体类
 * ID: 主键id类型
 * <p>
 * 会自动加入容器中，因为继承了JpaRepository
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findByIdBetween(int min, int max);

    /**
     * 可自定义sql
     * !!! from 后面为 表对应的实体类，不是表明
     * @return   JPA原生返回不能用实体类接收
     */
    @Query("select d.id,d.age from Department d where d.age = ?1")
    List<Map<String,Object>> findAllByAgeBetween(int age);
}
