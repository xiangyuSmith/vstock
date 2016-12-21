package com.vstock.front.service;

import com.vstock.db.dao.IUserAssetsDao;
import com.vstock.db.entity.UserAssets;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
@Service
public class UserAssetsService {
    @Autowired
    IUserAssetsDao userAssetsDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<UserAssets> findAll(UserAssets record, Page page){
        return userAssetsDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(UserAssets record){return userAssetsDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(UserAssets record){return userAssetsDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(UserAssets record){
        return userAssetsDao.update(record.getStatus(),record.getInvalidDate(),record.getId());
    }

    /**
     * 关联涨幅表查询所有
     * @param record
     * @return
     */
    public List<UserAssets> findBasicinformationRoseAll(UserAssets record){return userAssetsDao.findBasicinformationRoseAll(record);}

    public List<UserAssets> findUserAssets(UserAssets record){
        Page page = new Page(5,"1");
        return this.findAll(record,page);
    }

}
