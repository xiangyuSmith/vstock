package com.vstock.db.dao;

import com.vstock.db.entity.Refund;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/12/8.
 */
public interface IRefundDao {
    //分页查询所有
    List<Refund> findAll(@Param("obj") Refund record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询对象
    Refund find(@Param("obj") Refund record);

    //查询所有总数
    int findCount(@Param("obj") Refund record);

    //添加数据
    int insert(Refund record);

    //修改数据
    int update(Refund record);

    //带时间区间分页查询所有
    List<Refund> findAllDate(@Param("obj") Refund record,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //带时间区间查询总数
    int findCountDate(@Param("obj") Refund record,@Param("startTime") String startTime,@Param("endTime") String endTime);
}
