package com.lsy.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsy.model.User;

import java.util.List;

@DS("master")
public interface UserMapper extends BaseMapper<User>{
    List<User> find();
}
