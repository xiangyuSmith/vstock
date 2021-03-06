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

    //带时间区间分页查询所有退款记录
    List<Refund> findRefundAll(@Param("obj") Refund record,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //带时间区间查询退款记录总数
    int findRefundCount(@Param("obj") Refund record,@Param("startTime") String startTime,@Param("endTime") String endTime);

    //带时间区间分页查询所有退款记录
    List<Refund> findSellerAllDate(@Param("obj") Refund record,@Param("startTime") String startTime,
                                   @Param("endTime") String endTime,@Param("nick") String nick,
                                   @Param("mobile") String mobile,@Param("alipayAccount") String alipayAccount,
                                   @Param(value = "startPos") Integer startPos,@Param("pageSize") Integer pageSize);

    //带时间区间查询退款记录总数
    int findSellerCountDate(@Param("obj") Refund record,@Param("startTime") String startTime,
                            @Param("endTime") String endTime,@Param("nick") String nick,
                            @Param("mobile") String mobile,@Param("alipayAccount") String alipayAccount);

}
