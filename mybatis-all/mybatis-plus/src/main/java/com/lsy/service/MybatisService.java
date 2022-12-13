package com.lsy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.lsy.mapper.UserMapper;
import com.lsy.model.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface MybatisService {
    // 子类若包含@Service、@Component等注解，接口默认方法的@Bean也会被识别到，并进行注入
    @Bean
    default User user1() {
        return new User();
    }

    String test();

    void save(User user);

    List<User> findByEmail(String something);

    void updateAge(User user);

    void deleteById(Long id);

    IPage<User> page();

    void findBySql();

    PageInfo<User> pageHelper();
}
