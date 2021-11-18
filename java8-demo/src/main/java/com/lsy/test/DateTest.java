package com.lsy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        /*Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        System.out.println(format);
        System.out.println(date);
        System.out.println(date.getTime());*/
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,1);//这里改为1
        Date time=cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String format = sdf.format(time);
        Date parse = null;
        try {
             parse = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(parse);
        System.out.println(parse.getTime());
    }
}
