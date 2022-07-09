package com.lsy.method;

import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * java 8 全新日期时间 api
 * Date如果不格式化，打印出的日期可读性差
 * Tue Sep 10 09:34:04 CST 2019
 * 使用SimpleDateFormat对时间进行格式化，但SimpleDateFormat是线程不安全的
 */
public class LocalDateTimeApi {

    /**
     * 验证SimpleDateFormat为非线程安全的
     *
     * 源码中的 calendar.setTime(date); （calendar为成员变量，高并发下会有线程安全问题）
     * @see SimpleDateFormat#format(Date, StringBuffer, FieldPosition)
     */
    public void checkSDFNotThreadSafety(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //多线程下 format为非线程安全的
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                sdf.format(new Date());
            }).start();
        }

    }

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
        LocalDate customDate = LocalDate.of(2022, Month.JULY, 10);
        //2022-07-10
        System.out.println(customDate);
        System.out.println("year="+customDate.getYear()+"month="+customDate.getMonthValue()+"day="+customDate.getDayOfMonth());
        //和上面一样
        System.out.println("year="+customDate.get(ChronoField.YEAR)+"month="+customDate.get(ChronoField.MONTH_OF_YEAR)+"day="+customDate.get(ChronoField.DAY_OF_MONTH));
    }

    /**
     * 只获取十分秒等
     */
    @Test
    public void time(){
        LocalTime nowTime = LocalTime.now();
        System.out.println(nowTime);
        LocalTime customTime = LocalTime.of(10, 21);
        System.out.println(customTime);
    }

    /**
     * 获取秒数
     */
    @Test
    public void instant(){
        Instant now = Instant.now();
        //只是为了获取秒数或者毫秒数，使用System.currentTimeMillis()来得更为方便
        //获取秒数 时间戳
        long second = now.getEpochSecond();
        System.out.println(second);
        //获取毫秒数 时间戳
        long milli = now.toEpochMilli();
        System.out.println(milli);
    }

    /**
     * 获取年月日时分秒等
     */
    @Test
    public void dateTime(){
        LocalDateTime nowTime = LocalDateTime.now();
        //2022-07-09T23:22:44.747
        System.out.println("NowTime="+nowTime);
        System.out.println("year="+nowTime.getYear()+"month="+nowTime.getMonthValue()+"day="+nowTime.getDayOfMonth());

        //修改当前日期

        //+1日
        System.out.println("+1Day="+nowTime.plusDays(1));
        //+1年
        System.out.println("+1Year="+nowTime.plus(1, ChronoUnit.YEARS));
        //-1月
        System.out.println("-1Month="+nowTime.minusMonths(1));
        //-1h
        System.out.println("-1H="+nowTime.minus(1, ChronoUnit.HOURS));


        //同上
        System.out.println("year="+nowTime.get(ChronoField.YEAR)+"month="+nowTime.get(ChronoField.MONTH_OF_YEAR)+"day="+nowTime.get(ChronoField.DAY_OF_MONTH));
        //自定义日期
        LocalDateTime customDateTime = LocalDateTime.of(2022, 7, 10, 19, 22, 11);
        System.out.println("自定义日期="+customDateTime);
    }

    /**
     * 日期格式转换
     */
    @Test
    public void format(){
        LocalDate now = LocalDate.now();
        //自定义日期格式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("自定义日期格式="+now.format(dateTimeFormatter));
        System.out.println("自定义日期格式1="+now.format(DateTimeFormatter.ISO_DATE));
    }

    /**
     * 日期格式解析
     */
    @Test
    public void parse(){
        //和SimpleDateFormat相比，DateTimeFormatter是线程安全的
        LocalDate parse = LocalDate.parse("20221021",DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(parse);
    }

}
