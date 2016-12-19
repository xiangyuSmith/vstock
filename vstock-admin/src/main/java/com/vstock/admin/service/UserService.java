package com.vstock.admin.service;

import com.vstock.db.dao.IUserDao;
import com.vstock.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
