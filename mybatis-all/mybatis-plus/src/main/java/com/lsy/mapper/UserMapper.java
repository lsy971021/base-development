package com.lsy.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsy.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 当使用的数据源为spring.datasource.dynamic.primary默认指定的数据源时可省略
 * 注解可以写在方法上，也可以写类上，遵循就近原则
 */
@DS("master")
public interface UserMapper extends BaseMapper<User>{
    List<User> find();
    @DS("master")
    @Update("update user set del=1 where id=#{id}")
    int deleteById(Long id);

    @DS("prod")
    @Insert("insert into new_user values(null,#{name},#{age},#{email},#{createTime},#{updateTime},#{modTime},0)")
    int saveUser(User user);

    @Update("update user set age=#{age},update_time=#{updateTime} where id=#{id}")
    int updateEmailById(User user);
}
