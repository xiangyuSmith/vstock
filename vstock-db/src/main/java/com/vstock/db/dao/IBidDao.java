package com.vstock.db.dao;

import com.vstock.db.entity.Bid;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by administor on 2016/12/6.
 */
public interface IBidDao {

    //分页查询所有
    List<Bid> findAll(@Param("obj")Bid record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //分页查询所有关联用户
    List<Bid> findNewAll(@Param("obj")Bid record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    List<Bid> findAllBid(@Param("obj")Bid record);

    List<Bid> findAllBidOrder(@Param("obj")Bid record,@Param(value = "sort")Integer sort);

    List<Bid> findOrderByMoney(@Param("obj")Bid record);

    //查询所有总数
    int findCount(@Param("obj")Bid record);

    //前台去掉删除查询总数
    int findWebCount(@Param("obj")Bid record);

    //添加数据
    int insert(Bid record);

    //修改数据
    int update(@Param("status")String status,@Param("paymentId")Integer paymentId, @Param("bidMoney")BigDecimal bidMoney, @Param("invalidDate")String invalidDate, @Param("id")Integer id);

    //关联峰值表分页查询
    List<Bid> findAndPricePeak(@Param("obj")Bid record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //关联峰值表分页状态正序查询
    List<Bid> findOrderStatus(@Param("obj")Bid record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    Bid findByType(@Param("obj")Bid record,@Param(value = "sort") Integer sort, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //分页带时间区间查询
    List<Bid> findBidForSorts(@Param("bftSize") String bftSize,@Param("year") String year,@Param("brand") String brand,@Param("priceStart") String priceStart,@Param("priceEnd") String priceEnd);

    //分页带叫价金额区间查询
    List<Bid> findAndUser(@Param("obj")Bid record,@Param(value = "minimumMoney") BigDecimal minimumMoney,@Param(value = "maximumMoney") BigDecimal maximumMoney, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询叫价区间总数
    int findAndUserCount(@Param("obj")Bid record,@Param(value = "minimumMoney") BigDecimal minimumMoney,@Param(value = "maximumMoney") BigDecimal maximumMoney);

}
