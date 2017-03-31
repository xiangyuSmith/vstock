package com.vstock.db.dao;

import com.vstock.db.entity.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPaymentDao {

    int insert(Payment payment);

    Payment findByTrade(@Param("obj")Payment record);

    List<Payment> findAll(@Param("obj")Payment record, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);
}
