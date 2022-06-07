package com.lsy.algorithm;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * 根据自定义字段进行加密
 */
public class Md5Sha {
    /**
     * 生成32位
     * 如果是两个随机的值进行md5加密，碰撞率大概在2的-128次方。
     */
    @Test
    public void md5(){
        String text = "人民日报刘思远";
        String md5 = DigestUtils.md5Hex(text);
        String sha1Hex = DigestUtils.sha1Hex(text);
        System.out.println(md5.length());
        System.out.println(md5);
    }

    /**
     * 40位
     */
    @Test
    public void sha1Hex(){
        String text = "人民日报刘思远";
        String sha1Hex = DigestUtils.sha1Hex(text);
        System.out.println(sha1Hex.length());
        System.out.println(sha1Hex);
    }

    @Test
    public void sha1(){
        String text = "人民日报刘思远";
        byte[] sha1 = DigestUtils.sha1(text);
        System.out.println(sha1.length);
        for (int i = 0; i < sha1.length; i++) {
            System.out.println(sha1[i]);
        }
    }
}
