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
        List<Dictionaries> dictionariesListResult = new ArrayList<>();
        Query query = new Query();
//        query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"bid")));
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"updatetime")));
        query.skip(page.getStartPos());
        query.limit(page.getPageSize());
        if(dictionaries.getId() != null && !"".equals(dictionaries.getId())){
            Dictionaries dictionar = mongoTemplate.findById(dictionaries.getId(),Dictionaries.class);
            String commodityDataId = dictionar.getCommodityDataId();
            CommodityData commodity = mongoTemplate.findById(commodityDataId,CommodityData.class);
            dictionar.setCommodityData(commodity);
            dictionariesListResult.add(dictionar);
            return dictionariesListResult;
        }
        if(dictionaries.getBid() != 0 && !"".equals(dictionaries.getBid())){
            query.addCriteria(Criteria.where("bid").is(dictionaries.getBid()));
        }
        if(dictionaries.getCommodityDataId() != null && !"".equals(dictionaries.getCommodityDataId())){
            query.addCriteria(Criteria.where("commodityDataId").regex(dictionaries.getCommodityDataId()));
        }
        if(dictionaries.getColorly() != null && !"".equals(dictionaries.getColorly())){
            query.addCriteria(Criteria.where("colorly").regex(dictionaries.getColorly()));
        }

        if(status != null && !"-1".equals(status)){
            String nullStr = null;
            switch (status){
                case "0":
                    if(dictionaries.getIdentification() != null && !"".equals(dictionaries.getIdentification())){
                        query.addCriteria(Criteria.where("identification").regex(dictionaries.getIdentification()));
                    }else {
                        query.addCriteria(Criteria.where("identification").in(nullStr));
                    }
                    query.addCriteria(Criteria.where("status").in(nullStr));
                    break;
                case "1":
                    if(dictionaries.getIdentification() != null && !"".equals(dictionaries.getIdentification())){
                        query.addCriteria(Criteria.where("identification").regex(dictionaries.getIdentification()));
                    }else{
                        query.addCriteria(Criteria.where("identification"). ne(null));
                    }
                    query.addCriteria(Criteria.where("status").in(nullStr));
                    break;
                case "2":
                    query.addCriteria(Criteria.where("status"). is(status));
                    break;
                case "3":
                    query.addCriteria(Criteria.where("status"). is(status));
                    break;
            }
        }else{
            String nullStr = null;
            query.addCriteria(Criteria.where("status").in(nullStr));
        }

        if(dictionaries.getGirard() != null && !"".equals(dictionaries.getGirard())){
            query.addCriteria(Criteria.where("girard").regex(dictionaries.getGirard()));
        }
        if(dictionaries.getCreateTime() != null && !"".equals(dictionaries.getCreateTime())){
            query.addCriteria(Criteria.where("createTime").regex(dictionaries.getCreateTime()));
        }
        if(dictionaries.getUpdatetime() != null && !"".equals(dictionaries.getUpdatetime())){
            query.addCriteria(Criteria.where("updatetime").regex(dictionaries.getUpdatetime()));
        }
        if(dictionaries.getUpdateUser() != null && !"".equals(dictionaries.getUpdateUser())){
            query.addCriteria(Criteria.where("updateUser").regex(dictionaries.getUpdateUser()));
        }
        if(dictionaries.getReservedField() != null && !"".equals(dictionaries.getReservedField())){
            query.addCriteria(Criteria.where("reservedField").regex(dictionaries.getReservedField()));
        }
        if(dictionaries.getReservedFieldT() != null && !"".equals(dictionaries.getReservedFieldT())){
            query.addCriteria(Criteria.where("reservedFieldT").regex(dictionaries.getReservedFieldT()));
        }
        if(colorStatus != null && !"".equals(colorStatus)){
            query.addCriteria(Criteria.where("colorly").is(colorStatus));
        }
        if(productName != null && !"".equals(productName)){
            query.addCriteria(Criteria.where("commodityData.commodityName").regex(".*?" + productName + ".*", "i"));
        }
        if(storeName != null && !"".equals(storeName)){
            query.addCriteria(Criteria.where("commodityData.stockxName").regex(".*?" + storeName + ".*", "i"));
        }
        List<Dictionaries> dictionariesList = mongoTemplate.find(query,Dictionaries.class);
        return dictionariesList;
    }

    //获取页面总数
    public List<Dictionaries> getCount(Dictionaries dictionaries,String status,String productName,String storeName,String colorStatus){
        List<Dictionaries> dictionariesList = null;
        List<Dictionaries> dictionariesListResult = new ArrayList<>();
        Query query = new Query();
        if(dictionaries.getId() != null && !"".equals(dictionaries.getId())){
            Dictionaries dictionar = mongoTemplate.findById(dictionaries.getId(),Dictionaries.class);
            String commodityDataId = dictionar.getCommodityDataId();
            CommodityData commodity = mongoTemplate.findById(commodityDataId,CommodityData.class);
            dictionar.setCommodityData(commodity);
            dictionariesListResult.add(dictionar);
            return dictionariesListResult;
        }
        if(dictionaries.getBid() != 0 && !"".equals(dictionaries.getBid())){
            query.addCriteria(Criteria.where("bid").is(dictionaries.getBid()));
        }
        if(dictionaries.getCommodityDataId() != null && !"".equals(dictionaries.getCommodityDataId())){
            query.addCriteria(Criteria.where("commodityDataId").regex(dictionaries.getCommodityDataId()));
        }
        if(dictionaries.getColorly() != null && !"".equals(dictionaries.getColorly())){
            query.addCriteria(Criteria.where("colorly").regex(dictionaries.getColorly()));
        }

        if(status != null && !"-1".equals(status)){
            String nullStr = null;
            switch (status){
                case "0":
                    if(dictionaries.getIdentification() != null && !"".equals(dictionaries.getIdentification())){
                        query.addCriteria(Criteria.where("identification").regex(dictionaries.getIdentification()));
                    }else{
                        query.addCriteria(Criteria.where("identification").in(nullStr));
                    }
                    query.addCriteria(Criteria.where("status").in(nullStr));
                    break;
                case "1":
                    if(dictionaries.getIdentification() != null && !"".equals(dictionaries.getIdentification())){
                        query.addCriteria(Criteria.where("identification").regex(dictionaries.getIdentification()));
                    }else {
                        query.addCriteria(Criteria.where("identification").ne(null));
                    }
                    query.addCriteria(Criteria.where("status").in(nullStr));
                    break;
                case "2":
                    query.addCriteria(Criteria.where("status"). is(status));
                    break;
                case "3":
                    query.addCriteria(Criteria.where("status"). is(status));
                    break;
            }
        }else{
            String nullStr = null;
            query.addCriteria(Criteria.where("status").in(nullStr));
        }

        if(dictionaries.getGirard() != null && !"".equals(dictionaries.getGirard())){
            query.addCriteria(Criteria.where("girard").regex(dictionaries.getGirard()));
        }
        if(dictionaries.getCreateTime() != null && !"".equals(dictionaries.getCreateTime())){
            query.addCriteria(Criteria.where("createTime").regex(dictionaries.getCreateTime()));
        }
        if(dictionaries.getUpdatetime() != null && !"".equals(dictionaries.getUpdatetime())){
            query.addCriteria(Criteria.where("updatetime").regex(dictionaries.getUpdatetime()));
        }
        if(dictionaries.getUpdateUser() != null && !"".equals(dictionaries.getUpdateUser())){
            query.addCriteria(Criteria.where("updateUser").regex(dictionaries.getUpdateUser()));
        }
        if(dictionaries.getReservedField() != null && !"".equals(dictionaries.getReservedField())){
            query.addCriteria(Criteria.where("reservedField").regex(dictionaries.getReservedField()));
        }
        if(dictionaries.getReservedFieldT() != null && !"".equals(dictionaries.getReservedFieldT())){
            query.addCriteria(Criteria.where("reservedFieldT").regex(dictionaries.getReservedFieldT()));
        }
        if(colorStatus != null && !"".equals(colorStatus)){
            query.addCriteria(Criteria.where("colorly").is(colorStatus));
        }
        if(productName != null && !"".equals(productName)){
            query.addCriteria(Criteria.where("commodityData.commodityName").regex(".*?" + productName + ".*", "i"));
        }
        if(storeName != null && !"".equals(storeName)){
            query.addCriteria(Criteria.where("commodityData.stockxName").regex(".*?" + storeName + ".*", "i"));
        }
        dictionariesList = mongoTemplate.find(query,Dictionaries.class);
        return dictionariesList;
    }

    //不分页查询所有记录
    public List<Dictionaries> findDcListAll(Dictionaries record){
        return dictionariesDao.findDcListAll(record);
    }

}
