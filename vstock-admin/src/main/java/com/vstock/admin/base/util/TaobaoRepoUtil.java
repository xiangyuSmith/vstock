package com.vstock.admin.base.util;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by xiangyu on 2016/5/5.
 * 数据收集日志
 */
@TargetUrl("https://github.com/\\w+/\\w+")@HelpUrl("https://github.com/\\w+")
public class TaobaoRepoUtil {

    @ExtractBy(value = "//h1[@class='entry-title public']/strong/a/text()", notNull = true)
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    public String getName() { return name; }

    public void setName(String name) { this.name = name;}

    public String getAuthor() { return author;}

    public void setAuthor() { this.author = author; }

    public String getReadme() { return readme; }

    public void setReadme () { this.readme = readme; }

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000)
                , new ConsolePageModelPipeline(), TaobaoRepoUtil.class)
                .addUrl("https://github.com/code4craft").thread(5).run();
    }
}
