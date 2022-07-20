package com.lsy.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsy.model.User;

import java.util.List;

/**
 * 当使用的数据源为spring.datasource.dynamic.primary默认指定的数据源时可省略
 */
@DS("master")
public interface UserMapper extends BaseMapper<User>{
    List<User> find();
}
