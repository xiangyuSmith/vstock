package com.vstock.db.dao;

import com.vstock.db.entity.Trade;
import com.vstock.db.entity.TradeYunfee;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
public interface ITradeDao {
    //分页查询所有
    List<Trade> findAll(@Param("obj")Trade record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    List<Trade> findAllSale(@Param("obj")Trade record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize,
                                @Param(value = "startDateTime") String startDateTime, @Param("endDateTime") String endDateTime);

    List<Trade> findAllTrade(@Param("obj")Trade record);

    List<Trade> findInStatus(@Param("obj")Trade record);

    //带时间区间分页查询
    List<Trade> findAllDate(@Param("obj")Trade record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize,
                            @Param(value = "startDateTime") String startDateTime, @Param("endDateTime") String endDateTime);

    //查询所有总数
    int findCount(@Param("obj")Trade record);

    int findCountDate(@Param("obj")Trade record, @Param(value = "startDateTime") String startDateTime, @Param("endDateTime") String endDateTime);

    //后台带时间区间分页查询
    List<Trade> findAdminAllDate(@Param("obj")Trade record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize,
                            @Param(value = "startDateTime") String startDateTime, @Param("endDateTime") String endDateTime);

    int findAdminCountDate(@Param("obj")Trade record, @Param(value = "startDateTime") String startDateTime, @Param("endDateTime") String endDateTime);

    //添加数据
    int insert(Trade record);

    //修改数据
    int update(@Param("obj")Trade record);

    //修改所有
    int updateAll(Trade record);

    //关联叫价表分组查询
    List<Trade> findAndBid(@Param("obj")Trade record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    List<Trade> findAllWeb(@Param("obj")Trade record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    int findCountWeb(@Param("obj")Trade record);

    List<TradeYunfee> findAllYunFee();

    //后台弹窗用查询，固定查询状态为待发货、检验失败、交易完成订单
    List<Trade> findModel(@Param("obj")Trade record);
}
