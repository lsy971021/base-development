package com.lsy.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * 必须与数据库字段一一对应
 */
@Data
public class User {
    private Long id;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String name;
    /**
     * 其中 %s 会填充为字段
     * 输出 SQL 为：update 表 set 字段=字段+1 where ... (即 在原字段值基础上+1，手动赋得值无效)
     */
    @TableField(update = "%s+1")
    private Integer age;
    /**
     * mybatisplus 更新时默认对值为null的属性不处理（即若某属性为null，不更新这个字段，可能造成若要更新某个字段为null失败）
     * 需要加此注解和属性才能生效
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String email;

    /**
     * java.util.Date的大多数方法已经过时
     * java.util.Date的输出可读性差
     * java.util.Date对应的格式化类SimpleDateFormat是线程不安全的类。阿里巴巴开发手册中禁用static修饰SimpleDateFormat。
     * LocalDateTime 对应的格式化类DateTimeFormatter是线程安全的
     */

    /**
     * 阿里巴巴的开发手册中建议每个数据库表必须要有create_time 和 update_time字段，我们可以使用自动填充功能维护这两个字段
     * FieldFill.INSERT: 在新增时生效（自动调用 insertFill(MetaObject metaObject)方法）
     * FieldFill.INSERT_UPDATE: 在新增或更新时均生效
     * FieldFill.UPDATE: 在更新时生效（自动调用 updateFill(MetaObject metaObject)方法）
     *
     * 需要实现元对象处理器接口MetaObjectHandler  {@link com.lsy.config.MpHandler#insertFill(MetaObject)}
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * @see com.lsy.config.MpHandler#updateFill(MetaObject)
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * @TableField(.. , update="now()") 使用数据库时间
     * 输出 SQL 为：update 表 set 字段=now() where ...
     */
    @TableField(update = "now()")
    private LocalDateTime modTime;

    /**
     * exist=false:表示当前属性不是数据库的字段，但在项目中必须使用，这样在对该属性赋值时，mybatis-plus就会忽略这个，不会报错。
     *       否则会报一个如下的异常。
     *       ###Error querying database. Cause:com.mysql.jdbc.exception.jdbc4.MySQLSyntaxErrorException:Unknown column ‘***’ 'in field list’
     */
    @TableField(exist = false)
    private String comment;
}
