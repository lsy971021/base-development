package com.lsy.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.ibatis.reflection.MetaObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 必须与数据库字段一一对应
 */
@Data
public class User implements Serializable {
    private Long id;

    /**
     * 当数据库字段字符集为 uft8时，一些字符插入会报错，如：𬍛， 需要修改成utf8mb4字符集
     * utf8能够存下大部分中文汉字，mysql支持的 utf8 编码最大字符长度为 3 字节，如果遇到 4 字节的宽字符就会插入异常了。三个字节的 UTF-8 最大能编码的 Unicode 字符是 0xffff，
     * 也就是 Unicode 中的基本多文种平面(BMP)。也就是说，任何不在基本多文本平面的 Unicode字符，都无法使用 Mysql 的 utf8 字符集存储，包括 Emoji 表情(Emoji是一种特殊的 Unicode 编码，
     * 常见于 ios 和 android 手机上)，和很多不常用的汉字，以及任何新增的 Unicode 字符等等(utf8的缺点)。
     */
//    @TableField(updateStrategy = FieldStrategy.IGNORED)
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
     * FieldFill.INSERT: 在新增时生效（自动调用 insertFill(MetaObject metaObject)方法）  ！！！！ insert整个user对象时才会生效
     * FieldFill.INSERT_UPDATE: 在新增或更新时均生效  ！！！！ updateByEntity/byId 时才会生效
     * FieldFill.UPDATE: 在更新时生效（自动调用 updateFill(MetaObject metaObject)方法）
     *
     * 需要实现元对象处理器接口MetaObjectHandler  {@link com.lsy.config.MpHandler#insertFill(MetaObject)}
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * @see com.lsy.config.MpHandler#updateFill(MetaObject)
     */
    @TableField(fill = FieldFill.UPDATE,update = "now()")
    /**
     * 当此字段为null时不向前段返回
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updateTime;

    /**
     * @TableField(.. , update="now()") 使用数据库时间
     * 输出 SQL 为：update 表 set 字段=now() where ...
     */
    @TableField(update = "now()")
    private LocalDateTime modTime;

    private Integer del;

}
