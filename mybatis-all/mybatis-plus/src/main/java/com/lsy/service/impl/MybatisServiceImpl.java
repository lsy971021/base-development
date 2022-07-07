package com.lsy.service.impl;

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

    /**
     * mybatisplus 更新时默认对值为null的属性不处理（即若某属性为null，不更新这个字段，可能造成若要更新某个字段为null失败）
     * 需要加此注解和属性来生效： @TableField(updateStrategy = FieldStrategy.IGNORED)
     * @param user
     */
    @Override
    public void updateBySomething(User user) {
        LambdaUpdateWrapper<User> wrapper = new UpdateWrapper<User>().lambda();
        wrapper.eq(User::getId,user.getId());
        wrapper.set(User::getEmail,null);
        //若user中其他属性未赋值(即null值)，则不会更新这些值,wrapper的会生效
//        int update = userMapper.update(user, wrapper);
        int update = userMapper.updateById(user);
//        int update = userMapper.update(user, null);
        System.out.println(update);
    }

    @Override
    public void deleteBySomething(String something) {
        int delete = userMapper.deleteById(something);
        System.out.println(delete);
    }

    /**
     * IPage
     * @return
     */
    @Override
    public IPage<User> page() {
        // isSearchCount : 是否查询总量
        IPage<User> page = new Page(2,3);
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
     * @return
     */
    @Override
    public PageInfo<User> pageHelper() {
        int pageIndex = 1;
        int pageSize = 10;
        //count: 是否查询count，默认开启
        //orderBy : 排序
        PageHelper.startPage(pageIndex,pageSize,"id desc");
        List<User> users = userMapper.find();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        pageInfo.setList(users);
        return pageInfo;
    }

}
