package com.lsy.mapper;

import com.lsy.pojo.Subject;

import java.util.List;

public interface SubjectMapper {

    List<Subject> findAll();

    Subject findById(int id);

}
