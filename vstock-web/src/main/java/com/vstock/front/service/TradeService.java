package com.vstock.front.service;

import com.vstock.db.dao.ITradeDao;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
@Service
public class TradeService {
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
        return tradeDao.update(record.getStatus(),record.getEndDate(),record.getId());
    }

    //个人中心出售查询
    public List<Trade> findTrade(Trade record, Page page){
        page.setPageSize(5);
        return tradeDao.findAndBid(record,page.getStartPos(),page.getPageSize());
    }

}
