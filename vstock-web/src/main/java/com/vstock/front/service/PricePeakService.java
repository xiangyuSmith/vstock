package com.vstock.front.service;

import com.vstock.db.dao.IPricePeakDao;
import com.vstock.db.entity.PricePeak;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
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
        return pricePeakDao.findByType(record,sort,page.getStartPos(),page.getPageSize());
    }

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
        return pricePeakDao.update(record.getStatus(),record.getInvalidDate(),record.getId());
    }


    public PricePeak getHighestAndlowest(int bid,String size,int sort, Page page){
        PricePeak pricePeak = new PricePeak();
        pricePeak.setBasicinformationId(bid);
        pricePeak.setPeakSize(size);
        List<PricePeak> pricePeaks = findByType(pricePeak,sort,page);
        if(pricePeaks.size() != 0){
            return pricePeaks.get(0);
        }
        return null;
    }

    public PricePeak getHighestAndlowest(int bid,String size,String dateTime, Page page){
        PricePeak pricePeak = new PricePeak();
        pricePeak.setBasicinformationId(bid);
        pricePeak.setPeakSize(size);
        pricePeak.setCreateDate(dateTime);
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
            if(amount.compareTo(pricePeak.getHighestBid()) == 1){
                pricePeak.setHighestBid(amount);
                result = update(pricePeak);
            }
        }else{
            if(type == 0){
                PricePeak p = new PricePeak( bId, size , null , amount, 0, userId , "", DateUtils.dateToString(new Date()));
                result = insert(p);
            }else if(type == 1){
                PricePeak p = new PricePeak( bId, size , amount , null, 0, userId , "", DateUtils.dateToString(new Date()));
                result = insert(p);
            }
        }
        return result;
    }

}
