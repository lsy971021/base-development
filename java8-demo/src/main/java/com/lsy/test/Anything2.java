package com.lsy.test;

import org.junit.Test;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class Anything2 {

    @Test
    public void test(){
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("file.txt");

        Map<String, String> getenv = System.getenv();
        Map properties = System.getProperties();
        System.out.println(getenv);
        System.out.println(properties);
    }
}
