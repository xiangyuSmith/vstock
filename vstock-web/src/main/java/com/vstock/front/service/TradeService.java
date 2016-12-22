package com.vstock.front.service;

import com.vstock.db.dao.ITradeDao;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.security.md.ToolMD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
@Service
public class TradeService {

    final static Logger logger = Logger.getLogger(TradeService.class);

    @Autowired
    ITradeDao tradeDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<Trade> findAll(Trade record, Page page){
        return tradeDao.findAll(record,page.getStartPos(),page.getPageSize());
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
    public Trade getLastTrade(int bid,String size,int status, Page page){
        Trade trade = new Trade();
        trade.setBasicinformationId(bid);
        trade.setBftSize(size);
        trade.setStatus(status);
        List<Trade> tradelist = findAll(trade,page);
        if(tradelist.size() != 0){
            return tradelist.get(0);
        }
        return null;
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
                .append("trade_no=").append(trade.getTrandeNo())
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bid_id=").append(trade)
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("transaction_money=").append(trade.getTransactionMoney())
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("bft_size=").append(trade.getBftSize())
                .append(Trade.TRADE_MD5_MARK_NOTIFY).append("Md5Sign=").append(tradeMd5Key)
                .toString());
        trade.setSign(sign);
        int result = insert(trade);
        if(result == 0){
            logger.warn("服务器繁忙，请稍后再试");
            return 0;
        }
        return 1;
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
}
