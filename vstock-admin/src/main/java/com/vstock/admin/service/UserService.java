package com.vstock.admin.service;

import com.vstock.db.dao.IUserDao;
import com.vstock.db.entity.User;
import com.vstock.db.entity.UserActivity;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunson on 2015/11/21.
 */
@Service
public class UserService {

    User user;

    @Autowired
    IUserDao userDao;

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

    public List<UserActivity> findActivityAll(Page page){ return userDao.findActivityAll(page.getStartPos(),page.getPageSize()); }
    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(User record){return userDao.findCount(record);}

    public int findActivityCount(){return userDao.findActivityCount();}

    //拼接查詢條件
    public String linkAddress(String linkAddress, User record){
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

}
