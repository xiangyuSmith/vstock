package com.vstock.db.dao;

import com.vstock.db.entity.User;

/**
 * Created by xiangyu on 2016/11/28.
 */
public interface IUserDao {

    int insertUser(User user);

    User findUser(User user);
}
