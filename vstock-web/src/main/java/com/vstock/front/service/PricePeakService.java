package com.vstock.front.service;

import com.vstock.db.dao.IPricePeakDao;
import com.vstock.db.entity.PricePeak;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.front.support.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/8.
 */
@Service
public class PricePeakService {
    @Autowired
    IPricePeakDao pricePeakDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<PricePeak> findAll(PricePeak record, Page page){
        return pricePeakDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 出价和叫价
     * @param record
     * @param page
     * @return
     */
    public List<PricePeak> findByType(PricePeak record,int sort, Page page){
        String startTime = ToolDateTime.format(new Date(),ToolDateTime.pattern_ymd) + " 00:00:00";
        String endTime = ToolDateTime.format(new Date(),ToolDateTime.pattern_ymd) + " 23:59:59";
        return pricePeakDao.findByType(record,sort,page.getStartPos(),page.getPageSize(),startTime,endTime);
    }

    /**
     * 最低卖价 & 最高叫价
     */
    public List<PricePeak> findBySellAndBuy(int sort){
        return pricePeakDao.findBySellAndBuy(sort);
    }

    /**
     * 获取最高/最低
     */
//    public List<PricePeak> findAllByhightMin(){
//        return pricePeakDao.findAllByhightMin(sort);
//    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(PricePeak record){return pricePeakDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(PricePeak record){return pricePeakDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(PricePeak record){
        return pricePeakDao.update(record);
    }
    /**
     * 修改
     * @param record
     * @return
     */
    public int updateX(PricePeak record){
        return pricePeakDao.updateX(record);
    }

    public int updateY(PricePeak record){
        return pricePeakDao.updateY(record);
    }

    /**
     * @param sort
     * @return
     */
    public PricePeak getHighestAndlowest(int bid,String size,int sort, Page page){
        PricePeak pricePeak = new PricePeak();
        pricePeak.setBasicinformationId(bid);
        pricePeak.setPeakSize(size);
        pricePeak.setStatus(0);
        List<PricePeak> pricePeaks = findByType(pricePeak,sort,page);
        if(pricePeaks.size() != 0){
            return pricePeaks.get(0);
        }
        return null;
    }

    public PricePeak getHighestAndlowestDate(int bid,String size,int sort, Page page,String startTime,String endTime){
        PricePeak pricePeak = new PricePeak();
        pricePeak.setBasicinformationId(bid);
        pricePeak.setPeakSize(size);
        pricePeak.setStatus(0);
        startTime = startTime + " 00:00:00";
        endTime = endTime + " 23:59:59";
        List<PricePeak> pricePeaks = pricePeakDao.findByType(pricePeak,sort,page.getStartPos(),page.getPageSize(),startTime,endTime);
        if(pricePeaks.size() != 0){
            return pricePeaks.get(0);
        }
        return null;
    }

    public PricePeak getHighestAndlowest(int bid,String size,String dateTime, Page page){
        PricePeak pricePeak = new PricePeak();
        pricePeak.setBasicinformationId(bid);
        pricePeak.setPeakSize(size);
        pricePeak.setCreateDate(DateUtils.dateToString(DateUtils.getDate(dateTime,"yyyy-MM-dd"),"yyyy-MM-dd"));
        List<PricePeak> pricePeaks = findAll(pricePeak,page);
        if(pricePeaks.size() != 0){
            return pricePeaks.get(0);
        }
        return null;
    }

    /**
     * 新增 or 更新 - 价格峰值
     * @param pricePeak
     * @param amount
     * @param bId
     * @param size
     * @param userId
     * @return
     */
    public int isAmount(PricePeak pricePeak,BigDecimal amount,int bId,String size,String userId,int type){
        int result = 0;
        if(pricePeak != null){
            if(type == 1){
                if (pricePeak.getHighestBid() != null && !"".equals(pricePeak.getHighestBid())) {
                    if (amount.compareTo(pricePeak.getHighestBid()) == 1) {
                        pricePeak.setHighestBid(amount);
                        pricePeak.setHighestBidderId(userId);
                        result = update(pricePeak);
                    }
                }else {
                    pricePeak.setHighestBid(amount);
                    pricePeak.setHighestBidderId(userId);
                    result = update(pricePeak);
                }
            }else if(type == 0){
                if (pricePeak.getMinimumSellingPrice() != null && !"".equals(pricePeak.getMinimumSellingPrice())) {
                    if (amount.compareTo(pricePeak.getMinimumSellingPrice()) == -1) {
                        pricePeak.setMinimumSellingPrice(amount);
                        pricePeak.setMinimumSellingId(userId);
                        result = update(pricePeak);
                    }
                }else {
                    pricePeak.setMinimumSellingPrice(amount);
                    pricePeak.setMinimumSellingId(userId);
                    result = update(pricePeak);
                }
            }
        }else{
            if(type == 0){
                PricePeak p = new PricePeak( bId, size , null , amount, 0, "" , userId, DateUtils.dateToString(new Date()));
                result = insert(p);
            }else if(type == 1){
                PricePeak p = new PricePeak( bId, size , amount , null, 0, userId , "", DateUtils.dateToString(new Date()));
                result = insert(p);
            }
        }
        return result;
    }

    public int save(Page page, String amount,String bId,String size,String userId,String type){
        PricePeak record = new PricePeak();
        record.setBasicinformationId(Integer.parseInt(bId));
        record.setPeakSize(size);
        List<PricePeak> pricePeakList = this.findAll(record,page);
        if (pricePeakList.size() > 0){
            record = pricePeakList.get(0);
        }
        return this.isAmount(record,new BigDecimal(amount),Integer.parseInt(bId),size,userId,Integer.parseInt(type));
    }

}
