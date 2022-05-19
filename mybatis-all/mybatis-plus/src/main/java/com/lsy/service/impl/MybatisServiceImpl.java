package com.lsy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Override
    public void findBySomething(String something) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.likeRight(User::getEmail,something).and(x->x.isNotNull(User::getName));
        wrapper.func(x->{
            if(true) x.ge(User::getId,3);
            else x.le(User::getId,3);
        });
        wrapper.last("limit 2");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Override
    public void updateBySomething(User user) {
        LambdaUpdateWrapper<User> wrapper = new UpdateWrapper<User>().lambda();
        wrapper.eq(User::getId,user.getId());
        int update = userMapper.update(user, wrapper);
//        int update = userMapper.update(user, null);
        System.out.println(update);
    }

    @Override
    public void deleteBySomething(String something) {
        int delete = userMapper.deleteById(something);
        System.out.println(delete);
    }

    @Override
    public Page<User> page() {
        Page<User> page = new Page<>(2,3);
        return userMapper.selectPage(page,null);
    }

    @Override
    public void findBySql() {
        System.out.println(userMapper.find());
    }

}
