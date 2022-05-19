package com.lsy.mapper;

import com.lsy.pojo.Department;
import com.lsy.util.Page;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 利用mybatis的mapper代理开发dao
 * 只用写mapper接口和mapper.xml映射文件，即可自动生成mapper接口实现类代理对象，即自动生成SqlSessionTest类似类
 * 规范：
 * 1 mapper.java接口名 跟 mapper.xml映射文件同名并且在同一个包下(若不在同一包下需在sqlMapperConfig中的mappers标签下配置路径)
 * 2 mapper.xml映射文件中，namespace="mapper接口地址" --完全包名.mapper接口名
 * 3 mapper.java接口中的方法名和mapper.xml中的statement的id值一致
 * 4 mapper.java接口中的方法参数类型/返回值类型 和 mapper.xml中statement的parameterType/result指定类型一致
 * 5 SqlMapperConfig.xml 配置文件中 -- 由于使用mapper代理方式，改为加载mapper接口!（需在sqlMapperConfig中的mappers下开启扫包）
 */
public interface DepartmentMapper {

    List<Department> findAll();

    /**
     * 用于mybatis分页插件
     * @return
     */
    List<Department> findByRowBounds(RowBounds rowBounds);

    Department findById(int id);

    Department findByName(String name);

    int insert(Department department);

    int countAll();

    List<Department> findByPage(Page page);
}
