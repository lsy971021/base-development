package com.lsy.test;

/**
 * 在此根目录下 .../maven 执行 mvn clean package/install，生成的jar 不能直接运行  java -jar xxx.jar
 * jar -xvf xxx.jar 解压jar包
 * 因为带有main方法的类信息不会添加到manifest中（打开jar文件中META_INF/MANIFEST.MF文件，将无法看到Main-Class一行）
 * 为了生成可执行的jar，需要借助maven-jar-plugin
 * <plugin>
 *    <groupId>org.apache.maven.plugins</groupId>
 *    <artifactId>maven-jar-plugin</artifactId>
 *    <configuration>
 *        <archive>
 *            <manifest>
 *                <mainClass>com.lsy.test.Test</mainClass>
 *            </manifest>
 *        </archive>
 *    </configuration>
 * </plugin>
 *
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
