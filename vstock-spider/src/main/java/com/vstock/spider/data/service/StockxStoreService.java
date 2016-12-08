package com.vstock.spider.data.service;

import com.vstock.db.dao.IStockxStore;
import com.vstock.db.entity.StockxInfo;
import com.vstock.db.entity.StockxStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2016/5/11.
 */
@Service("stockxStore")
public class StockxStoreService {

    @Autowired
    IStockxStore stockxStore;

    //获取列表
    public List<StockxStore> findList() {
        List<StockxStore> stockList = stockxStore.findList();
        return stockList;
    }

    //获取stockx基本信息列表
    public List<StockxInfo> findStockxInfo() {
        return stockxStore.findStockxInfo();
    }

    //更新
    public int updateStore(StockxStore record) {
        return stockxStore.updateStockxStore(record);
    }

    public List<StockxStore> finalList() {
        //获取店铺表中录入的连接地址
        List<StockxStore> list = this.findList();
        List<StockxStore> finalList = new ArrayList<StockxStore>();
        //判断是否存在值
        if (list.size() > 0) {
            //调用爬虫方法
            for (StockxStore stockxStore : list) {
                int num = 0;
                try {
                    num = Integer.parseInt(stockxStore.getPageNo());
                }catch (Exception e){
                    System.out.print(e.getMessage());
                }
                String toUrl = stockxStore.getUrl();
                for (int i = 1; i <= num; i++) {
                    String newUrl = toUrl + "&pageNo=" + i;
                    StockxStore store = new StockxStore();
                    store.setId(stockxStore.getId());
                    store.setBrand(stockxStore.getBrand());
                    store.setPageNo(stockxStore.getPageNo());
                    store.setName(stockxStore.getName());
                    store.setStatus(stockxStore.getStatus());
                    store.setUrl(newUrl);
                    finalList.add(store);
                }
            }
        }
        return finalList;
    }
}
