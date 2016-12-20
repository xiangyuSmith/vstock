package com.vstock.front.service;

import com.vstock.db.dao.IUserAddressDao;
import com.vstock.db.entity.UserAddress;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
@Service
public class UserAddressService {
    @Autowired
    IUserAddressDao userAddressDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<UserAddress> findAll(UserAddress record, Page page){
        return userAddressDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(UserAddress record){return userAddressDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(UserAddress record){return userAddressDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(UserAddress record){
        return userAddressDao.update(record.getStatus(),record.getInvalidDate(),record.getId());
    }

    public List<UserAddress> findAllUserAddress(UserAddress record){
        Page page = new Page(5,"1");
        return this.findAll(record, page);
    }

}
