package com.vstock.admin.data.spider;

import com.vstock.ext.util.ConstUtil;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.ToolSpring;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

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
