package com.lsy.JVM;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class ClassLoaderTest {

    public static void main(String[] args) {
        try {
            URL url = ClassLoaderTest.class.getClassLoader().getResource("test.txt");
            System.out.println("绝对路径="+url.getPath());

            //当用java -jar 启动时不存在绝对路径时用此方法获取内容
            //用当前类加载器获取resource
            InputStream stream = ClassLoaderTest.class.getClassLoader().getResourceAsStream("test.txt");
            //类加载器
            URL resource1 = ClassLoader.getSystemResource("test.txt");
            File file = new File(resource1.toURI());
            System.out.println("文件上次修改时间戳="+file.lastModified());
            System.out.println(resource1.toURI());
            System.out.println(resource1.getPath());
        } catch (URISyntaxException e) {
            System.out.println(1111);
        }
    }
}
