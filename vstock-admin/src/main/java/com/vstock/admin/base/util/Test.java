package com.vstock.admin.base.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by xiangyu on 2016/5/10.
 */
public class Test implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    //当前页面url
    String urls = "";
    String nowUrl = "";
    String getHtmlResult = "";

    @Override
    public void process(Page page) {
        //当前 url
        nowUrl = page.getUrl().toString();
        //创建 ChromeDriver 环境，建立chrome内核模拟浏览器
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(nowUrl);
        WebElement webElement = driver.findElement(By.xpath("/html"));
        //打印搜索到的页面 System.out.println(webElement.getAttribute("outerHTML"));
        //获取页面
        getHtmlResult = webElement.getAttribute("outerHTML");
        //获取所有鞋类商品url
        List<String> urlList = Html.create(getHtmlResult).links().regex("(//\\w+.\\w+.com\\/item.htm\\?.*?id=([\\d]+).*?)").all();

        page.addTargetRequests(urlList);
        page.putField("author", Html.create(getHtmlResult).regex("https://s.taobao\\.com/search?q=(\\w+).*").toString());
        page.putField("name", Html.create(getHtmlResult).xpath("//h3[@class='tb-main-title']/text()").toString());
        if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 关闭窗口，释放资源。
        driver.close();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new Test())
                //从https://github.com/code4craft开始抓
                .addUrl("https://nike.tmall.com/category-1007447067.htm?spm=a1z10.5-b.w4006-2637950475.3.R6MezQ&search=y&catName=%C4%D0%D7%D3%D0%AC%C0%E0&scene=taobao_shop#bd")
                //设置Scheduler，使用Redis来管理URL队列
                //.setScheduler(new RedisScheduler("localhost"))
                //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //开启5个线程同时执行
                .thread(5)
                //启动爬虫
                .run();
    }
}
