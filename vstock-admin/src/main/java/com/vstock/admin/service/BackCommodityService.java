package com.vstock.admin.service;

import com.vstock.db.dao.IBackCommodityDao;
import com.vstock.db.entity.BackCommodity;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackCommodityService {

    @Autowired
    IBackCommodityDao backCommodityDao;

    /**
     * 查询所有
     * @param record
     * @return
     */
    public List<BackCommodity> findAll(BackCommodity record){return backCommodityDao.findAll(record);}

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(BackCommodity record){return backCommodityDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(BackCommodity record){return backCommodityDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(BackCommodity record){return backCommodityDao.update(record);}

    /**
     * 分页链表鞋库表查询
     * @param record
     * @param page
     * @return
     */
    public List<BackCommodity> findAndBtf(BackCommodity record, String startTime, String endTime, Page page){return backCommodityDao.findAndBtf(record,startTime,endTime,page.getStartPos(),page.getPageSize());}

    /**
     * 链表鞋库表查询总数
     * @param record
     * @return
     */
    public int findAndBtfCount(BackCommodity record, String startTime, String endTime){return backCommodityDao.findAndBtfCount(record,startTime,endTime);};

}
