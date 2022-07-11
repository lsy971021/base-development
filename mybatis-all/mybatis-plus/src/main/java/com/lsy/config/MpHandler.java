package com.lsy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 *  entity中对字段有@TableField 且 属性 有 fill 进行处理
 *
 */
@Slf4j
@Component
public class MpHandler implements MetaObjectHandler {

    /**
     * 对fill=FieldFill.INSERT 进行处理
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //当实体类中有此字段的填充属性（setter），避免填充时开销过大
        boolean has = metaObject.hasSetter("createTime");
        if(!has) {
            log.info("createTime自动赋值失败，无setter方法");
            return;
        }
        //判断实体类中字段时候被赋值
        Object createTime = metaObject.getValue("createTime");
        if(createTime!=null){
            log.info("createTime以被赋值");
            return;
        }
        //参数1： 参数
        //参数2： 字段名
        //参数3： 字段class类型
        //参数4： 对字段赋值
//        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        setFieldValByName("createTime",LocalDateTime.now(),metaObject);
    }

    /**
     * 对fill=FieldFill.UPDATE(INSERT_UPDATE) 进行处理
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        boolean has = metaObject.hasSetter("updateTime");
        if(!has) {
            log.info("updateTime自动赋值失败，无setter方法");
            return;
        }
        //判断实体类中字段时候被赋值
        Object createTime = metaObject.getValue("updateTime");
        if(createTime!=null){
            log.info("updateTime以被赋值");
            return;
        }
//        strictUpdateFill(metaObject,"updateTime",LocalDateTime.class, LocalDateTime.now());
        setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
    }
}
