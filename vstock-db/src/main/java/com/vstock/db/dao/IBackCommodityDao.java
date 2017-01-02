package com.vstock.db.dao;

import com.vstock.db.entity.BackCommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBackCommodityDao {

    //查询所有
    List<BackCommodity> findAll(BackCommodity record);

    //查询所有总数
    int findCount(BackCommodity record);

    //新增
    int insert(BackCommodity record);

    //修改
    int update(BackCommodity record);

    //链表鞋库表查询
    List<BackCommodity> findAndBtf(@Param("obj") BackCommodity record, @Param("startTime")String startTime, @Param("endTime")String endTime, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //链表鞋库查询总数
    int findAndBtfCount(@Param("obj")BackCommodity record, @Param("startTime")String startTime, @Param("endTime")String endTime);

}
