package com.vstock.db.dao;

import com.vstock.db.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangyu on 2016/11/28.
 */
public interface IUserDao {

    int insertUser(User user);

    User findUser(User user);

    int update(User user);

    List<User> findAll(@Param("obj") User user, @Param("startPos")Integer startPos, @Param("pageSize")Integer pageSize);

    int findCount(User user);
}
