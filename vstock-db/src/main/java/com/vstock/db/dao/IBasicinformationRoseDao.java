package com.vstock.db.dao;

import com.vstock.db.entity.BasicinformationRose;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IBasicinformationRoseDao {

    List<BasicinformationRose> findRose(@Param("obj")BasicinformationRose basicinformationRose,@Param("startTime")String startTime,@Param("endTime")String endTime);

    List<BasicinformationRose> findAllDate(@Param("obj")BasicinformationRose record);

    List<BasicinformationRose> findNewRose(@Param("obj")BasicinformationRose record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    int insert(BasicinformationRose record);

    int insertLog();

    int del();

}
