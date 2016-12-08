package com.vstock.front.service;

import com.vstock.db.dao.IPricePeakDao;
import com.vstock.db.entity.PricePeak;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
