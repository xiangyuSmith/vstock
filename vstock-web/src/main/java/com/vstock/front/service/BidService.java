package com.vstock.front.service;

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
        return bidDao.findAll(record,page.getStartPos(),page.getPageSize()/2);
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

    public int update(Bid record){
        return bidDao.update(record.getStatus(),record.getInvalidDate(),record.getId());
    }
}
