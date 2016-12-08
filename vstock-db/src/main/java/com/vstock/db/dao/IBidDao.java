package com.vstock.db.dao;

import com.vstock.db.entity.Bid;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/6.
 */
public interface IBidDao {

    //分页查询所有
    List<Bid> findAll(@Param("obj")Bid record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询所有总数
    int findCount(@Param("obj")Bid record);

    //添加数据
    int insert(Bid record);

    //修改数据
    int update(@Param("status")String status, @Param("invalidDate")Date invalidDate, @Param("id")Integer id);

    //关联峰值表分页查询
    List<Bid> findAndPricePeak(@Param("obj")Bid record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);
}
