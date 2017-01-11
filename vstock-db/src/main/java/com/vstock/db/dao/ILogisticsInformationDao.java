package com.vstock.db.dao;

import com.vstock.db.entity.LogisticsInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ILogisticsInformationDao {

    //分页查询所有
    List<LogisticsInformation> findAll(@Param("obj") LogisticsInformation record);

    //查询所有总数
    int findCount(@Param("obj") LogisticsInformation record);

    //添加数据
    int insert(LogisticsInformation record);

    //修改数据
    int update(LogisticsInformation record);

}
