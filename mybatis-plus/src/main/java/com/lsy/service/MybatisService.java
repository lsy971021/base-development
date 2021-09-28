package com.lsy.service;

import com.lsy.model.User;

public interface MybatisService {
    void test();

    void save(User user);

    void findBySomething(String something);

    void updateBySomething(User user);

    void deleteBySomething(String something);
}
