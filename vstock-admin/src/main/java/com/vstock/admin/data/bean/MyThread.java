package com.vstock.admin.data.bean;

import com.vstock.admin.base.service.StockxStoreService;
import com.vstock.ext.util.ToolSpring;
import com.vstock.admin.data.spider.GithubRepoPageProcessor;
import com.vstock.db.entity.StockxStore;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2016/9/21.
 */
public class MyThread extends Thread{
    private static Logger logger = Logger.getLogger(MyThread.class);
    StockxStoreService stockxStoreService = (StockxStoreService) ToolSpring.getBean("stockxStore");

    public MyThread(String name){
        super(name);
    }

    public void run(){
        List<StockxStore> finalList = stockxStoreService.finalList();
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
        logger.info("第" + a + "个线程" + fina.size());
        logger.info("==========================================================");
        for (StockxStore stockxStore : fina){
            GithubRepoPageProcessor.main(stockxStore,fina);
        }
    }
}
