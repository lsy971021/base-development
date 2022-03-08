package com.lsy.test.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DomDemo {
    public static void main(String[] args) {
        send();
    }
    public static void send(){
        try {
            Document document = Jsoup.connect("http://www.hshfy.sh.cn/shfy/gweb2017/index_flws.html").get();
            Elements info = document.getElementsByClass("t_span1 float_a");
            String text = info.text();
            System.out.println(text.substring(4, text.indexOf('篇')));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
