package com.vstock.front.support.job;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.CustomJob;
import com.vstock.db.entity.Payment;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.ext.util.ToolSpring;
import com.vstock.front.service.*;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class QuartzJob implements Job {

    final static Logger logger = Logger.getLogger(QuartzJob.class);

    BidService bidService = (BidService) ToolSpring.getBean("bid");

    TradeService tradeService = (TradeService) ToolSpring.getBean("trade");

    ResultDataService resultDataService = (ResultDataService) ToolSpring.getBean("resultData");

    PaymentService paymentService = (PaymentService) ToolSpring.getBean("payment");

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
        //订单金额计时
        int result = tradeSend(tradeList,60*60*24,record.TRADE_CLOSE,1);
        //保证金计时
        int result2 = tradeSend(tradeListNotBond,60*15,record.TRADE_CLOSE,2);
        return result * result2;
    }

    private int bidSend( List<Bid> bids,int time,int status){
        Bid b = new Bid();
        int result = 1;
        for (Bid bid : bids) {
            int day = Integer.parseInt(bid.getTermValidity());
            if(time == 0){
                time = 60*60*24*day;
            }
            long difference = isdifference(bid.getBidDate(),time);
            if(difference <= 0){
                b.setId(bid.getId());
                b.setStatus(String.valueOf(status));
                int x = bidService.update(b);
                result = result * x;
            }
        }
        return result;
    }

    private int tradeSend( List<Trade> trades,int time,int status,int type){
        int result = 1;
        long difference = 0;
        Trade t = new Trade();
        Payment p = new Payment();
        for (Trade trade : trades) {
            difference = isdifference(trade.getTransactionDate(),time);
            if(type == 1){
                p.setOrder_record_id(trade.getId());
                p.setPayment_type(3);
                p.setPayment_status(Payment.PAY_STATUS_SUCCESS);
                Payment payment = paymentService.findByTrade(p);
                if(payment != null){
                    difference= isdifference(trade.getTransactionDate(),60*15);
                }
            }
            if(difference <= 0){
                t.setId(trade.getId());
                t.setStatus(status);
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
