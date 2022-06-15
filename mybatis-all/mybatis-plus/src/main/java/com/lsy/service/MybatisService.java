package com.lsy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.lsy.model.User;

public interface MybatisService {
    void test();

    void save(User user);

    void findBySomething(String something);

    void updateBySomething(User user);

    void deleteBySomething(String something);

    IPage<User> page();

    void findBySql();

    PageInfo<User> pageHelper();
}
