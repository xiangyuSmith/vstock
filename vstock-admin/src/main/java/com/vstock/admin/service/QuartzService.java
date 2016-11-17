package com.vstock.admin.service;

import com.vstock.db.dao.IStockxStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiangyu on 2016/7/18.
 */
@Service
public class QuartzService {

    @Autowired
    IStockxStore stockxStore;


}
