package com.vstock.admin.data.spider;

import com.vstock.admin.base.service.CommodityDataService;
import com.vstock.admin.base.test.Webmagic;
import com.vstock.admin.base.util.ConstUtil;
import com.vstock.admin.base.util.DateUtils;
import com.vstock.admin.base.util.ToolSpring;
import com.vstock.db.entity.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
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

import java.util.*;

/**
 * Created by xiangyu on 2016/5/5.
 * wemagic 标准模式
 */
public class TaobaoRepoPageProcessor implements PageProcessor {

    private static Logger logger = Logger.getLogger(TaobaoRepoPageProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        Spider.create(new TaobaoRepoPageProcessor())
                .addUrl("https://landaitiyu.taobao.com/")
                .thread(1)
                .run();
    }
}
