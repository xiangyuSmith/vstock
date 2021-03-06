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

    //按类型查询
    List<PricePeak> findByType(@Param("obj")PricePeak record,@Param(value = "sort") Integer sort, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize,@Param("startTime")String startTime,@Param("endTime")String endTime);

    //最低售价 & 最高叫价
    List<PricePeak> findBySellAndBuy(@Param(value = "sort") Integer sort);

    //查询所有总数
    int findCount(@Param("obj")PricePeak record);

    //添加数据
    int insert(PricePeak record);

    //修改数据
    int update(PricePeak record);

    int updateX(PricePeak record);

    int updateY(PricePeak record);

    //关联鞋库基本信息查询
    List<PricePeak> findAndBft(@Param("obj")PricePeak record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //关联鞋库基本信息总数
    int findBftCount(@Param("obj")PricePeak record);
}
