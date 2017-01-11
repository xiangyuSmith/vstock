package com.vstock.db.dao;

import com.vstock.db.entity.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ILogisticsInformationDao {

    //分页查询所有
    List<UserAddress> findAll(@Param("obj") UserAddress record);

    //查询所有总数
    int findCount(@Param("obj") UserAddress record);

    //添加数据
    int insert(UserAddress record);

    //修改数据
    int update(UserAddress record);

}
