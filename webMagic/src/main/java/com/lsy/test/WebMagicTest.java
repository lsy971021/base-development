package com.lsy.test;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Scheduler默认使用内存队列(当url少时候使用)
 * QueueScheduler是内存队列
 * FileCacheQueueScheduler 使用文件保存抓取url，需指定路径，会创建urls.txt和cursor.txt两个文件
 */
public class WebMagicTest implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {

        //抓取所有url
        //List<String> allLinks = page.getHtml().links().all();

        //向scheduler对象中添加新的目标url（抓取列表）， 将链接地址添加到访问队列中
//        page.addTargetRequests(allLinks);

        //get()取一个元素，all取所有的，xpath解析
        //List<String> all = page.getHtml().xpath("//div[@class='j_nolist']/text()").all();
        //dom解析
        Document document = page.getHtml().getDocument();
//        List<String> all = page.getHtml().xpath("//div[@class='e']/a[1]/@href]").all();
        Elements jobList = document.getElementsByClass("tags");

        page.putField("jobLists",jobList.text());


        //把解析的结果发送给pipeline  map结构,默认发送到控制台
        /*ResultItems resultItems = page.getResultItems();
        resultItems.put("title",page.getHtml());*/

        //和resultItems使用一样
//        page.putField("title",page.getHtml());


    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WebMagicTest())
                //.setDownloader(new HttpClientDownloader())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://search.51job.com/list/010000,000000,0000,32%252C38,9,99,java,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=")
                //开启5个线程抓取
                .thread(5)
                //添加文件输出的pipeline
                //FilePipeline 向磁盘输出 ； consolePipleline 向控制台输出 ； 也可自定义Pipleline
                .addPipeline(new FilePipeline("/Volumes/DATA/other"))
                //启动爬虫
                .run();
    }

}
