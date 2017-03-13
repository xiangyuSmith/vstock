package com.vstock.front.support.job;

import com.vstock.db.entity.*;
import com.vstock.ext.util.*;
import com.vstock.front.service.*;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class QuartzJob implements Job {

    final static Logger logger = Logger.getLogger(QuartzJob.class);

    BidService bidService = (BidService) ToolSpring.getBean("bid");

    TradeService tradeService = (TradeService) ToolSpring.getBean("trade");

    ResultDataService resultDataService = (ResultDataService) ToolSpring.getBean("resultData");

    PaymentService paymentService = (PaymentService) ToolSpring.getBean("payment");

    PricePeakService pricePeakService = (PricePeakService) ToolSpring.getBean("pricePeak");

    RefundService refundService = (RefundService) ToolSpring.getBean("refund");

    BasiciformationRoseService basiciformationRoseService = (BasiciformationRoseService) ToolSpring.getBean("basiciformationRose");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CustomJob customJob = (CustomJob)context.getMergedJobDataMap().get("scheduleJob");
        logger.info(customJob.getJobName()+"starting ...");
        if("trade_bid_list".equals(customJob.getJobName())){
            //实时任务
            int bid_result = quartzBid();
            int trade_result = quartzTrade();
            if(bid_result == 0){
                logger.warn("update bid fail ...");
            }
            if(trade_result == 0){
                logger.warn("update trade fail ...");
            }
        }
        if("initTimer".equals(customJob.getJobName())){
            //每日凌晨一点，调用涨幅表任务
            resultDataService.insertRose();
            resultDataService.getBrandMarket();
            bidService.peakPriceJobBid();
//            basiciformationRoseService.getRoseDegree();
        }
    }

    private int quartzBid(){
        Bid record = new Bid();
        record.setStatus(String.valueOf(record.STATUS_PENDING));
        List<Bid> bidlist = bidService.findAllBid(record);
        record.setStatus(String.valueOf(record.STATUS_INIT));
        List<Bid> bidlisting = bidService.findAllBid(record);
        int result = bidSend(bidlist,60*60*24,record.STATUS_CLEAN);
        int result2 = bidSend(bidlisting,0,record.STATUS_OVERDUE);
        return result * result2;
    }

    private int quartzTrade(){
        Trade record = new Trade();
        record.setStatus(record.TRADE_NOTIFIY_PAY);
        List<Trade> tradeList = tradeService.findAllTrade(record);
        record.setStatus(record.TRADE_NOTIFIY_PAY_BOND);
        List<Trade> tradeListNotBond = tradeService.findAllTrade(record);
        //订单支付金额倒计时
        int result = tradeSend(tradeList,60*60*24,record.TRADE_CLOSE,1);
        //保证金支付倒计时
        int result2 = tradeSend(tradeListNotBond,60*15,record.TRADE_CLOSE,2);
        return result * result2;
    }

    private int bidSend( List<Bid> bids,int time,int status){
        Bid b = new Bid();
        Bid bwhere = new Bid();
        PricePeak p = new PricePeak();
        Page lagePage = new Page();
        lagePage.setStartPos(0);
        lagePage.setPageSize(1);
        int result = 1;
        for (Bid bid : bids) {
            if(status == 11){
                int day = Integer.parseInt(bid.getTermValidity());
                time = 60*60*24*day;
            }
            long difference = isdifference(bid.getBidDate(),time);
            if(difference <= 0){
                b.setId(bid.getId());
                b.setStatus(String.valueOf(status));
                int x = bidService.update(b);
                if(status == 11){
                    //叫价过期，生成退款单
                    Refund refund = new Refund();
                    if("0".equals(bid.getType())){
                        refund.setType("4");
                    }else{
                        refund.setType("3");
                    }
                    refund.setRefundNo(OddNoUtil.refundNo());
                    refund.setTradeNo(String.valueOf(bid.getId()));
                    refund.setRefundObj(bid.getType());
                    refund.setBtfId(bid.getBasicinformationId());
                    refund.setBtfName(bid.getBftName());
                    refund.setRefundPrice(bid.getBidBond());
                    refund.setStatus(Refund.REFUND_NOTIFIY);
                    refund.setRemarks("叫价/出价过期");
                    refund.setCreateDate(DateUtils.getCurrentTimeAsString());
                    refundService.insert(refund);
                    //如果为过期状态，则更新峰值表
                    PricePeak pricePeak = pricePeakService.getHighestAndlowest(bid.getBasicinformationId(),bid.getBftSize(), DateUtils.dateToString(new Date()),lagePage);
                    if(pricePeak != null){
                        if("0".equals(bid.getType())){
                            bwhere.setType("0");
                            bwhere.setBasicinformationId(bid.getBasicinformationId());
                            bwhere.setBftSize(bid.getBftSize());
                            bwhere.setStatus(String.valueOf(Bid.STATUS_INIT));
                            List<Bid> bidList = bidService.findAllBid(bwhere);
                            p.setId(pricePeak.getId());
                            if(bidList.size() == 0){
                                p.setMinimumSellingPrice(null);
                                p.setMinimumSellingId(null);
                            }else{
                                p.setMinimumSellingPrice(bidList.get(0).getBidMoney());
                                p.setMinimumSellingId(String.valueOf(bidList.get(0).getUserId()));
                            }
                            result = pricePeakService.updateX(p);
                        }else{
                            bwhere.setType("1");
                            bwhere.setBasicinformationId(bid.getBasicinformationId());
                            bwhere.setBftSize(bid.getBftSize());
                            bwhere.setStatus(String.valueOf(Bid.STATUS_INIT));
                            List<Bid> bidList = bidService.findAllBid(bwhere);
                            p.setId(pricePeak.getId());
                            if(bidList.size() == 0){
                                p.setHighestBid(null);
                                p.setHighestBidderId(null);
                            }else{
                                p.setHighestBid(bidList.get(0).getBidMoney());
                                p.setHighestBidderId(String.valueOf(bidList.get(0).getUserId()));
                            }
                            result = pricePeakService.updateY(p);
                        }
                    }
                }
                result = result * x;
            }
        }
        return result;
    }

    private int tradeSend( List<Trade> trades,int time,int status,int type){
        int result = 1;
        int ischeck = 0;
        long difference = 0;
        Trade t = new Trade();
        Bid b = new Bid();
        String buySaleType = "0";
        for (Trade trade : trades) {
            difference = isdifference(trade.getTransactionDate(),time);
            b.setId(trade.getBidId());
            Bid bid = bidService.findbid(b);
            if(type == 1){
                //判断是卖家出售-买家付款（24小时），还是买家直接购买付款（15分钟）
                if(bid.getUserId() == trade.getSellerId()){
                    //该订单为买家直接购买
                    time = 60*15;
                }else{
                    time = 60*60*24;
                    ischeck = 1;
                }
                difference= isdifference(trade.getTransactionDate(),time);
            }else{
                buySaleType = "1";
            }
            if(difference <= 0){
                //买家24小时未付款
                if(ischeck == 1){
                    Refund refund = new Refund();
                    refund.setRefundNo(OddNoUtil.refundNo());
                    refund.setTradeNo(trade.getTradeNo());
                    refund.setRefundObj("1");
                    refund.setBtfId(bid.getBasicinformationId());
                    refund.setBtfName(bid.getBftName());
                    refund.setRefundPrice(bid.getBidBond());
                    refund.setStatus(Refund.REFUND_NOTIFIY);
                    refund.setType("5");
                    refund.setCreateDate(DateUtils.getCurrentTimeAsString());
                    refundService.insert(refund);
                }
                t.setId(trade.getId());
                t.setStatus(status);
                t.setBuysaleType(buySaleType);
                int x = tradeService.update(t);
                result = result * x;
            }
        }
        return result;
    }

    /**
     * @param objDate   系统下单时间
     * @param second    距离到期时间(精确到秒)
     * @return
     */
    private long isdifference(String objDate,int second){
        long bid_time = Long.parseLong(DateUtils.date2TimeStamp(objDate , ToolDateTime.pattern_ymd_hms));
        long now_time = ToolDateTime.getDateByTime()/1000L;
        return (bid_time+second)-now_time;
    }
}
