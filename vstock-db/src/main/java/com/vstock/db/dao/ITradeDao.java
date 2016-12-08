package com.vstock.db.dao;

import com.vstock.db.entity.Trade;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
public interface ITradeDao {
    //分页查询所有
    List<Trade> findAll(@Param("obj")Trade record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询所有总数
    int findCount(@Param("obj")Trade record);

    //添加数据
    int insert(Trade record);

    //修改数据
    int update(@Param("status")String status, @Param("endDate")Date endDate, @Param("id")Integer id);
}
