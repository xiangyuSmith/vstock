package com.vstock.admin.service;

import com.vstock.db.dao.IBasicinformationRoseDao;
import com.vstock.db.dao.IUserAssetsDao;
import com.vstock.db.dao.IUserDao;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sunson on 2015/11/21.
 */
@Service
public class UserService {

    User user;

    @Autowired
    IUserDao userDao;

    @Autowired
    IUserAssetsDao userAssetsDao;

    @Autowired
    IBasicinformationRoseDao basicinformationRoseDao;

    public User getUser() {
        return user;
    }

    /**
     * 查询一个用户对象
     * @param user
     * @return
     */
    public User findUser(User user){return userDao.findUser(user);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(User record){return userDao.update(record);}

    /**
     *分页查询所有
     * @param record
     * @return
     */
    public List<User> findAll(User record, Page page){return userDao.findAll(record,page.getStartPos(),page.getPageSize());}

    /**
     *分页查询所有（带时间区间条件）
     * @param record
     * @return
     */
    public List<User> findAllDate(User record, Page page, String startCreateTime, String endCreateTime){return userDao.findAllDate(record,page.getStartPos(),page.getPageSize(),startCreateTime,endCreateTime);}

    public List<UserActivity> findActivityAll(Page page){ return userDao.findActivityAll(page.getStartPos(),page.getPageSize()); }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(User record){return userDao.findCount(record);}

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCountDate(User record, String startCreateTime, String endCreateTime){return userDao.findCountDate(record, startCreateTime, endCreateTime);}

    public int findActivityCount(){return userDao.findActivityCount();}

    //拼接查詢條件
    public String linkAddress(String linkAddress, User record, String startCreateTime, String endCreateTime){
        if (record.getNick() != null && !"".equals(record.getNick())){
            linkAddress = linkAddress + "&nick=" + record.getNick();
        }
        if (record.getMobile() != null && !"".equals(record.getMobile())){
            linkAddress = linkAddress + "&mobile=" + record.getMobile();
        }
        if (record.getSize() != null && !"".equals(record.getSize())){
            linkAddress = linkAddress + "&size=" + record.getSize();
        }
        if (record.getStatus() != null && !"".equals(record.getStatus())){
            linkAddress = linkAddress + "&status=" + record.getStatus();
        }
        if (startCreateTime != null && !"".equals(startCreateTime)){
            linkAddress = linkAddress + "&startCreateTime=" + startCreateTime;
        }
        if (endCreateTime != null && !"".equals(endCreateTime)){
            linkAddress = linkAddress + "&endCreateTime=" + endCreateTime;
        }
        return linkAddress;
    }

    /**
     * 保存方法
     * @param record
     * @return
     */
    public int save(User record){
        if (record.getId() != null && !"".equals(record.getId())){
            record.setUpdate_time(DateUtils.getCurrentTimeAsString());
            return this.update(record);
        }else {
            return 0;
        }
    }

    /**
     * 获取个人总资产价值
     * @param userId   用户ID
     * @return
     */
    public String totalAccount(String userId){
        BigDecimal amont = new BigDecimal(0);
        UserAssets record = new UserAssets();
        record.setUserId(Integer.parseInt(userId));
        List<UserAssets> userAssetsList = userAssetsDao.find(record);
        //循环获取个人资产
        for (UserAssets userAssets : userAssetsList){
            BasicinformationRose basicinformationRose = new BasicinformationRose();
            basicinformationRose.setBasicinformation_id(userAssets.getBasicinformationId());
            basicinformationRose.setBasicinformation_size(userAssets.getUserAssetsSize());
            List<BasicinformationRose> basicinformationRoseList = basicinformationRoseDao.findAllDate(basicinformationRose);
            //获取个人资产总价值
            if (basicinformationRoseList.size() > 0){
                basicinformationRose = basicinformationRoseList.get(0);
                amont = amont.add(basicinformationRose.getCurrent_market_value());
            }
        }
        return amont.toString();
    }

}
