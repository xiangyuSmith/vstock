package com.vstock.db.dao;

import com.vstock.db.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface IPaymentDao {

    int insert(Payment payment);

    Payment findByTrade(@Param("obj")Payment record);
}
