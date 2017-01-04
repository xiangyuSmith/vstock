package com.vstock.server.util;

import com.vstock.db.entity.BackCommodity;
import com.vstock.db.entity.Bid;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态状态公共方法
 */
public class StatusUtil {

    /**
     * 获取bid叫价表的状态
     * @return
     */
    public static List<Bid> bidStatus(){
        List<Bid> bidList = new ArrayList<Bid>();
        for (String str : Bid.statusStr){
            Bid record = new Bid();
            String[] status = str.split(":");
            record.setStatus(status[0]);
            record.setBftName(status[1]);
            bidList.add(record);
        }
        return bidList;
    }

    /**
     * 获取BackCommodity表的状态
     * @return
     */
    public static List<BackCommodity> backStatus(){
        List<BackCommodity> backStatus = new ArrayList<BackCommodity>();
        for (String str : BackCommodity.statuses){
            BackCommodity backCommodity = new BackCommodity();
            String[] strstatus = str.split(":");
            backCommodity.setStatus(Integer.parseInt(strstatus[0]));
            backCommodity.setBtfName(strstatus[1]);
            backStatus.add(backCommodity);
        }
        return backStatus;
    }
}
