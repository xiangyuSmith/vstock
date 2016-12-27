package com.vstock.admin.service;

import com.vstock.db.dao.IBidDao;
import com.vstock.db.entity.Bid;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administor on 2016/12/6.
 */
@Service
public class BidService {
    @Autowired
    IBidDao bidDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<Bid> findAll(Bid record, Page page){
        return bidDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(Bid record){return bidDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Bid record){return bidDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Bid record){
        return bidDao.update(record.getStatus(),record.getPaymentId(),record.getBidMoney(),record.getInvalidDate(),record.getId());
    }

    //出售记录个人中心查询
    public List<Bid> findBid(Bid record, Page page){
        page.setPageSize(5);
        return bidDao.findAndPricePeak(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * @param bftSize 尺码
     * @param year 年份
     * @param brand 品牌
     * @param priceStart 价格区间:start
     * @param priceEnd 价格区间:end
     * @return
     */
    public List<Bid> findBidForSorts(String bftSize,String year,String brand,String priceStart,String priceEnd){
        return bidDao.findBidForSorts(bftSize,year,brand,priceStart,priceEnd);
    }
}
