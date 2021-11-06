package com.lsy.annotation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.model.User;

public interface MybatisService {
    void test();

    void save(User user);

    void findBySomething(String something);

    void updateBySomething(User user);

    void deleteBySomething(String something);

    Page<User> page();

    void findBySql();
}
