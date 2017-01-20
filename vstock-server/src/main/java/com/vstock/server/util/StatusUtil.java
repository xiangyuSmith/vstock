package com.vstock.server.util;

import com.vstock.db.entity.BackCommodity;
import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Refund;
import com.vstock.db.entity.Trade;

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
     * 叫价表后台用状态
     * @return
     */
    public static List<Bid> bidStatusa(){
        List<Bid> bidList = new ArrayList<Bid>();
        for (String str : Bid.bidStatus){
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

    /**
     * 获取订单表前台展现状态
     * @return
     */
    public static List<Trade> tradeStatus(){
        List<Trade> tradeList = new ArrayList<Trade>();
        for (String str : Trade.tradeStatuses){
            Trade record = new Trade();
            String[] status = str.split(":");
            record.setId(Integer.parseInt(status[0]));
            record.setBftName(status[1]);
            tradeList.add(record);
        }
        return tradeList;
    }

    /**
     * 获取退款表状态
     * @return
     */
    public static List<Refund> refundStatus(){
        List<Refund> refundList = new ArrayList<Refund>();
        for (String str : Refund.refundstatus){
            Refund record = new Refund();
            String[] status = str.split(":");
            record.setStatus(status[0]);
            record.setBtfName(status[1]);
            refundList.add(record);
        }
        return refundList;
    }

    /**
     * 获取退款表类型
     * @return
     */
    public static List<Refund> refundType(){
        List<Refund> refundList = new ArrayList<Refund>();
        for (String str : Refund.refundType){
            Refund record = new Refund();
            String[] type = str.split(":");
            record.setType(type[0]);
            record.setBtfName(type[1]);
            refundList.add(record);
        }
        return refundList;
    }

    /**
     * 获取退款对象信息
     * @return
     */
    public static List<Refund> refundO(){
        List<Refund> refundList = new ArrayList<Refund>();
        for (String str : Refund.refundO){
            Refund record = new Refund();
            String[] obj = str.split(":");
            record.setRefundObj(obj[0]);
            record.setBtfName(obj[1]);
            refundList.add(record);
        }
        return refundList;
    }
}
