package com.vstock.spider.data.spider;

import com.vstock.db.entity.*;
import com.vstock.ext.util.ConstUtil;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.ToolSpring;
import com.vstock.spider.data.service.BasicinformationService;
import com.vstock.spider.data.service.CommodityDataService;
import com.vstock.spider.data.service.StockxStoreService;
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
import java.util.concurrent.TimeUnit;

/**
 * Created by xiangyu on 2016/5/5.
 * wemagic 标准模式
 */
public class GithubRepoPageProcessor implements PageProcessor {

    private static Logger logger = Logger.getLogger(GithubRepoPageProcessor.class);

    private static StockxStore stockxStore;
    private static int xnum = 0;
    private String s_name;
    //当前页面url
    private String nowUrl = "";
    private String getHtmlResult = "";
    private String getHtmlResultColor = "";
    private static List<StockxStore> stockxStoreList = new ArrayList<StockxStore>();
    List<String> str = new ArrayList<>();
    List<String> recrawlingUrl = new ArrayList<String>();
    List<String> recordData = new ArrayList<String>();
    Map<String, Map<String, String>> colorMap;
    Map<String, String> sizePriceMap;
    private String name = "";
    private String price = "";
    private String sales = "";
    private String readme = "";
    private String colorSize = "";
    private String color = "";
    private String size = "";
    private String brand = "";
    private String girard = "";
    private String colorSort = "";
    private String colorType = "";
    int i = 1;
    int x = 0;

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        CommodityDataService commodityDataService = (CommodityDataService) ToolSpring.getBean("commodityData");
        StockxStoreService stockxStoreService = (StockxStoreService) ToolSpring.getBean("stockxStore");
        BasicinformationService basicinformationService = (BasicinformationService) ToolSpring.getBean("basicinformation");
        //实例化bean保存数据
        TaobaoRepo taobaoRepo = new TaobaoRepo();
        //当前 url
        if (i == 1) {
            if (xnum < stockxStoreList.size()) {
                nowUrl = stockxStoreList.get(xnum).getUrl();
//                nowUrl="http://item.taobao.com/item.htm?id=4547349024";
                stockxStore = stockxStoreList.get(xnum);
                s_name = stockxStoreList.get(xnum).getName();
            } else {
                nowUrl = "";
                xnum = 0;
            }
        } else {
            if (x < str.size()) {
                //判断获取的值是否是最后一个
                if (str.size() - x > 1) {
                    nowUrl = this.updateNowUrl(str.get(x));
                    x++;
                } else {//是最后一个值
                    //判断重复爬取是否有值
                    if (recrawlingUrl.size() > 0) {
                        recordData = new ArrayList<String>();
                        //循环去重
                        for (String duplicate : recrawlingUrl) {
                            if (!recordData.contains(duplicate)) {
                                recordData.add(duplicate);
                            }
                        }
                        recrawlingUrl = recordData;
                        recordData = new ArrayList<String>();
                        //赋值地址，去掉list中的第一条值
                        nowUrl = this.updateNowUrl(recrawlingUrl.get(0));
                        recordData.add(recrawlingUrl.get(0));
                        recrawlingUrl.remove(0);
                    } else {//最后一个地址
                        nowUrl = this.updateNowUrl(str.get(x));
                        x++;
                    }
                }
            } else {
                xnum++;
                nowUrl = "";
                x = 0;
                i = 1;
            }
        }
        if ("".equals(nowUrl)) {
            logger.info("getData exit ...");
        } else {
            logger.info("=================================================");
            logger.info("地址有值进入成功");
            logger.info("=================================================");
            //创建 ChromeDriver 环境，建立chrome内核模拟浏览器
            String chromeDriver = ConstUtil.getSpiderProperties().getProperty("chromeDriver");
            logger.info("=================================================");
            logger.info("获取谷歌地址成功！地址：" + chromeDriver);
            logger.info("=================================================");
            String sysPro = System.getProperty("webdriver.chrome.driver");
            if (!"".equals(sysPro)) {
                System.getProperties().setProperty("webdriver.chrome.driver", chromeDriver);
            }
            logger.info("=================================================");
            logger.info("给主机地址" + System.getProperty("webdriver.chrome.driver"));
            logger.info("=================================================");
            WebDriver driver = null;
            WebElement webElement = null;
            try {
                driver = new ChromeDriver();
                logger.info("=================================================");
                logger.info("启动谷歌内核成功" + driver.toString());
                logger.info("=================================================");
//                driver.get("https://item.taobao.com/item.htm?id=530842149830");
                driver.get(nowUrl);
                logger.info("=================================================");
                logger.info("赋值网页地址:" + nowUrl);
                logger.info("=================================================");
                webElement = driver.findElement(By.xpath("/html"));
                logger.info("=================================================");
                logger.info("下载网页界面........");
                logger.info("=================================================");
            } catch (Exception e) {
                logger.error("WebDriver Runtime request for nowUrl ~!!!!");
                xnum--;
            }
            int number = 0;
            //获取鞋类颜色
            List<WebElement> rememberColorList = new ArrayList<WebElement>();
            //获取鞋类鞋码
            List<WebElement> rememberSizeMeList = new ArrayList<>();
            List<WebElement> rememberSizeMeListes = new ArrayList<>();
            //查找评论事件
            WebElement webElementPinglun = null;
            try {
                //隐式等待
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                webElementPinglun = driver.findElement(By.xpath("//div[@class='tb-tabbar-inner-wrap']/ul/li[2]/a"));
            } catch (Exception e) {
                logger.debug(e.getMessage());
                System.gc();
            }
            try {
                //隐式等待
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                rememberColorList = driver.findElements(By.xpath("//div[@class='tb-skin']/dl[@class='J_Prop tb-prop tb-clear  J_Prop_Color ']/dd/ul/li[not(contains(@class,'tb-out-of-stock'))]/a"));//tb-out-of-stock
                rememberSizeMeListes = driver.findElements(By.xpath("//div[@class='tb-skin']/dl[@class='J_Prop J_TMySizeProp tb-prop tb-clear  J_Prop_measurement ']/dd/ul/li[not(contains(@class,'tb-out-of-stock'))]/a"));
                if (rememberColorList.size() != 0) {
                    //遍历颜色
                    colorType = "";
                    colorMap = new HashMap<String, Map<String, String>>();
                    for (WebElement webElement1 : rememberColorList) {
                        int fornum = 0;
                        if (rememberColorList.size() > 1) {
                            int iAttempts1 = 0;
                            while (iAttempts1 < 5) {
                                try {
                                    webElement1.click();
                                    break;
                                } catch (Exception e) {
                                    Thread.sleep(3000);
                                }
                                iAttempts1++;
                            }
                        }

                        //线程沉睡3秒
                        Thread.sleep(3000);
                        //打印搜索到的页面
                        getHtmlResultColor = webElement.getAttribute("outerHTML");
                        String[] stringsVal = new String[]{"tb-selected", "tb-txt tb-selected", "tb-txt"};
                        String colorVal = "";
                        for (String str : stringsVal) {
                            colorVal = Html.create(getHtmlResultColor).xpath("//div[@class='tb-skin']/dl[@class='J_Prop tb-prop tb-clear  J_Prop_Color ']/dd/ul/li[@class='" + str + "']/a/span/text()").toString();
                            if (colorVal != null) {
                                break;
                            }
                        }
                        if (colorVal == null || "null".equals(colorVal)) {
                            int iAttempts1 = 0;
                            while (iAttempts1 < 5) {
                                try {
                                    webElement1.click();
                                    break;
                                } catch (Exception e) {
                                    Thread.sleep(2000);
                                }
                                iAttempts1++;
                            }
                        }
                        //打印搜索到的页面
                        getHtmlResultColor = webElement.getAttribute("outerHTML");

                        sizePriceMap = new HashMap<String, String>();
                        //隐式等待
                        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        rememberSizeMeList = driver.findElements(By.xpath("//div[@class='tb-skin']/dl[@class='J_Prop J_TMySizeProp tb-prop tb-clear  J_Prop_measurement ']/dd/ul/li[not(contains(@class,'tb-out-of-stock'))]/a"));
                        if (rememberSizeMeList.size() != 0) {
                            //遍历鞋码
                            for (WebElement webElement2 : rememberSizeMeList) {
                                fornum++;
                                // Click the element
                                if (rememberSizeMeListes.size() > 1) {
                                    int iAttempts = 0;
                                    while (iAttempts < 5) {
                                        try {
                                            webElement2.click();
                                            break;
                                        } catch (Exception e) {
                                            Thread.sleep(1000);
                                        }
                                        iAttempts++;
                                    }
                                }
                                //打印搜索到的页面
                                getHtmlResult = webElement.getAttribute("outerHTML");
                                size = Html.create(getHtmlResult).xpath("//div[@class='tb-skin']/dl[@class='J_Prop J_TMySizeProp tb-prop tb-clear  J_Prop_measurement ']/dd/ul/li[@class='tb-selected']/a/span/text()").toString();
                                int iAttemptsSize = 0;
                                while (iAttemptsSize < 5) {
                                    size = Html.create(getHtmlResult).xpath("//div[@class='tb-skin']/dl[@class='J_Prop J_TMySizeProp tb-prop tb-clear  J_Prop_measurement ']/dd/ul/li[@class='tb-selected']/a/span/text()").toString();
                                    if (size != null) {
                                        break;
                                    }
                                    iAttemptsSize++;
                                }
                                if (size != null && !"".equals(size)) {
                                    size = this.shoeroue(size);
                                    size = size.replaceAll("=", "|");
                                }
                                price = Html.create(getHtmlResult).xpath("//em[@id='J_PromoPriceNum']/text()").toString();
                                if ("".equals(price) || price == null) {
                                    price = Html.create(getHtmlResult).xpath("//em[@class='tb-rmb-num']/text()").toString();
                                }
                                if (size != null && !"".equals(size)) {
                                    sizePriceMap.put(size, price);
                                }
                                //判断是否为最后一个颜色(分类)，若是则将颜色(分类)置空
                                if (fornum >= rememberSizeMeList.size()) {
                                    webElement1.click();
                                }
                                //判断是否为最后一个尺码，若是则将尺码置空
                                if (fornum >= rememberSizeMeList.size() && fornum > 1) {
                                    webElement2.click();
                                }
                                Thread.sleep(250);
                            }
                        } else {
                            //没有尺码
                            getHtmlResult = webElement.getAttribute("outerHTML");
                            price = Html.create(getHtmlResult).xpath("//em[@id='J_PromoPriceNum']/text()").toString();
                            if ("".equals(price) || price == null) {
                                price = Html.create(getHtmlResult).xpath("//em[@class='tb-rmb-num']/text()").toString();
                            }
                            sizePriceMap.put("均码", price);
                        }
                        String[] strings = new String[]{"tb-selected", "tb-txt tb-selected", "tb-txt"};
                        for (String str : strings) {
                            color = Html.create(getHtmlResultColor).xpath("//div[@class='tb-skin']/dl[@class='J_Prop tb-prop tb-clear  J_Prop_Color ']/dd/ul/li[@class='" + str + "']/a/span/text()").toString();
                            if (color != null) {
                                break;
                            }
                        }
                        if (color != null && !"".equals(color)) {
                            colorType = colorType + color + ",";
                            colorMap.put(color, sizePriceMap);
                        } else {
                            if (!recordData.contains(nowUrl)) {
                                recrawlingUrl.add(nowUrl);
                                page.addTargetRequests(recrawlingUrl);
                                number = 1;
                            }
                        }
                    }
                    if (sizePriceMap.isEmpty()) {
                        if (!recordData.contains(nowUrl)) {
                            recrawlingUrl.add(nowUrl);
                            page.addTargetRequests(recrawlingUrl);
                            number = 1;
                        }
                    }
                }
            } catch (Exception e) {
                if (!recordData.contains(nowUrl)) {
                    recrawlingUrl.add(nowUrl);
                    page.addTargetRequests(recrawlingUrl);
                    number = 1;
                }
                System.gc();
                logger.debug(e.getMessage());
            }
            //获取页面
            getHtmlResult = webElement.getAttribute("outerHTML");
            //判断鞋码为空，关闭浏览器。添加本地址重新再爬取
            if (number == 0) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(colorMap);
                    colorSize = jsonObject.toString();
                    //打印搜索到的页面
                    name = Html.create(getHtmlResult).xpath("//h3[@class='tb-main-title']/text()").toString();
                    price = Html.create(getHtmlResult).xpath("//em[@id='J_PromoPriceNum']/text()").toString();
                    if ("".equals(price) || price == null) {
                        price = Html.create(getHtmlResult).xpath("//em[@class='tb-rmb-num']/text()").toString();
                    }
                    sales = Html.create(getHtmlResult).xpath("//strong[@id='J_SellCounter']/text()").toString();
                    readme = Html.create(getHtmlResult).xpath("//div[@id='readme']/tidyText()").toString();
                    //商品详情
                    colorSort = Html.create(getHtmlResult).xpath("//ul[@class='attributes-list']/li[1]/text()").toString();
                    girard = Html.create(getHtmlResult).xpath("//ul[@class='attributes-list']/li[2]/text()").toString();
                    brand = Html.create(getHtmlResult).xpath("//ul[@class='attributes-list']/li[3]/text()").toString();
                    if (colorSort != null && !"".equals(colorSort)) {
                        colorSort = colorSort.split(":")[1];
                    }
                    if (girard != null && !"".equals(girard)) {
                        girard = girard.split(":")[1];
                    }
                    if (brand != null && !"".equals(brand)) {
                        brand = brand.split(":")[1];
                    }
                    //获取评论 TODO
                    if (webElementPinglun != null) {
                        webElementPinglun.click();
                    }
                } catch (Exception e) {
                    logger.debug(e.getMessage());
                    System.gc();
                }
                taobaoRepo.setName(name);
                taobaoRepo.setPrice(price);
                taobaoRepo.setSales(sales);
                taobaoRepo.setReadme(readme);
                taobaoRepo.setAuthor(nowUrl);
                CommodityData record = new CommodityData();
                CommodityDetail detail = new CommodityDetail();
                if (name != null || price != null || sales != null) {
                    if (!name.equals("") || !price.equals("") || !sales.equals("")) {
                        if (name != null || price != null || sales != null) {
                            int i = 1;
                            int d = 0;
                            try {
                                //检查是否存在该数据
                                CommodityData commodityDataInfo = commodityDataService.findByNames(name, girard);
                                if (commodityDataInfo == null) {
                                    //添加商品信息
                                    record.setStockxName(s_name);
                                    record.setStockxId(stockxStore.getId());
                                    record.setCommodityName(name);
                                    record.setCommodityPrice(price);
                                    record.setTransactionRecord(sales);
                                    record.setGirard(girard);
                                    record.setColorSort(colorSort);
                                    record.setBrand(brand);
                                    record.setProductUrl(nowUrl);
                                    record.setCreateDate(DateUtils.getCurrentTimeAsString());
                                    i = commodityDataService.insertCommodityData(record);
                                    //添加详情信息
                                    detail.setCommodityDataId(record.getId());
                                } else {
                                    detail.setCommodityDataId(commodityDataInfo.getId());
                                }
                                //i != 1 则首次添加商品数据失败
                                if (i == 1) {
                                    detail.setTransactionRecord(sales);
                                    detail.setColorSize(colorSize);
                                    detail.setCreateDate(DateUtils.getCurrentTimeAsString());
                                    d = commodityDataService.insertcommodityDetail(detail);
                                    if (d == 1) {
                                        logger.info("insert dataInfo success，dateTime:" + DateUtils.getCurrentTimeAs14String());
                                    } else {
                                        logger.info("insert dataInfo error，dateTime:" + DateUtils.getCurrentTimeAs14String());
                                    }
                                    //判断审核字典是否存在
                                    List<Dictionaries> dictionariesList = new ArrayList<Dictionaries>();
                                    if (commodityDataInfo == null) {
                                        dictionariesList = commodityDataService.finddictionaries(record.getId());
                                    } else {
                                        dictionariesList = commodityDataService.finddictionaries(commodityDataInfo.getId());
                                    }
                                    String[] colorNames = null;
                                    if (colorType != null && !"".equals(colorType)) {
                                        //分隔当前商品数据 款式/颜色
                                        colorType = colorType.substring(0, colorType.length() - 1);
                                        colorNames = colorType.split(",");
                                    } else {
                                        colorNames = new String[]{"无"};
                                    }
                                    if (dictionariesList.size() == 0) {
                                        for (String colorly : colorNames) {
                                            Dictionaries dictionarie = new Dictionaries();
                                            if (commodityDataInfo == null) {
                                                dictionarie.setCommodityDataId(record.getId());
                                                dictionarie.setCommodityData(record);
                                            } else {
                                                dictionarie.setCommodityDataId(commodityDataInfo.getId());
                                                dictionarie.setCommodityData(commodityDataInfo);
                                            }
                                            dictionarie.setColorly(colorly);
                                            dictionarie.setCreateTime(DateUtils.getCurrentTimeAsString());
                                            //添加字典数据
                                            commodityDataService.insertDicInfo(dictionarie);
                                        }
                                    } else {
                                        //校验字段是否匹配，若不匹配则补齐
                                        String nowColorlys = "";
                                        for (Dictionaries dic : dictionariesList) {
                                            //拼接当前字典表中所包含的数据
                                            nowColorlys += dic.getColorly() + ",";
                                        }
                                        nowColorlys = nowColorlys.substring(0, nowColorlys.length() - 1);
                                        for (String colorly : colorNames) {
                                            Dictionaries dictionarie = new Dictionaries();
                                            //判断 nowColorlys 中是否包含 colorly，若不包含则添加字典数据
                                            if (!nowColorlys.contains(colorly)) {
                                                if (commodityDataInfo == null) {
                                                    dictionarie.setCommodityDataId(record.getId());
                                                    dictionarie.setCommodityData(record);
                                                } else {
                                                    dictionarie.setCommodityDataId(commodityDataInfo.getId());
                                                    dictionarie.setCommodityData(commodityDataInfo);
                                                }
                                                dictionarie.setColorly(colorly);
                                                //添加字典数据
                                                dictionarie.setCreateTime(DateUtils.getCurrentTimeAsString());
                                                commodityDataService.insertDicInfo(dictionarie);
                                            }
                                        }
                                    }
                                    //查询字典商品标签名     &     验证该店铺下字典表数据是否全部已审核
                                    List<Dictionaries> dicObjList = new ArrayList<Dictionaries>();
                                    if (commodityDataInfo == null) {
                                        dicObjList = commodityDataService.finddictionaries(record.getId());
                                    } else {
                                        dicObjList = commodityDataService.finddictionaries(commodityDataInfo.getId());
                                    }
                                    for (Dictionaries dic : dicObjList) {
                                        if (!"".equals(dic.getIdentification()) && dic.getIdentification() != null) {
                                            //状态是否为已审
                                            if (!"2".equals(dic.getStatus()) && !"3".equals(dic.getStatus())) {
                                                //该商品已审核,则添加最终数据
                                                ResultData resultData = new ResultData();
                                                dic.getIdentification();
                                                //组装最终商品数据
                                                if (commodityDataInfo == null) {
                                                    resultData.setCommodityDataId(record.getId());
                                                } else {
                                                    resultData.setCommodityDataId(commodityDataInfo.getId());
                                                }
                                                Iterator keys = jsonObject.keys();
                                                while (keys.hasNext()) {
                                                    String keyStr = keys.next().toString();
                                                    //判断是否和字典匹配
                                                    if (dic.getColorly().equals(keyStr)) {
                                                        //从鞋库取数据
                                                        Basicinformation b = new Basicinformation();
                                                        b.setName(dic.getIdentification());
                                                        List<Basicinformation> baciList = basicinformationService.findAll(b);
                                                        //基于鞋库保存数据
                                                        if (baciList.size() > 0) {
                                                            resultData.setBasiciformationId(baciList.get(0).getId());
                                                            resultData.setStoreId(stockxStore.getId());
                                                            resultData.setStoreName(stockxStore.getName());
                                                            resultData.setProductName(dic.getIdentification());
                                                            resultData.setGirard(dic.getGirard());
                                                            resultData.setSizePrice(jsonObject.get(keyStr).toString());
                                                            resultData.setBrand(brand);
                                                            resultData.setTransactionRecord(sales);
                                                            resultData.setCreateTime(DateUtils.getCurrentTimeAsString());
                                                            //保存最终结果
                                                            int dataReultBool = commodityDataService.saveResultDatas(resultData);
                                                            if (dataReultBool == 1) {
                                                                logger.info("save final result data success ! ! ! ");
                                                            } else {
                                                                logger.error("save final result data Error ! ! ! ");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                logger.error(e.getMessage());
                                driver.quit();
                                System.gc();
                            }
                        }
                    }
                }
            }
            //重置参数
            name = "";
            price = "";
            sales = "";
            if (i == 1) {
                str = new ArrayList<>();
                List<String> Url = new ArrayList<String>();
                Url = Html.create(getHtmlResult).regex("(//\\w+.\\w+.com\\/item.htm\\?.*?id=([\\d]+).*?)").all();
                List<WebElement> pageNextList = driver.findElements(By.xpath("//div[@class='pagination']/a[@class='J_SearchAsync next']"));
                if (pageNextList.size() != 0) {
                    //遍历鞋码
                    for (WebElement webElement1 : rememberSizeMeList) {
                        webElement1.click();
                        Url = Html.create(getHtmlResult).regex("(//\\w+.\\w+.com\\/item.htm\\?.*?id=([\\d]+).*?)").all();
                    }
                }
                // 获取后续地址
                String equ = "item.taobao.com/item.htm";
                HashSet<String> hs = new HashSet<String>(Url);
                List<String> str2 = new ArrayList<>();
                if (Url.size() == 0) {
                    //链接失效，将更新链接为失效链接
                    stockxStore.setStatus("0");
                    stockxStoreService.updateStore(stockxStore);
                }
                for (String temp : hs) {
                    if (temp.contains(equ)) {
                        str.add(temp);
                        str2.add(temp);
                    }
                }
                //将百度页面作为过渡页面
                str2.add("http://www.baidu.com");
                page.addTargetRequests(str2);
            }
            driver.quit();
            System.gc();
            // 更新爬取状态为进行中
            i = -1;
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(StockxStore roeced, List<StockxStore> list) {
        stockxStoreList = list;
        stockxStore = roeced;
        //调用抓取功能
        GithubRepoPageProcessor githubRepoPageProcessor = new GithubRepoPageProcessor();
        githubRepoPageProcessor.setS_name(roeced.getName());
        //String url = "https://landaitiyu.taobao.com/widgetAsync.htm?ids=7471119073&path=%2Fshop%2Fview_shop.htm&callback=callbackGetMods7471119073&site_instance_id=241542072";
        Spider.create(githubRepoPageProcessor)
                //从"https://github.com/code4craft"开始抓
                .addUrl(roeced.getUrl())
                //设置Scheduler，使用Redis来管理URL队列
                //.scheduler(new RedisSc
                // heduler("localhost"))
                //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //使用Selenium做页面动态渲染
                //.downloader(new SeleniumDownloader("/Program Files (x86)/Google/Chrome/Application"))
                //开启5个线程抓取
                .thread(1)
                .setExitWhenComplete(true)
                //启动爬虫
                .run();
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public static String shoeroue(String sizes) {
        //初始化变量
        String rule = "1/3";
        String taobaoRule = "2/3";
        int i = 0;
        String shoe = null;
        String size = null;
        //判断是否包含1/3
        if (sizes.contains(rule)) {//包含
            i = sizes.indexOf(rule);
            if (i > 1) {
                shoe = sizes.trim().substring(0, i - 1) + ".5";
                if (sizes.length() > (i + 3)) {
                    size = sizes.trim().substring(i + 3, sizes.length());
                }
                sizes = shoe + size;
            }
        }//判断是否包含2/3
        else if (sizes.contains(taobaoRule)) {
            i = sizes.indexOf(rule);
            if (i > 1) {
                shoe = sizes.trim().substring(0, i - 1) + ".5";
                if (sizes.length() > (i + 3)) {
                    size = sizes.trim().substring(i + 3, sizes.length());
                }
                sizes = shoe + size;
            }
        }
        return sizes;
    }

    //验证Url地址是否正确拼接
    public String updateNowUrl(String url) {
        if (url.contains("http")) {
            if (url.contains("http:")) {
                url = url;
            } else {
                url = url.replaceAll("http", "http:");
            }
        } else {
            url = "http:" + url;
        }
        return url;
    }
}
