package com.vstock.front.service;

import com.vstock.db.dao.IExpressDao;
import com.vstock.db.entity.Express;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressService {
    @Autowired
    IExpressDao expressDao;

    /**
     * 查询所有记录
     * @param record
     * @return
     */
    public List<Express> findAll(Express record){
        return expressDao.findAll(record);
    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(Express record){return expressDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Express record){return expressDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Express record){return expressDao.update(record);}

    /**
     * 保存方法
     * @param record
     * @return
     */
    public int save(Express record){
        if (record.getId() != null){
            return this.update(record);
        }else {
            return this.insert(record);
        }
    }

}
