package com.vstock.admin.service;

import com.vstock.db.dao.IDictionariesDao;
import com.vstock.db.entity.Dictionaries;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by sunson on 2015/11/21.
 */
@Service
public class DictionariesService {

    @Autowired
    IDictionariesDao dictionariesDao;

    //修改
    public int update(Dictionaries dictionaries){
        return dictionariesDao.update(dictionaries);
    }

    public List<Dictionaries> dictionariesList(Dictionaries dictionaries,String status,String productName,String storeName,String colorStatus,Page page){
        return dictionariesDao.findDcList(dictionaries,status,page.getStartPos(),page.getPageSize());
    }

    //获取页面总数
    public List<Dictionaries> getCount(Dictionaries dictionaries,String status,String productName,String storeName,String colorStatus){
        return dictionariesDao.getCount(dictionaries,status);
    }

    //不分页查询所有
    public List<Dictionaries> findDcListAll(Dictionaries record){
        return dictionariesDao.findDcListAll(record);
    }

    //链表commodity_data查询所有分页记录
    public List<Dictionaries> findAll(Dictionaries record, String stockxName, String commodityName, Page page){
        return dictionariesDao.findAll(record,stockxName,commodityName,page.getStartPos(),page.getPageSize());
    }

    //链表commodity_data查询所有总数
    public int findCount(Dictionaries record, String stockxName, String commodityName){
        return dictionariesDao.findCount(record,stockxName,commodityName);
    }

    //根据ID查询
    public Dictionaries findById(String id){
        return dictionariesDao.findById(id);
    }

}
