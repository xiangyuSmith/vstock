package com.vstock.spider.data.controller;

import com.vstock.db.entity.StockxStore;
import com.vstock.ext.util.ConstUtil;
import com.vstock.spider.data.bean.MyThread;
import com.vstock.spider.data.service.StockxStoreService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2016/9/21.
 */
@Controller
@RequestMapping("/interruptTest")
public class InterruptTestController {

    @Autowired
    StockxStoreService stockxStoreService;

    private static Logger logger = Logger.getLogger(InterruptTestController.class);

    private static Integer timeThread = Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("timeThread"));

    @RequestMapping("index")
    public String index() throws InterruptedException {
        Integer numberThread = Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("numberThread"));
        List<StockxStore> finalList = stockxStoreService.finalList();
        //获取机器数量 & 机器号
        int listSize = finalList.size();
        int machineCount = listSize / Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("machineCount"));
        int machineNum = Integer.parseInt(ConstUtil.getSpiderProperties().getProperty("machineNum"));
        //按机器数平局分配
        int subCount = listSize % machineCount == 0 ? listSize / machineCount : listSize / machineCount + 1;
        int startIndext = 0;
        int stopIndext = 0;
        if (subCount == 1) {
            threadStockx(finalList, numberThread);
        } else {
            for (int k = 0; k < subCount; k++) {
                stopIndext = (k == subCount - 1) ? stopIndext + listSize % machineCount : stopIndext + machineCount;
                List<StockxStore> tempList = new ArrayList<StockxStore>(finalList.subList(startIndext, startIndext + stopIndext));
                startIndext = stopIndext;
                if (machineNum - 1 == k) {
                    //处理当前集合
                    threadStockx(tempList, numberThread);
                } else {
                    if (k == subCount - 1) {
                        //判断是否有余
                        if (listSize % machineCount != 0) {
                            if (machineNum == 1) {
                                //处理当前集合
                                threadStockx(tempList, numberThread);
                            }
                        }
                    }
                }
            }
        }
        return "base/index";
    }

    public void threadStockx(List<StockxStore> tempList, int numberThread) throws InterruptedException {
        MyThread.setFinalList(tempList);
        if (tempList.size() >= numberThread) {
            for (int i = 0; i < numberThread; i++) {
                String name = i + "-" + numberThread;
                MyThread t1 = new MyThread(name);
                t1.start();
                Thread.sleep(timeThread);
            }
        } else {
            MyThread t1 = new MyThread("MyThread1");
            t1.start();
        }
    }
}
