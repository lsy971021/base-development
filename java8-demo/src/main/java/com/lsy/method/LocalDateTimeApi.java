package com.lsy.method;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * java 8 全新日期时间 api
 * Date如果不格式化，打印出的日期可读性差
 * Tue Sep 10 09:34:04 CST 2019
 * 使用SimpleDateFormat对时间进行格式化，但SimpleDateFormat是线程不安全的
 */
public class LocalDateTimeApi {

    /**
     * 只获取年月日
     */
    @Test
    public void date(){
        LocalDate localDateNowTime = LocalDate.now();
        System.out.println(localDateNowTime);
        //Sat Jul 09 23:22:44 CST 2022
        System.out.println(new Date());
        ////构造指定的年月日
        LocalDate customDate = LocalDate.of(2022, 7, 10);
        //2022-07-10
        System.out.println(customDate);
        System.out.println("year="+customDate.getYear()+"month="+customDate.getMonthValue()+"day="+customDate.getDayOfMonth());
        //和上面一样
        System.out.println("year="+customDate.get(ChronoField.YEAR)+"month="+customDate.get(ChronoField.MONTH_OF_YEAR)+"day="+customDate.get(ChronoField.DAY_OF_MONTH));
    }

    public void time(){
        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime);
        LocalTime customTime = LocalTime.of(10, 21);
        System.out.println(customTime);
    }

    /**
     * 获取年月日时分秒
     */
    @Test
    public void dateTime(){
        LocalDateTime nowTime = LocalDateTime.now();
        //2022-07-09T23:22:44.747
        System.out.println(nowTime);
        System.out.println("year="+nowTime.getYear()+"month="+nowTime.getMonthValue()+"day="+nowTime.getDayOfMonth());
        //同上
        System.out.println("year="+nowTime.get(ChronoField.YEAR)+"month="+nowTime.get(ChronoField.MONTH_OF_YEAR)+"day="+nowTime.get(ChronoField.DAY_OF_MONTH));

        LocalDateTime customDateTime = LocalDateTime.of(2022, 7, 10, 19, 22, 11);
        System.out.println(customDateTime);

    }

}
