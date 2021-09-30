package com.lsy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsy.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User>{
    List<User> find();
}
