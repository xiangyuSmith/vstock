package com.vstock.front.service;

import com.vstock.db.dao.IBidDao;
import com.vstock.db.entity.Bid;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/6.
 */
@Service
public class BidService {

    public final static String TIME_ONE = "1";
    public final static String TIME_THREE = "3";
    public final static String TIME_FIVE = "5";
    public final static String TIME_SERVEN = "7";

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
        return bidDao.update(record.getStatus(),record.getBidMoney(),record.getInvalidDate(),record.getId());
    }

    /**
     * 带峰值查询
     * @param record
     * @param page
     * @return
     */
    public List<Bid> findAndPricePeak(Bid record, Page page){
        return bidDao.findAndPricePeak(record,page.getStartPos(),page.getPageSize());
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

    /**
     * 最高/最低 叫价/出价 记录
     * @param bid  鞋库编号
     * @param type 0；卖家    1：买家
     * @param sort 排序方式 1：ASC  2：DESC
     * @return
     */
    public Bid getHightAndMinPrice(int bid,int type,int sort, Page page){
        Bid bids = new Bid();
        bids.setBasicinformationId(bid);
        bids.setType(type);
        Bid b = bidDao.findByType(bids,sort,page.getStartPos(),page.getPageSize());
        return b;
    }

    /**
     * 计算时间
     * @param overdueTime
     * @return
     */
    public String getOverDueTime(String overdueTime){
        switch (overdueTime) {
            case TIME_ONE:
                return DateUtils.dateToString(DateUtils.addDaysToDate(new Date(),1));
            case TIME_THREE:
                return DateUtils.dateToString(DateUtils.addDaysToDate(new Date(),1));
            case TIME_FIVE:
                return DateUtils.dateToString(DateUtils.addDaysToDate(new Date(),1));
            case TIME_SERVEN:
                return DateUtils.dateToString(DateUtils.addDaysToDate(new Date(),1));
            default:
                return DateUtils.dateToString(DateUtils.addDaysToDate(new Date(),1));
        }
    }

    public int updateBid(String id, String status, String endDate, String bidMoney){
        Bid record = new Bid();
        if (id != null && !"".equals(id)) {
            record.setId(Integer.parseInt(id));
        }
        if (status != null && !"".equals(status)) {
            record.setStatus(Integer.parseInt(status));
        }
        if (endDate != null && !"".equals(endDate)) {
            record.setInvalidDate(endDate);
        }
        if (bidMoney != null && !"".equals(bidMoney)) {
            BigDecimal bigDecimal = new BigDecimal(bidMoney);
            record.setBidMoney(bigDecimal);
        }
        return this.update(record);
    }

}
