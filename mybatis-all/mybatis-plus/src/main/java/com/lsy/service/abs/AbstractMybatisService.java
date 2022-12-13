package com.lsy.service.abs;

import com.lsy.config.MyFactoryBean;
import com.lsy.mapper.UserMapper;
import com.lsy.model.User;
import com.lsy.params.req.SaveParams;
import com.lsy.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractMybatisService implements MybatisService {
    @Autowired
    // @Qualifier 可加可不加，里面的值 & 可加可不加 （& + beanName）
    @Qualifier("&myFactoryBean")
    protected MyFactoryBean myFactoryBean;
    @Autowired
    // 要加，通过factoryBean获取对象
    @Qualifier("myFactoryBean")
    protected SaveParams saveParams1;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected User user;
    @Autowired
    @Qualifier("test")
    protected SaveParams saveParams;

}
