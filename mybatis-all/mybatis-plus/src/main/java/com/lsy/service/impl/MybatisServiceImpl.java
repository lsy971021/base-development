package com.lsy.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsy.model.User;
import com.lsy.mapper.UserMapper;
import com.lsy.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对同一个接口，可能会有几种不同的实现类，而默认只会采取其中一种。这种情况下 用 @Primary
 */
@Primary
@Service
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User user;
    public String test(){
        System.out.println(user.toString());
        return user.toString();
    }


    @Override
    public void save(User user) {
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Override
    public List<User> findByEmail(String email) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.like(User::getEmail, email).and(x -> x.isNotNull(User::getName));
        wrapper.func(x -> {
            if (true) x.ge(User::getId, 0);
            else x.le(User::getId, 3);
        });
        wrapper.last("limit 2");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
        return users;
    }

    /**
     * mybatisplus 更新时默认对值为null的属性不处理（即若某属性为null，不更新这个字段，可能造成若要更新某个字段为null失败）
     * 需要加此注解和属性来生效： @TableField(updateStrategy = FieldStrategy.IGNORED)
     * 修改时需转换成User对象才能生效MpHandler中的 updateFill()，手写sql可使@TableField(updateStrategy = FieldStrategy.IGNORED) 失效
     *
     * @param user
     */
    @Override
    public void updateAge(User user) {
        LambdaUpdateWrapper<User> wrapper = new UpdateWrapper<User>().lambda();
        wrapper.eq(User::getId, user.getId());
        wrapper.set(User::getAge, user.getAge());
        //若user中其他属性未赋值(即null值)，则不会更新这些值(要生效需在字段上加@TableField(updateStrategy = FieldStrategy.IGNORED)),wrapper的会生效
//        int update = userMapper.update(user, wrapper);
//        int update = userMapper.updateById(user);
        int update = userMapper.updateEmailById(user);
//        int update = userMapper.update(user, null);
        System.out.println(update);
    }

    /**
     * dynamic只有3.5.0版本以上才可以用 多数据源事务控制 @DSTransactional注解
     *
     * @param id
     */
    @DSTransactional
    @Override
    public void deleteById(Long id) {
        // 若为下面的语句，则 updateFill() 中的update不会生效
//        userMapper.deleteById(id);
        //需转换成User，然后对其修改才能生效MpHandler中的 updateFill()
        User u = new User();
        u.setId(id);
        u.setDel(1);
        userMapper.updateById(u);
        User user = userMapper.selectById(id);
        //模拟报错，测试多数据源事务
//        int i = 1 / 0;
        userMapper.saveUser(user);
        System.out.println(user);
    }

    /**
     * IPage
     *
     * @return
     */
    @Override
    public IPage<User> page() {
        // isSearchCount : 是否查询总量
        IPage<User> page = new Page(2, 3);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        // 获取查询列表
        List<User> users = userIPage.getRecords();
        //获取总页数
        long pages = userIPage.getPages();
        return userIPage;
    }

    @Override
    public void findBySql() {
        System.out.println(userMapper.find());
    }

    /**
     * pageHelper
     *
     * @return
     */
    @Override
    public PageInfo<User> pageHelper() {
        int pageIndex = 1;
        int pageSize = 10;
        //count: 是否查询count，默认开启
        //orderBy : 排序
        PageHelper.startPage(pageIndex, pageSize, "id desc");
        List<User> users = userMapper.find();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        pageInfo.setList(users);
        return pageInfo;
    }

}
