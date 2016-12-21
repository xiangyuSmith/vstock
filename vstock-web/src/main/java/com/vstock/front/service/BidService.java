package com.vstock.front.service;

import com.vstock.db.dao.IBidDao;
import com.vstock.db.entity.Bid;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.security.md.ToolMD5;
import org.apache.log4j.Logger;
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

    final static Logger logger = Logger.getLogger(BidService.class);

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
     * 关联峰值表查询
     * @param record
     * @param page
     * @return
     */
    public List<Bid> findAndPricePeak(Bid record, Page page){return bidDao.findAndPricePeak(record,page.getStartPos(),page.getPageSize());}

    public Bid findByBid(Bid record, Page page){
        List<Bid> bidList = findAll(record,page);
        if(bidList.size() > 0){
            return bidList.get(0);
        }
        return null;
    }

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Bid record){return bidDao.insert(record);}

    public int createBid(String bName, int uid, int bId, String size, double amount,String overdueTime,
                         int type,BigDecimal bidBond,int status,String bidDate,String bidMd5Key){
        if(amount <= 0){
            logger.warn("叫价金额为0或小于0");
            return 0;
        }
        String sign = ToolMD5.encodeMD5Hex(new StringBuilder()
                .append("bId=").append(bId)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("bName=").append(bName)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("size=").append(size)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("amount=").append(amount)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("Md5Sign=").append(bidMd5Key)
                .toString());
        Bid bid = new Bid();
        bid.setBftName(bName);
        bid.setUserId(uid);
        bid.setBasicinformationId(bId);
        bid.setBftSize(size);
        bid.setBidMoney(new BigDecimal(amount));
        bid.setInvalidDate(overdueTime);
        bid.setSign(sign);
        bid.setType(type);
        bid.setBidBond(bidBond);
        bid.setStatus(status);
        bid.setBidDate(bidDate);
        return insert(bid);
    }

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Bid record){
        return bidDao.update(record.getStatus(),record.getBidMoney(),record.getInvalidDate(),record.getId());
    }

    //出售记录个人中心查询
    public List<Bid> findBid(Bid record, Page page){
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
}
