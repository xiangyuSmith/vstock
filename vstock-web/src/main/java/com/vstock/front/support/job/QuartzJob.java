package com.vstock.front.support.job;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.CustomJob;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.ext.util.ToolSpring;
import com.vstock.front.service.BasiciformationRoseService;
import com.vstock.front.service.BidService;
import com.vstock.front.service.ResultDataService;
import com.vstock.front.service.TradeService;
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
//            basiciformationRoseService.getRoseDegree();
        }
    }

    private int quartzBid(){
        Bid record = new Bid();
        record.setStatus(String.valueOf(record.STATUS_PENDING));
        List<Bid> bidlist = bidService.findAllBid(record);
        Bid b = new Bid();
        int result = 1;
        for (Bid bid : bidlist) {
            long difference = isdifference(bid.getBidDate(),60*15);
            if(difference <= 0){
                b.setId(bid.getId());
                b.setStatus("11");
                int x = bidService.update(b);
                result = result * x;
            }
        }
        return result;
    }

    private int quartzTrade(){
        Trade record = new Trade();
        record.setStatus(record.TRADE_NOTIFIY_PAY);
        List<Trade> tradeList = tradeService.findAllTrade(record);
        Trade t = new Trade();
        int result = 1;
        for (Trade trade : tradeList) {
            long difference = isdifference(trade.getTransactionDate(),60*60*24);
            if(difference <= 0){
                t.setId(trade.getId());
                t.setStatus(41);
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
        long difference = (bid_time+second)-now_time;
        return difference;
    }
}
