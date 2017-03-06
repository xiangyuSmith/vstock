package com.vstock.spider.data.spider;

import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.StockxStore;
import com.vstock.ext.util.ConstUtil;
import com.vstock.ext.util.JsonTool;
import com.vstock.ext.util.ToolSpring;
import com.vstock.spider.data.service.BasicinformationService;
import org.apache.log4j.Logger;
import org.json.JSONArray;
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

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by xiangyu on 2016/5/5.
 * wemagic 标准模式
 */
public class StockxInfoRepoPageProcessor implements PageProcessor {

    private Logger logger = Logger.getLogger(StockxInfoRepoPageProcessor.class);

    private static StockxStore stockxStore;

    private static HttpServletRequest request;

    private static String brandName = "";
    //初始化url
    private List<String> urlList = new ArrayList<String>();

    String toUrl = "";
    String getHtmlResult = "";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        BasicinformationService basicinformationService = (BasicinformationService) ToolSpring.getBean("basicinformation");
        //当前 url
        toUrl = page.getUrl().toString();
        try {
            String json = loadJSON(toUrl);
            Map map = JsonTool.toMap(json);
            String productss = map.get("Products").toString();
            JSONArray myJsonArray = new JSONArray(productss);
            for (int i = 0; i < myJsonArray.length(); i++) {
                //获取每一个JsonObject对象
                JSONObject myjObject = myJsonArray.getJSONObject(i);
                //获取每一个对象中的值
                String urlKey = myjObject.get("urlKey").toString();
                JSONObject imgUrlObject = new JSONObject(myjObject.get("media").toString());
                //获取图片链接
                String smallImageUrl = imgUrlObject.get("smallImageUrl").toString();
                //String thumbUrl = imgUrlObject.getString("thumbUrl");
                //创建 ChromeDriver 环境，建立chrome内核模拟浏览器
                String chromeDriver = getPropertiesInfo().getProperty("chromeDriver");
                System.getProperties().setProperty("webdriver.chrome.driver", chromeDriver);
                WebDriver driver = new ChromeDriver();
                try {
                    driver.get("https://stockx.com/" + urlKey);
                    WebElement webElement = driver.findElement(By.xpath("/html"));
                    //打印搜索到的页面 System.out.println(webElement.getAttribute("outerHTML"));
                    //获取页面
                    getHtmlResult = webElement.getAttribute("outerHTML");
                    String productName = Html.create(getHtmlResult).xpath("//h1[@data-reactid='.4.6.0.0.0.0.0.0']/text()").toString();
                    String style = Html.create(getHtmlResult).xpath("//span[@data-reactid='.4.6.2.0.0.0.1.1']/text()").toString();
                    String colorWay = Html.create(getHtmlResult).xpath("//span[@data-reactid='.4.6.2.0.0.1.0.1.1']/text()").toString();
                    String releaseDate = Html.create(getHtmlResult).xpath("//span[@data-reactid='.4.6.2.0.0.2.1.1']/text()").toString();
                    String[] rels = releaseDate.split("\\.");
                    releaseDate = rels[2] + "/" + rels[0] + "/" + rels[1];
                    Calendar now = Calendar.getInstance();
                    //年份
                    String year = String.valueOf(now.get(Calendar.YEAR));
                    int nowDate = Integer.parseInt(year.substring(2, 4));
                    int csdate = Integer.parseInt(releaseDate.substring(0, 2));
                    if (csdate > nowDate) {
                        releaseDate = "19" + releaseDate;
                    } else {
                        releaseDate = "20" + releaseDate;
                    }
                    String originalRetailPrice = Html.create(getHtmlResult).xpath("//span[@data-reactid='.4.6.2.0.0.3.1.1']/text()").toString();
                    String img1 = Html.create(getHtmlResult).xpath("//meta[@name='image']/@content").toString();
                    if(!"".equals(originalRetailPrice)){
                        originalRetailPrice = originalRetailPrice.substring(1,originalRetailPrice.length());
                    }
                    //保存数据
                    if (productName != null && style != null) {
                        Basicinformation basicinformation = new Basicinformation();
                        basicinformation.setBrand(brandName);
                        basicinformation.setName(productName);
                        List<Basicinformation> baList = basicinformationService.findAll(basicinformation);
                        if (baList.size() == 0) {
                            //添加图片
                            String smallImgUrl = loadImg(smallImageUrl, urlKey, 1);
                            String imgUrl = loadImg(img1, urlKey, 0);
                            basicinformation.setArtNo(style);
                            basicinformation.setColores(colorWay);
                            basicinformation.setEsaledate(releaseDate);
                            basicinformation.setEofferprice(originalRetailPrice);
                            basicinformation.setImgUrl(imgUrl);
                            basicinformation.setSmallImgUrl(smallImgUrl);
                            basicinformation.setState("1");
                            int result = basicinformationService.insertbasicinfrom(basicinformation);
                            if (result == 1) {
                                logger.info("Info : insert basicinformation suceess");
                            } else {
                                logger.error("Error : insert basicinformation error");
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                } finally {
                    driver.quit();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String toUrl, String brandNames, HttpServletRequest requests) {
        //调用抓取功能
        StockxInfoRepoPageProcessor stockInfoRepoPageProcessor = new StockxInfoRepoPageProcessor();
        brandName = brandNames;
        request = requests;
        Spider.create(stockInfoRepoPageProcessor)
                //从"https://github.com/code4craft"开始抓
                .addUrl(toUrl)
                //设置Scheduler，使用Redis来管理URL队列
                //.scheduler(new RedisSc
                // heduler("localhost"))
                //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new JsonFilePipeline("D:\\data\\webmagic"))
                //使用Selenium做页面动态渲染
                //.downloader(new SeleniumDownloader("/Program Files (x86)/Google/Chrome/Application"))
                //开启5个线程抓取
                .thread(1)
                //启动爬虫
                .run();

    }

    /**
     * 请求接口返回json数据
     *
     * @param url
     * @return
     */
    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    public static String loadImg(String imgUrl, String productName, int type) {
        BufferedInputStream bis = null;
        HttpURLConnection httpurlConnection = null;
        File fileinput = null;
        BufferedImage bfimg = null;
        //存储文件路径
        String saveUrl = "";
        try {
            // 网络接收图片
            URL url = new URL(imgUrl);
            httpurlConnection = (HttpURLConnection) url.openConnection();
            httpurlConnection.setConnectTimeout(30000);
            httpurlConnection.setReadTimeout(30000);
            httpurlConnection.setDoOutput(true);
            httpurlConnection.connect();
            InputStream inputStream = httpurlConnection
                    .getInputStream();
            bis = new BufferedInputStream(
                    httpurlConnection.getInputStream());
            bfimg = ImageIO.read(inputStream);
            String ursl = getPropertiesInfo().getProperty("projectPath");
            if (type == 1) {
                //缩略图
                saveUrl = "/assets/shoesImg/small/";
            } else {
                //大图
                saveUrl = "/assets/shoesImg/";
            }
            File file = new File(ursl + saveUrl);
            // 判断文件夹是否创建，没有创建则创建新文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(file, productName + ".jpg");
            saveUrl = saveUrl + productName + ".jpg";
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file.getAbsolutePath()));
            // 创建输入流缓冲
            byte[] buffer = new byte[1024];
            // 读入字节数
            int num = -1;
            while (true) {
                num = bis.read(buffer);
                if (num == -1) {
                    // 读取结束
                    bos.flush();
                    break;
                }
                bos.flush();
                bos.write(buffer, 0, num);
            }
            // 关闭字节流
            bos.close();
            ImageIO.write(bfimg, "png", file);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return saveUrl;
    }

    public static Properties getPropertiesInfo() {
        Properties prop = new Properties();
        try {
            InputStream in = ConstUtil.class.getResourceAsStream("/spider.properties");
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
