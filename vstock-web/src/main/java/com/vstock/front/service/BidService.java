package com.vstock.front.service;

import com.vstock.db.dao.IBidDao;
import com.vstock.db.entity.Bid;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.security.md.ToolMD5;
import com.vstock.front.service.interfaces.IVstockConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("bid")
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

    public List<Bid> findNewAll(Bid record, Page page){
        return bidDao.findNewAll(record,page.getStartPos(),page.getPageSize());
    }

    public List<Bid> findAllBid(Bid record){
        return bidDao.findAllBid(record);
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
                         String type,BigDecimal bidBond,String status,String bidDate,String bidMd5Key){
        if(amount <= 0){
            logger.warn("叫价金额为0或小于0");
            return 0;
        }
        String sign = getBidSign(bId,size,amount,bidMd5Key);
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
        insert(bid);
        int biid = bid.getId();
        return biid;
    }

    public String getBidSign(int bId,String size,double amount,String bidMd5Key){
        return ToolMD5.encodeMD5Hex(new StringBuilder()
                .append("bId=").append(bId)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("size=").append(size)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("amount=").append(amount)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("Md5Sign=").append(bidMd5Key)
                .toString());
    }

    /**
     * 校验签名
     * @param bid   叫价id
     * @param basicinformationId    鞋库id
     * @param size  鞋码
     * @param amount    金额
     * @param bidMd5Key
     * @return boolean
     */
    public boolean isBidSign(int bid,int basicinformationId,String size,double amount,String bidMd5Key){
        Bid b = new Bid();
        b.setId(bid);
        Page page = new Page();
        page.setStartPos(0);
        page.setPageSize(1);
        Bid bidObj = findByBid(b,page);
        String s = getBidSign(basicinformationId,size,amount,bidMd5Key);
        return bidObj.getSign().equals(getBidSign(basicinformationId,size,amount,bidMd5Key));
    }

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Bid record){
        return bidDao.update(record.getStatus(),record.getPaymentId(), record.getBidMoney(),record.getInvalidDate(),record.getId());
    }

    /**
     * 查询所有（根据金额倒序）
     * @param record
     * @return
     */
    public List<Bid> findOrderByMoney(Bid record){return bidDao.findOrderByMoney(record);}

    //出售记录个人中心查询
    public List<Bid> findBid(Bid record, Page page){
        return bidDao.findAndPricePeak(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 最高/最低 叫价/出价 记录
     * @param bid  鞋库编号
     * @param type 0；卖家    1：买家
     * @param sort 排序方式 1：ASC  2：DESC
     * @return
     */
    public Bid getHightAndMinPrice(int bid,String type,int sort, Page page){
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

    //修改状态
    public int updateBid(String id, String btfId, String status, String size, String endDate, String bidMoney){
        Bid record = new Bid();
        record.setInvalidDate(DateUtils.dateToString(new Date()));
        record.setPaymentId(-1);
        if (id != null && !"".equals(id)) {
            record.setId(Integer.parseInt(id));
         }
        if (endDate != null && !"".equals(endDate)) {
            record.setInvalidDate(endDate);
        }
        if (bidMoney != null && !"".equals(bidMoney)) {
             BigDecimal bigDecimal = new BigDecimal(bidMoney);
             record.setBidMoney(bigDecimal);
        }
        if (status != null && !"".equals(status)) {
            record.setStatus(status);
            if (this.isBidSign(Integer.parseInt(id),Integer.parseInt(btfId),size,Double.parseDouble(bidMoney),VstockConfigService.getConfig(IVstockConfigService.BID_VSTOCK_MD5KEY))) {
                return this.update(record);
            }
        }else {
            return this.update(record);
        }
        return 0;
    }
}
