package com.vstock.front.service;

import com.vstock.db.dao.IUserDao;
import com.vstock.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserDao userDao;

    public int insertUser(User user){
        return userDao.insertUser(user);
    }

    public User findById(String suid){
        User u = new User();
        u.setId(suid);
        return userDao.findUser(u);
    }

    public User findUser(String mobile){
        User u = new User();
        u.setMobile(mobile);
        return userDao.findUser(u);
    }

}
