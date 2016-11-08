package com.vstock.admin.data.web.controller;

import com.vstock.admin.base.service.StockxStoreService;
import com.vstock.admin.data.bean.MyThread;
import com.vstock.db.entity.StockxStore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("index")
    public String index() throws InterruptedException {
        Integer numberThread = 2;
        List<StockxStore> finalList = stockxStoreService.finalList();
        if (finalList.size() >= numberThread){
            for (int i = 0; i < numberThread; i++){
                String name = i + "-" + numberThread;
                MyThread t1 = new MyThread(name);
                t1.start();
                Thread.sleep(10000);
            }
        }else {
            MyThread t1 = new MyThread("MyThread1");
            t1.start();
        }
        return "base/index";
    }
}
