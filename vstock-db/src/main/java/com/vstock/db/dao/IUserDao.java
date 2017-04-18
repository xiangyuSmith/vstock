package com.vstock.db.dao;

import com.vstock.db.entity.User;
import com.vstock.db.entity.UserActivity;
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

    //带时间区间查询
    List<User> findAllDate(@Param("obj") User user, @Param("startPos")Integer startPos, @Param("pageSize")Integer pageSize, @Param("startCreateTime")String startCreateTime, @Param("endCreateTime")String endCreateTime);

    List<UserActivity> findActivityAll(@Param("startPos")Integer startPos, @Param("pageSize")Integer pageSize);

    int findCount(User user);

    //带时间区间查询
    int findCountDate(@Param("obj")User user, @Param("startCreateTime")String startCreateTime, @Param("endCreateTime")String endCreateTime);

    int findActivityCount();
}
