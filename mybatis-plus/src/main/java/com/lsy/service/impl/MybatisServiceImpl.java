package com.lsy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsy.model.User;
import com.lsy.mapper.UserMapper;
import com.lsy.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    private UserMapper userMapper;

    public void test(){
        System.out.println("====="+userMapper);
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void findBySomething(String something) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getAge,something);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Override
    public void updateBySomething(User user) {

    }

    @Override
    public void deleteBySomething(String something) {

    }
}
