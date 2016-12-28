package com.vstock.admin.service;

import com.vstock.db.dao.IBidDao;
import com.vstock.db.entity.Bid;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 带叫价金额区间分页查询
     * @param record   对象
     * @param minimumMoney   最小叫价金额
     * @param maximumMoney   最大叫价金额
     * @return
     */
    public List<Bid> findAndUser(Bid record, BigDecimal minimumMoney, BigDecimal maximumMoney, Page page){
        return bidDao.findAndUser(record,minimumMoney,maximumMoney,page.getStartPos(),page.getPageSize());
    }

    /**
     *  带叫价金额区间总数查询
     * @param record    对象
     * @param minimumMoney    最小叫价金额
     * @param maximumMoney    最大叫价金额
     * @return
     */
    public int findAndUserCount(Bid record, BigDecimal minimumMoney, BigDecimal maximumMoney){
        return bidDao.findAndUserCount(record, minimumMoney, maximumMoney);
    }

    public Map<Page,List<Bid>> findList(Bid record, String pageNow, String minimumMoney, String maximumMoney){
        Map<Page,List<Bid>> param = new HashMap<Page,List<Bid>>();
        BigDecimal minimum = new BigDecimal(0);
        BigDecimal maximum = new BigDecimal(0);
        if (maximumMoney != null && !"".equals(maximumMoney)){
            maximum = new BigDecimal(maximumMoney);
        }
        if (minimumMoney != null && !"".equals(minimumMoney)){
            minimum = new BigDecimal(minimumMoney);
        }
        int dCListCount = this.findAndUserCount(record,minimum,maximum);
        Page page = new Page(dCListCount,pageNow);
        List<Bid> bidList = this.findAndUser(record, minimum,maximum,page);
        param.put(page,bidList);
        return param;
    }

    //下一页地址拼接
    public String linkAddress(String linkAddress, Bid record, String minimumMoney, String maximumMoney){
        if (minimumMoney != null && !"".equals(minimumMoney)){
            linkAddress = linkAddress + "&minimumMoney=" + minimumMoney;
        }
        if (maximumMoney != null && !"".equals(maximumMoney)){
            linkAddress = linkAddress + "&maximumMoney=" + maximumMoney;
        }
        if (record.getName() != null && !"".equals(record.getName())){
            linkAddress = linkAddress + "&name=" + record.getName();
        }
        if (record.getBftSize() != null && !"".equals(record.getBftSize())){
            linkAddress = linkAddress + "&bftSize=" + record.getBftSize();
        }
        if (record.getBftName() != null && !"".equals(record.getBftName())){
            linkAddress = linkAddress + "&bftName=" + record.getBftName();
        }
        linkAddress = linkAddress + "&type=" + record.getType();
        linkAddress = linkAddress + "&status=" + record.getStatus();
        return linkAddress;
    }
}
