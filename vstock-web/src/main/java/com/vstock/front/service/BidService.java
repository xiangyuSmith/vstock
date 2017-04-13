package com.vstock.front.service;

import com.vstock.db.dao.IBasicinformation;
import com.vstock.db.dao.IBidDao;
import com.vstock.db.dao.IPricePeakDao;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.Bid;
import com.vstock.db.entity.PricePeak;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.ext.util.security.md.ToolMD5;
import com.vstock.front.service.interfaces.IVstockConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("bid")
public class BidService {

    final static Logger logger = Logger.getLogger(BidService.class);

    public final static String TIME_ONE = "1";
    public final static String TIME_THREE = "3";
    public final static String TIME_FIVE = "5";
    public final static String TIME_SERVEN = "7";

    @Autowired
    IBidDao bidDao;
    @Autowired
    IBasicinformation basicinformationDao;
    @Autowired
    IPricePeakDao pricePeakDao;

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
        List<Bid> bidList = bidDao.findNewAll(record,page.getStartPos(),page.getPageSize());
        //打乱排序
        Collections.shuffle(bidList);
        return bidList;
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
     * 去掉已删除状态的查询总数
     * @param record
     * @return
     */
    public int findWebCount(Bid record){return bidDao.findWebCount(record);}

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

    public Bid findbid(Bid record){
        Page page = new Page(10,"1");
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
        String invalidDate = getOverDueTime(overdueTime);
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
        bid.setInvalidDate(invalidDate);
        bid.setTermValidity(overdueTime);
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
        return bidDao.update(record.getStatus(),record.getPaymentId(), record.getBidMoney(),record.getInvalidDate(),record.getId(),record.getBidDate());
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

    //出售记录个人中心状态正序查询
    public List<Bid> findBidStatus(Bid record, Page page){
        return bidDao.findOrderStatus(record,page.getStartPos(),page.getPageSize());
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
    public int updateBid(String id, String btfId, String status, String size, String endDate, String bidMoney, String bidDate){
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
        record.setBidDate(bidDate);
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

    public List<Bid> findAllBidOrder(Bid record,Integer sort){
        return bidDao.findAllBidOrder(record,sort);
    }

    /**
     * 校验当前金额
     * @param money
     * @param size
     * @param basicinfortionId
     * @param type
     * @return
     */
    public int checkMoney(BigDecimal money,String size,Integer basicinfortionId,Integer type){
        Bid b = new Bid();
        b.setBftSize(size);
        b.setBasicinformationId(basicinfortionId);
        b.setType("0");
        List<Bid> bidSellList = findAllBidOrder(b,0);
        b.setType("1");
        List<Bid> bidBuyList = findAllBidOrder(b,1);
        return checkSell(bidSellList,bidBuyList,type,money);
    }

    /**
     * 遍历map参数
     */
    public Map<String,String> eachMap(Map requestParams){
        Map<String,String> params = new HashMap<String,String>();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 当前鞋子信息
     * @param sellBid 当前鞋子最低卖价
     * @param buyBid  当前鞋子最高售价
     * @param type  类型：0 卖家   1：买家
     * @return
     */
    public int checkSell(List<Bid> sellBid,List<Bid> buyBid,Integer type,BigDecimal money){
        if(sellBid.size() == 0 && buyBid.size() == 0){
            if (money.equals(new BigDecimal(0))) {
                return 0;
            }else {
                return 6;
            }
        }
        if(sellBid.size() > 0){
            if(money.compareTo(sellBid.get(0).getBidMoney()) == 1){
                return 1;
            }
            if(money.compareTo(sellBid.get(0).getBidMoney()) == 0){
                return 2;
            }
            if(money.compareTo(sellBid.get(0).getBidMoney()) == -1){
                return 6;
            }
        }
        if(type == 0){
            if(sellBid.size() > 0 && buyBid.size() > 0) {
                if (money.compareTo(sellBid.get(0).getBidMoney()) == -1 && money.compareTo(buyBid.get(0).getBidMoney()) == 1) {
                    return 3;
                }
            }
            if(buyBid.size() > 0){
                if(money.compareTo(buyBid.get(0).getBidMoney()) == 0){
                    return 4;
                }
                if(money.compareTo(buyBid.get(0).getBidMoney()) == -1){
                    return 5;
                }
            }
        }else{
            if(sellBid.size() > 0 && buyBid.size() > 0) {
                if(money.compareTo(sellBid.get(0).getBidMoney()) == -1 && money.compareTo(buyBid.get(0).getBidMoney()) == 1){
                    return 3;
                }
            }
            if(buyBid.size() > 0){
                if(money.compareTo(buyBid.get(0).getBidMoney()) == -1){
                    return 4;
                }
                if(money.compareTo(buyBid.get(0).getBidMoney()) == 0){
                    return 5;
                }
                if(money.compareTo(buyBid.get(0).getBidMoney()) == 1){
                    return 6;
                }
            }
        }
        return 0;
    }

    public void peakPriceJobBid(){
        Basicinformation b = new Basicinformation();
        List<Basicinformation> basicinformationList = basicinformationDao.findAll(b);
        Bid bi = new Bid();
        for (Basicinformation bfm : basicinformationList) {
            for (String size : Basicinformation.sizes) {
                bi.setBasicinformationId(Integer.parseInt(bfm.getId()));
                bi.setBftSize(size);
                bi.setStatus("10");
                //买家出价
                bi.setType("1");
                Bid buybid = bidDao.findByType(bi,2,0,1);
                //卖家叫价
                bi.setType("0");
                Bid sellbid = bidDao.findByType(bi,1,0,1);
                PricePeak pricePeak = new PricePeak();
                pricePeak.setBasicinformationId(Integer.parseInt(bfm.getId()));
                pricePeak.setPeakSize(size);
                if(buybid != null){
                    pricePeak.setHighestBid(buybid.getBidMoney());
                    pricePeak.setHighestBidderId(String.valueOf(buybid.getUserId()));
                }
                if(sellbid != null){
                    pricePeak.setMinimumSellingPrice(sellbid.getBidMoney());
                    pricePeak.setMinimumSellingId(String.valueOf(sellbid.getUserId()));
                }
                pricePeak.setStatus(0);
                pricePeak.setCreateDate(ToolDateTime.format(new Date(),ToolDateTime.pattern_ymd_hms));
                if(buybid != null || sellbid != null){
                    pricePeakDao.insert(pricePeak);
                }
            }
        }
    }
}
