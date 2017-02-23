package com.vstock.front.service;

import com.vstock.db.dao.IPaymentDao;
import com.vstock.db.dao.IRefundDao;
import com.vstock.db.dao.ITradeDao;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.OddNoUtil;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.security.md.ToolMD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("trade")
public class TradeService {

    final static Logger logger = Logger.getLogger(TradeService.class);

    @Autowired
    ITradeDao tradeDao;
    @Autowired
    IRefundDao refundDao;
    @Autowired
    BidService bidService;
    @Autowired
    IPaymentDao paymentDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<Trade> findAll(Trade record, Page page){
        return tradeDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    public List<Trade> findAllSale(Trade record, Page page){
        return tradeDao.findAllSale(record,page.getStartPos(),page.getPageSize());
    }


    public List<Trade> findAllTrade(Trade record){
        return tradeDao.findAllTrade(record);
    }

    /**
     * 链表查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<Trade> findAndBid(Trade record, Page page){return tradeDao.findAndBid(record,page.getStartPos(),page.getPageSize());}

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(Trade record){return tradeDao.findCount(record);}

    /**
     * 获取最后成交价
     * @return
     */
    public Trade getLastTrade(int bid,String size, Page page){
        Trade trade = new Trade();
        trade.setBasicinformationId(bid);
        trade.setBftSize(size);
        List<Trade> tradelist = findAllSale(trade,page);
        if(tradelist.size() != 0){
            return tradelist.get(0);
        }
        return null;
    }

    /**
     * 校验当前状态并返回更新后的状态
     * @param tradeId
     * @param page
     * @return
     */
    public int checkTradeStatus(int tradeId, Page page){
        Trade trade = new Trade();
        trade.setId(tradeId);
        List<Trade> tradelist = findAll(trade,page);
        if(tradelist.size() != 0){
            Trade t = tradelist.get(0);
            if(t.getStatus()>0){
                return Trade.TRADE_PAY_LOGISTICS;
            }
            return Trade.TRADE_NOTIFIY_PAY;
        }
        return -1;
    }

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Trade record){return tradeDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Trade record){
        return tradeDao.update(record.getStatus(),record.getUpdateDate(),record.getId());
    }

    public int updateAll(Trade record){return tradeDao.updateAll(record);}

    /**
     * web个人中心查询
     * @param record
     * @param page
     * @return
     */
    public List<Trade> findAllWeb (Trade record,Page page){return tradeDao.findAllWeb(record,page.getStartPos(),page.getPageSize());}

    /**
     * web前台个人中心查询
     * @param record
     * @return
     */
    public int findCountWeb(Trade record){return tradeDao.findCountWeb(record);}

    //个人中心出售查询
    public List<Trade> findTrade(Trade record, Page page){
        return this.findAndBid(record, page);
    }

    /**
     * 直接出售第一步：下单
     * @param trade
     * @param tradeMd5Key
     * @return
     */
    public int createTradeOne(Trade trade,String tradeMd5Key){
        String sign = ToolMD5.encodeMD5Hex(new StringBuilder()
                .append("trade_no=").append(trade.getTradeNo())
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bid_id=").append(trade.getBid())
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("transaction_money=").append(trade.getTransactionMoney())
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bft_size=").append(trade.getBftSize())
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("Md5Sign=").append(tradeMd5Key)
                .toString());
        trade.setSign(sign);
        insert(trade);
        if(trade.getId() == 0){
            logger.warn("服务器繁忙，请稍后再试");
            return 0;
        }
        return trade.getId();
    }

    public boolean verificationSgin(Trade record,String tradeMd5Key){
        Trade trade = new Trade();
        trade.setId(record.getId());
        if(record.getTradeNo() == ""){
            record.setTradeNo(null);
        }
        List<Trade> tradeList = this.findAllTrade(trade);
        if (tradeList.size() > 0 ){
            String sign = ToolMD5.encodeMD5Hex(new StringBuilder()
                    .append("trade_no=").append(record.getTradeNo())
                    .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bid_id=").append(record.getBid())
                    .append(Trade.TRADE_MD5_MARK_NOTIFY).append("transaction_money=").append(record.getTransactionMoney())
                    .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bft_size=").append(record.getBftSize())
                    .append(Trade.TRADE_MD5_MARK_NOTIFY).append("Md5Sign=").append(tradeMd5Key)
                    .toString());
            if (tradeList.get(0).getSign().equals(sign)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 修改订单信息
     * @param record  修改对象
     * @param tradeMd5Key   加密串
     * @return
     */
    public int save(Trade record,String tradeMd5Key){
        int i = 0;
        if (record.getId() != null && !"".equals(record.getId())){
            if (this.verificationSgin(record,tradeMd5Key)) {
                //判断是买家删除还是买家删除
                if (record.getStatus() == 51 || record.getStatus() == 52){
                    Trade trade = new Trade();
                    trade.setId(record.getId());
                    List<Trade> tradeList = this.findAllTrade(trade);
                    if (tradeList.size() > 0){
                        //判断该订单是否买卖家都删除
                        if (tradeList.get(0).getStatus() == 51 || tradeList.get(0).getStatus() == 52) {
                            record.setStatus(50);
                        }
                    }
                }
                i = this.update(record);
                //判断确认收款创建卖家退款单
                if (i > 0 && record.getStatus() == 40){
                    Bid bid = new Bid();
                    Refund refund = new Refund();
                    bid.setId(record.getBidId());
                    bid = bidService.findbid(bid);
                    refund.setRefundNo(OddNoUtil.refundNo());
                    refund.setTradeNo(record.getTradeNo());
                    refund.setRefundObj("1");
                    refund.setBtfId(bid.getBasicinformationId());
                    refund.setBtfName(bid.getBftName());
                    refund.setRefundPrice(bid.getBidBond());
                    refund.setStatus("0");
                    refund.setType("4");
                    refund.setCreateDate(DateUtils.getCurrentTimeAsString());
                    refundDao.insert(refund);
                }
            }
        }else {
            i = this.createTradeOne(record,tradeMd5Key);
        }
        return i;
    }

    //获取订单状态
    public List<Trade> status(){
        List<Trade> tradeList = new ArrayList<Trade>();
        for (String str : Trade.statuses){
            Trade trade = new Trade();
            String[] status = str.split(":");
            trade.setId(Integer.parseInt(status[0]));
            trade.setBftName(status[1]);
            tradeList.add(trade);
        }
        return tradeList;
    }

    /**
     * 查询鞋子某个尺码或者全部每天的销售价格
     * @param bidId  鞋子ID
     * @param size   鞋码
     * @param startDate   创建开始时间
     * @param endDate     创建结束时间
     * @return
     */
    public List<Point> tradeHchar(String bidId, String size, String startDate, String endDate){
        List<Point> pointList = new ArrayList<Point>();
        Long date = DateUtils.getCurrentAllDays(DateUtils.getDate(startDate,"yyyy-MM-dd"),DateUtils.getDate(endDate,"yyyy-MM-dd"),true);
        Trade record = new Trade();
        record.setBasicinformationId(Integer.parseInt(bidId));
        Page page = new Page(100000,"1");
        page.setStartPos(0);
        page.setPageSize(100000);
        record.setBftSize(size);
        for (int i = 0; i < date; i++) {
            String time =  DateUtils.dateToString(DateUtils.wantToLose(DateUtils.getDate(startDate,"yyyy-MM-dd"),-i),"yyyy-MM-dd");
            List<Trade> tradeList = tradeDao.findAllDate(record, page.getStartPos(), page.getPageSize(), time+" 00:00:00", time+" 23:59:59");
            for (Trade trade : tradeList) {
                Point point = new Point();
                point.setX(DateUtils.getDate(trade.getTransactionDate(),"yyyy-MM-dd HH:mm:ss").getTime());
//                DateUtils.getDate(trade.getTransactionDate() , "yyyy-MM-dd HH:mm:ss");
                point.setY(trade.getTransactionMoney().intValue());
                pointList.add(point);
            }
        }
        return pointList;
    }

    /**
     * 查询运费表
     * @param cityname 城市名称
     * @return
     */
    public BigDecimal findAllYunFee(String cityname){
        List<TradeYunfee> tradeYunfeeList = tradeDao.findAllYunFee();
        cityname = cityname.substring(0,2);
        for (TradeYunfee yunfee : tradeYunfeeList) {
            boolean isCity = yunfee.getCityName().contains(cityname);
            if(isCity){
                return yunfee.getFeeMoney();
            }
        }
        return null;
    }

}
