package com.vstock.spider.data.service;

import com.vstock.db.dao.ICommodityData;
import com.vstock.db.dao.ICommodityDetail;
import com.vstock.db.dao.IDictionariesDao;
import com.vstock.db.dao.IResultData;
import com.vstock.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by administor on 2016/5/12.
 */
@Service("commodityData")
public class CommodityDataService {

    @Autowired
    ICommodityData commodityDataDao;

    @Autowired
    ICommodityDetail commodityDetailDao;

    @Autowired
    IResultData resultDataDao;

    @Autowired
    IDictionariesDao dictionariesDao;

    /**
     * 商品数据的新增方法
     *
     * Date: 2016/05/12  下午：17:37:41
     * @return
     */
    public int insertCommodityData(CommodityData cmmodityData){
        return commodityDataDao.insertcommodityData(cmmodityData);
    }


    /**
     * 商品数据详情
     */
    public int insertcommodityDetail(CommodityDetail commodityDetail){
        return commodityDataDao.insertcommodityDetail(commodityDetail);
    }

    /**
     * 模糊查询单个商品
     * @return
     */
    public CommodityData findByNames(String name,String girard){
        List<CommodityData> commodityDataList = commodityDataDao.findByName(name,girard);
        if(commodityDataList.size()==0){
            return null;
        }
        return commodityDataDao.findByName(name,girard).get(0);
    }

    /**
     * 判断字典数据是否存在
     */
    public List<Dictionaries> finddictionaries(String commodityId){
        return dictionariesDao.findByCommodityId(commodityId);
    }

    /**
     * 添加字典数据
     */
    public int insertDicInfo(Dictionaries dictionarie){
        return dictionariesDao.insertdictionaries(dictionarie);
    }


    /**
     * 添加最终结果数据
     */
    public int saveResultDatas(ResultData resultData){
        return resultDataDao.insertresultData(resultData);
    }

}
