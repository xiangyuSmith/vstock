package com.vstock.admin.service;

import com.vstock.db.dao.IRefundDao;
import com.vstock.db.entity.Refund;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundService {
    @Autowired
    IRefundDao refundDao;

    /**
     * 查询所有记录
     * @param record
     * @return
     */
    public List<Refund> findAll(Refund record, Page page){
        return refundDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询一个对象
     * @param record
     * @return
     */
    public Refund find(Refund record){return refundDao.find(record);}

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(Refund record){return refundDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Refund record){return refundDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Refund record){return refundDao.update(record);}

    /**
     * 保存方法
     * @param record
     * @return
     */
    public int save(Refund record){
        if (record.getId() != null){
            return this.update(record);
        }else {
            return this.insert(record);
        }
    }

}
