package com.lsy;

import org.aspectj.weaver.bcel.ExtensibleURLClassLoader;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import static org.springframework.core.io.support.SpringFactoriesLoader.FACTORIES_RESOURCE_LOCATION;

public class Anything {

    @Test
    public void test() throws Exception{
//        Enumeration<URL> resources = ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION);
//        Enumeration<URL> resources = ClassLoader.getSystemResources("test.txt");
        URL resource1 = ExtensibleURLClassLoader.getSystemResource("test.txt");
        System.out.println(resource1.getPath());

    }
}
