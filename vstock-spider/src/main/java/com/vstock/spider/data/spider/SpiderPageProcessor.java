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
import us.codecraft.webmagic.selector.Html;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiangyu on 2016/11/30.
 */
public class SpiderPageProcessor {

    private static Logger logger = Logger.getLogger(SpiderPageProcessor.class);

    private CommodityDataService commodityDataService = (CommodityDataService) ToolSpring.getBean("commodityData");

    private StockxStoreService stockxStoreService = (StockxStoreService) ToolSpring.getBean("stockxStore");

    private BasicinformationService basicinformationService = (BasicinformationService) ToolSpring.getBean("basicinformation");

    private String getHtmlResult = "";
    private String getHtmlResultColor = "";
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

    public void init(List<StockxStore> list) {
        //调用抓取功能
        TaobaoRepo taobaoRepo = new TaobaoRepo();
        String nowUrl = "";
        String getHtmlResult = "";
        for (StockxStore stockxStore : list) {
            nowUrl = stockxStore.getUrl();
            String chromeDriver = ConstUtil.getSpiderProperties().getProperty("chromeDriver");
            System.getProperties().setProperty("webdriver.chrome.driver", chromeDriver);
            WebDriver parentDriver = new ChromeDriver();
            parentDriver.get(nowUrl);
            WebElement webElement = parentDriver.findElement(By.xpath("/html"));
            getHtmlResult = webElement.getAttribute("outerHTML");
            List<String> proUrls = Html.create(getHtmlResult).regex("(//\\w+.\\w+.com\\/item.htm\\?.*?id=([\\d]+).*?)").all();
            //关闭父（列表）页，并对商品列表去重
            parentDriver.close();
            List<String> listTemp = repList(proUrls);
            for (String url : listTemp) {
                url = updateNowUrl(url);
                logger.info("当前淘宝店铺链接：" + url);
                String sysPro = System.getProperty("webdriver.chrome.driver");
                if (!"".equals(sysPro)) {
                    System.getProperties().setProperty("webdriver.chrome.driver", chromeDriver);
                }
                WebDriver driver = null;
                try {
                    driver = new ChromeDriver();
                    driver.get(url);
                    webElement = driver.findElement(By.xpath("/html"));
                } catch (Exception e) {
                    logger.error("启动谷歌内核失败!!!!原因：" + e.getMessage());
                }
                int number = 0;
                List<WebElement> rememberColorList = new ArrayList<WebElement>();
                List<WebElement> rememberSizeMeList = new ArrayList<>();
                List<WebElement> rememberSizeMeListes = new ArrayList<>();
                WebElement webElementPinglun = null;
                logger.info("开始爬取：" + url);
                try {
                    webElementPinglun = driver.findElement(By.xpath("//div[@class='tb-tabbar-inner-wrap']/ul/li[2]/a"));
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
                            }
                        }
                    }
                } catch (Exception e) {
                    System.gc();
                    logger.debug(e.getMessage());
                }
                //判断鞋码为空，关闭浏览器。添加本地址重新再爬取
                if (number == 0) {
                    JSONObject jsonObject = null;
                    try {
                        //获取页面
                        getHtmlResult = webElement.getAttribute("outerHTML");
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
                                        record.setStockxName(stockxStore.getName());
                                        record.setStockxId(stockxStore.getId());
                                        record.setCommodityName(name);
                                        record.setCommodityPrice(price);
                                        record.setTransactionRecord(sales);
                                        record.setGirard(girard);
                                        record.setColorSort(colorSort);
                                        record.setBrand(brand);
                                        record.setProductUrl(url);
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
                if (driver != null) {
                    driver.quit();
                }
                System.gc();
            }

        }
    }


    /**
     * List 去重
     *
     * @return
     */
    public List<String> repList(List<String> list) {
        List<String> b = new ArrayList<>();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String a = it.next();
            if (b.contains(a)) {
                it.remove();
            } else {
                b.add(a);
            }
        }
        return b;
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
