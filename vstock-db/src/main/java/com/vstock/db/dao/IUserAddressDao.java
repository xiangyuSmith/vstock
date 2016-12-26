package com.vstock.db.dao;

import com.vstock.db.entity.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/12/6.
 */
public interface IUserAddressDao {

    //分页查询所有
    List<UserAddress> findAll(@Param("obj") UserAddress record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询所有总数
    int findCount(@Param("obj") UserAddress record);

    //添加数据
    int insert(UserAddress record);

    //修改数据
    int update(@Param("obj")UserAddress record);

    UserAddress findType(@Param("obj") UserAddress record);

}
