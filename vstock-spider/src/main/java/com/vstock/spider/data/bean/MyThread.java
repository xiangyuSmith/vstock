package com.vstock.spider.data.bean;

import com.vstock.ext.util.ToolSpring;
import com.vstock.spider.data.service.StockxStoreService;
import com.vstock.spider.data.spider.GithubRepoPageProcessor;
import com.vstock.db.entity.StockxStore;
import com.vstock.spider.data.spider.SpiderPageProcessor;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2016/9/21.
 */
public class MyThread extends Thread{
    private static Logger logger = Logger.getLogger(MyThread.class);
    StockxStoreService stockxStoreService = (StockxStoreService) ToolSpring.getBean("stockxStore");

    public static List<StockxStore> finalList;

    public MyThread(String name){
        super(name);
    }

    public void run(){
        List<StockxStore> fina = new ArrayList<StockxStore>();
        String name = getName();
        int a = 0;
        int b = 0;
        if (name.contains("-")){
            String[] numberTeah = name.split("-");
            a = Integer.parseInt(numberTeah[0]);
            b = Integer.parseInt(numberTeah[1]);
            for (int i = a; i < finalList.size(); i+=b) {
                fina.add(finalList.get(i));
            }
        }else {
            for (int i = 0; i < finalList.size(); i++) {
                fina.add(finalList.get(i));
            }
        }

        logger.info("==========================================================");
        logger.info("第" + (a++) + "个线程" + fina.size());
        logger.info("==========================================================");
        SpiderPageProcessor spiderPageProcessor = new SpiderPageProcessor();
        spiderPageProcessor.init(fina);
    }

    public static List<StockxStore> getFinalList() {
        return finalList;
    }

    public static void setFinalList(List<StockxStore> finalList) {
        MyThread.finalList = finalList;
    }
}
