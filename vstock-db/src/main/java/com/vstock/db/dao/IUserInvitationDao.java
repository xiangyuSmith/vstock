package com.vstock.db.dao;

import com.vstock.db.entity.UserInvitation;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by administor on 2016/12/6.
 */
public interface IUserInvitationDao {

    //分页查询所有
    List<UserInvitation> findAll(@Param("obj") UserInvitation record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询所有总数
    int findCount(@Param("obj") UserInvitation record);

    //添加数据
    int insert(UserInvitation record);

    //修改数据
    int update(UserInvitation record);

}
