package com.vstock.front.service;

import com.vstock.db.dao.IRefundDao;
import com.vstock.db.entity.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("refund")
public class RefundService {

    @Autowired
    IRefundDao refundDao;

    public int insert(Refund refund){
        return refundDao.insert(refund);
    }

}
