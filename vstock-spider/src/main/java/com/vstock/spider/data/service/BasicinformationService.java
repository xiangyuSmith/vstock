package com.vstock.spider.data.service;

import com.vstock.db.dao.IBasicinformation;
import com.vstock.db.entity.Basicinformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administor on 2016/7/12.
 */
@Service("basicinformation")
public class BasicinformationService {
    @Autowired
    IBasicinformation basicinformationDao;


    //添加数据
    public int insertbasicinfrom(Basicinformation record) {
        return basicinformationDao.insert(record);
    }


    //不带分页查询
    public List<Basicinformation> findAll(Basicinformation record) {
        return basicinformationDao.findAll(record);
    }

}
