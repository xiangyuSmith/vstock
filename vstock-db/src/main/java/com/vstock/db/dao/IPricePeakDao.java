package com.vstock.db.dao;

import com.vstock.db.entity.PricePeak;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/8.
 */
public interface IPricePeakDao {
    //分页查询所有
    List<PricePeak> findAll(@Param("obj")PricePeak record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询所有总数
    int findCount(@Param("obj")PricePeak record);

    //添加数据
    int insert(PricePeak record);

    //修改数据
    int update(@Param("status")String status, @Param("endDate")Date endDate, @Param("id")Integer id);
}
