package com.lsy.annotation;

import java.lang.annotation.*;

//说明该注解将被包含在javadoc中
@Documented
//@Target 注解的作用目标:
//ElementType.TYPE)——接口、类、枚举、注解
//ElementType.FIELD)——字段、枚举的常量
//ElementType.METHOD)——方法
//ElementType.PARAMETER)——方法参数
//ElementType.CONSTRUCTOR) ——构造函数
//ElementType.LOCAL_VARIABLE)——局部变量
//ElementType.ANNOTATION_TYPE)——注解
//ElementType.PACKAGE)——包
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
//@Retention：注解的保留位置
//RetentionPolicy.SOURCE:这种类型的Annotations只在源代码级别保留,编译时就会被忽略,在class字节码文件中不包含。
//RetentionPolicy.CLASS:这种类型的Annotations编译时被保留,默认的保留策略,在class文件中存在,但JVM将会忽略,运行时无法获得。
//RetentionPolicy.RUNTIME:这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用。
@Retention(RetentionPolicy.RUNTIME)
//@Inherited：说明子类可以继承父类中的该注解
@Inherited
public @interface CustomAnnotations {
    String newMethod() default "DEFAULT";
}
