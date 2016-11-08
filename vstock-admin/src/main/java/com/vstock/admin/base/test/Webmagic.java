package com.vstock.admin.base.test;

import com.vstock.db.entity.CommodityData;
import com.vstock.db.entity.StockxStore;
import com.vstock.db.entity.TaobaoRepo;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.log.Log;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.*;

/**
 * Created by xiangyu on 2016/6/16.
 */
public class Webmagic  implements PageProcessor {

    private static Logger logger = Logger.getLogger(Webmagic.class);

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    //当前页面url
    String nowUrl = "";
    String getHtmlResult = "";
    //初始化url
    private List<String> urlList = new ArrayList<String>();
    //获取的数据
    String name = "";
    String price ="";
    String sales = "";
    String readme = "";
    String comment ="";
    String reviewDate = "";
    String shoeColor = "";
    String colorSize = "";
    String color = "";
    String size = "";
    Map<String,Map<String,String>> colorMap;
    Map<String,String> sizePriceMap;
    private String s_name;
    String brand = "";
    String girard = "";
    String colorSort = "";


    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    CommodityData record = new CommodityData();

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        //实例化bean保存数据
        TaobaoRepo taobaoRepo = new TaobaoRepo();
        String html = page.getHtml().toString();
        //抓取链接  https://item.taobao.com/item.htm?spm=a230r.1.14.22.Opuc5J&id=528366951160&ns=1&abbucket=11#detailS
        nowUrl = page.getUrl().regex("\"https:\\\\/\\\\/aoweiman.taobao.com\"").toString();
        taobaoRepo.setAuthor(nowUrl);
        //创建 ChromeDriver 环境
        String htmlt = page.getHtml().toString();
        // 部分二：定义如何抽取页面信息，并保存下来

        //抓取链接  https://item.taobao.com/item.htm?spm=a230r.1.14.22.Opuc5J&id=528366951160&ns=1&abbucket=11#detailS
        nowUrl = page.getUrl().regex("https:\\/\\/item.taobao.com\\/item.htm?spm=[a-z0-9\\-].[a-z0-9\\-].[a-z0-9\\-].[a-z0-9\\-].[a-z0-9\\-]&id=[a-z0-9\\-]&ns=1&abbucket=11#detailS").toString();
        taobaoRepo.setAuthor(nowUrl);
        //创建 ChromeDriver 环境
        //当前 url
        nowUrl = page.getUrl().toString();
        //创建 ChromeDriver 环境，建立chrome内核模拟浏览器
        System.getProperties().setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(nowUrl);

        WebElement webElement = driver.findElement(By.xpath("/html"));
        //获取鞋类颜色
        List<WebElement> rememberColorList = new ArrayList<WebElement>();
        //获取鞋类鞋码
        List<WebElement> rememberSizeMeList = new ArrayList<WebElement>();
        //查找评论事件
        WebElement webElementPinglun = null;
        try {//J_Prop tb-prop tb-clear  J_Prop_Color
            webElementPinglun = driver.findElement(By.xpath("//div[@class='tb-tabbar-inner-wrap']/ul/li[2]/a"));
            rememberColorList = driver.findElements(By.xpath("//div[@class='tb-skin']/dl[@class='J_Prop tb-prop tb-clear  J_Prop_Color ']/dd/ul/li[not(contains(@class,'tb-out-of-stock'))]/a"));//tb-out-of-stock
            if(rememberColorList.size()!=0){
                //遍历颜色tb-out-of-stock
                colorMap = new HashMap<String,Map<String,String>>();
                for (WebElement webElement1: rememberColorList){
                    int fornum = 0;
                    if(rememberColorList.size() > 1){
                        webElement1.click();
                    }
                    sizePriceMap = new HashMap<String,String>();
                    rememberSizeMeList = driver.findElements(By.xpath("//div[@class='tb-skin']/dl[@class='J_Prop J_TMySizeProp tb-prop tb-clear  J_Prop_measurement ']/dd/ul/li[not(contains(@class,'tb-out-of-stock'))]/a"));
                    if(rememberSizeMeList.size()!=0){
                        //遍历鞋码
                        for (WebElement webElement2: rememberSizeMeList){
                            fornum++;
                            webElement2.click();
                            if(rememberColorList.size() == 1 && rememberSizeMeList.size() == 1){
                                webElement2.click();
                            }
                            //打印搜索到的页面 System.out.println(webElement.getAttribute("outerHTML"));
                            getHtmlResult = webElement.getAttribute("outerHTML");
                            //区分淘宝和天猫页面
                            String types = page.getUrl().regex("//(\\w+)+").toString();

                            if ("detail".equals(types)) {
                                price = Html.create(getHtmlResult).xpath("//div[@class='tm-promo-price']/span[@class='tm-price']/text()").toString();
                            }else if ("item".equals(types)) {
                                price = Html.create(getHtmlResult).xpath("//em[@id='J_PromoPriceNum']/text()").toString();
                                if (price == "" || price == null){
                                    price = Html.create(getHtmlResult).xpath("//em[@class='tb-rmb-num']/text()").toString();
                                }
                                size = Html.create(getHtmlResult).xpath("//div[@class='tb-skin']/dl[@class='J_Prop J_TMySizeProp tb-prop tb-clear  J_Prop_measurement ']/dd/ul/li[@class='tb-selected']/a/span/text()").toString();
                                sizePriceMap.put(size,price);
                            }
                            //判断是否为最后一个颜色(分类)，若是则将颜色(分类)置空
                            if(fornum >= rememberSizeMeList.size()){
                                webElement2.click();
                            }
                        }
                        color = Html.create(getHtmlResult).xpath("//div[@class='tb-skin']/dl[@class='J_Prop tb-prop tb-clear  J_Prop_Color ']/dd/ul/li[@class='tb-selected']/a/span/text()").toString();
                        colorMap.put(color,sizePriceMap);
                    }
                }
            }
        }catch (Exception e){
            logger.debug(e.getMessage());
        }
        JSONObject jsonObject = new JSONObject(colorMap);
        colorSize = jsonObject.toString();

        //打印搜索到的页面 System.out.println(webElement.getAttribute("outerHTML"));
        getHtmlResult = webElement.getAttribute("outerHTML");
        //获取所有匹配的url
        urlList = Html.create(getHtmlResult).links().regex("(//\\w+.\\w+.com\\/item.htm\\?.*?id=([\\d]+).*?)").all();
        page.addTargetRequests(urlList);
        //区分淘宝和天猫页面
        String types = page.getUrl().regex("//(\\w+)+").toString();
        if ("detail".equals(types)) {
            name = Html.create(getHtmlResult).xpath("//div[@class='tb-detail-hd']/h1/text()").toString();
            price = Html.create(getHtmlResult).xpath("//div[@class='tm-promo-price']/span[@class='tm-price']/text()").toString();
            sales = Html.create(getHtmlResult).xpath("//span[@class='tm-count']/text()").toString();
            if (sales.equals("")) {
                sales = Html.create(getHtmlResult).xpath("//strong[@id='J_SellCounter']/text()").toString();
            }
            readme = Html.create(getHtmlResult).xpath("//div[@id='readme']/tidyText()").toString();
            comment = Html.create(getHtmlResult).xpath("//div[@id='J_KgRate_ReviewContent tb-tbcr-content']/text()").toString();
            reviewDate = Html.create(getHtmlResult).xpath("//div[@id='tb-r-info']/text()").toString();
            shoeColor = Html.create(getHtmlResult).xpath("//div[@id='tb-r-info']/span[@class='tb-r-date']/text()").toString();
        } else if ("item".equals(types)) {
            name = Html.create(getHtmlResult).xpath("//h3[@class='tb-main-title']/text()").toString();
            price = Html.create(getHtmlResult).xpath("//em[@id='J_PromoPriceNum']/text()").toString();
            if (price == "" || price == null){
                price = Html.create(getHtmlResult).xpath("//em[@class='tb-rmb-num']/text()").toString();
            }
            sales = Html.create(getHtmlResult).xpath("//strong[@id='J_SellCounter']/text()").toString();
            readme = Html.create(getHtmlResult).xpath("//div[@id='readme']/tidyText()").toString();
            //商品详情  span[contains(text(),'text2')]
            colorSort = Html.create(getHtmlResult).xpath("//ul[@class='attributes-list']/li[1]/text()").toString().split(":")[1];
            girard = Html.create(getHtmlResult).xpath("//ul[@class='attributes-list']/li[2]/text()").toString().split(":")[1];
            brand = Html.create(getHtmlResult).xpath("//ul[@class='attributes-list']/li[3]/text()").toString().split(":")[1];
            //评论
            if(webElementPinglun!=null){
                webElementPinglun.click();
            }

        }
        taobaoRepo.setName(name);
        taobaoRepo.setPrice(price);
        taobaoRepo.setSales(sales);
        taobaoRepo.setReadme(readme);
        taobaoRepo.setAuthor(nowUrl);


        if (!name.equals("") || !price.equals("") || !sales.equals("")) {
            if (name != null || price != null || sales != null) {
                record.setStockxName(s_name);
                record.setCommodityName(name);
                record.setCommodityPrice(price);
                record.setTransactionRecord(sales);
                try {
                    System.out.print("保存数据 == = == = ");
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        }

        name = "";
        price = "";
        sales = "";

        if(taobaoRepo.getName() == null){
            page.setSkip(true);
        }else if(taobaoRepo.getAuthor() == null){
            page.setSkip(true);
        }else if(taobaoRepo.getPrice() == null){
            page.setSkip(true);
        }else if(taobaoRepo.getReadme() == null){
            page.setSkip(true);
        }else{
            page.putField("repo",taobaoRepo);
        }
        // 部分三：从页面发现后续的url地址来抓取 page.getHtml().links().regex("https:\\/\\/item.taobao.com\\/item.html").all()
        String sas = Html.create(getHtmlResult).toString();
        List<String> Url = Html.create(getHtmlResult).regex("(//\\w+.\\w+.com\\/item.htm\\?.*?id=([\\d]+).*?)").all();
        List<String> str = new ArrayList<>();
        String equ = "item.taobao.com";
        HashSet<String> hs = new HashSet<String>(Url);
        for (String temp : hs) {
            if (temp.contains(equ)) {
                str.add(temp);
            }
        }
        if (str.size() > 0) {
            page.addTargetRequests(str);
        }
        // 部分三：从页面发现后续的url地址来抓取 page.getHtml().links().regex("https:\\/\\/item.taobao.com\\/item.html").all()
        //page.addTargetRequests(page.getHtml().links().regex("https:\\/\\/item.taobao.com\\/item.htm?spm=[a-z0-9\\-].[a-z0-9\\-].[a-z0-9\\-].[a-z0-9\\-].[a-z0-9\\-]&id=[a-z0-9\\-]&ns=1&abbucket=11#detailS").all());
        // 关闭窗口，释放资源。
        driver.close();
    }

    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        StockxStore roeced = new StockxStore();
        roeced.setName("蓝带体育");
        roeced.setUrl("https://landaitiyu.taobao.com/i/asynSearch.htm?_ksTS=1463128322693_204&callback=jsonp205&mid=w-7436268858-0&wid=7436268858&path=/search.htm&search=y&spm=a1z10.1-c.w5001-7444832562.5.Nda4RT&csy=1&keyword=NIKE&pv=null&scene=taobao_shop");
        roeced.setCreate_user("admin");
        Webmagic webmagic = new Webmagic();
        webmagic.setS_name(roeced.getName());
        Spider.create(webmagic)
                .addUrl(roeced.getUrl())
                .thread(1)
                .run();

    }
}
