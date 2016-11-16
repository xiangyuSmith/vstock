package com.vstock.admin.base.service;

import com.vstock.db.dao.IDictionariesDao;
import com.vstock.db.entity.CommodityData;
import com.vstock.db.entity.Dictionaries;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunson on 2015/11/21.
 */
@Service
public class DictionariesService {

    @Autowired
    IDictionariesDao dictionariesDao;

    @Autowired
    MongoTemplate mongoTemplate;

    //获取对象
    public Dictionaries findById(String dicId){
        return mongoTemplate.findById(dicId,Dictionaries.class);
    }

    //修改
    public int update(Dictionaries dictionaries){
        mongoTemplate.save(dictionaries);
        return 1;
    }

    public List<Dictionaries> dictionariesList(Dictionaries dictionaries,String status,String productName,String storeName,String colorStatus,Page page){
        return dictionariesDao.findDcList(dictionaries,status,page.getStartPos(),page.getPageSize());
    }

    //获取页面总数
    public List<Dictionaries> getCount(Dictionaries dictionaries,String status,String productName,String storeName,String colorStatus){
        return dictionariesDao.getCount(dictionaries,status);
    }

    //不分页查询所有记录
    public List<Dictionaries> findDcListAll(Dictionaries record){
        return dictionariesDao.findDcListAll(record);
    }

}
