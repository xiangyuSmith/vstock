package com.vstock.admin.base.controller;

import com.vstock.db.dao.*;
import com.vstock.db.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by administor on 2016/11/8.
 */
@Controller
@RequestMapping("/databaseMigration")
public class DatabaseMigrationController {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    IBasicinformation iBasicinformation;
    @Autowired
    ICommodityData iCommodityData;
    @Autowired
    IDictionariesDao iDictionariesDao;
    @Autowired
    IResultData iResultData;
    @Autowired
    IResultDataFactory iResultDataFactory;
    @Autowired
    ICommodityDetail iCommodityDetail;

    @RequestMapping("basicinformation")
    public void Basicinformation(){
        List<Basicinformation> basicinformationList = mongoTemplate.findAll(Basicinformation.class);
        for (int i = 0; i < basicinformationList.size(); i++){
            System.out.println("===============================================");
            System.out.println("                                               ");
            System.out.println("              导入第" + i +"条数据             ");
            System.out.println("                                               ");
            System.out.println("===============================================");
            if (i == 2237){
                System.out.println(i);
            }
            Basicinformation basicinformation = new Basicinformation();
            basicinformation = basicinformationList.get(i);
            basicinformation.setId(null);
            if (basicinformation.getCsaledate() != null) {
                if (basicinformation.getCsaledate().contains(",")) {
                    basicinformation.setCsaledate(basicinformation.getCsaledate().replace(",", ""));
                }
            }
            basicinformation.setEofferprice(basicinformation.getEofferprice().replaceAll("\\$",""));
            if (basicinformation.getEofferprice().contains(",")){
                basicinformation.setEofferprice(basicinformation.getEofferprice().replace(",",""));
            }
            if ("".equals(basicinformation.getCsaledate())){
                basicinformation.setCsaledate(null);
            }
            iBasicinformation.insert(basicinformation);
        }
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("          导入表Basicinformation成功！         ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }

    @RequestMapping("commodityData")
    public void CommodityData(){
        List<CommodityData> commodityDetailList = mongoTemplate.findAll(CommodityData.class);
        for (int i = 0; i < commodityDetailList.size(); i++){
            System.out.println("===============================================");
            System.out.println("                                               ");
            System.out.println("              导入第" + i +"条数据             ");
            System.out.println("                                               ");
            System.out.println("===============================================");
//            if (i == 2237){
//                System.out.println(i);
//            }
            CommodityData commodityDeta = new CommodityData();
            commodityDeta = commodityDetailList.get(i);
            commodityDeta.setId(null);
            iCommodityData.insertcommodityData(commodityDeta);
        }
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("            导入表CommodityData成功！          ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }

    @RequestMapping("dictionaries")
    public void Dictionaries(){
        List<Dictionaries> dictionariesList = mongoTemplate.findAll(Dictionaries.class);
        for (int i = 0; i < dictionariesList.size(); i++){
            System.out.println("===============================================");
            System.out.println("                                               ");
            System.out.println("              导入第" + i +"条数据             ");
            System.out.println("                                               ");
            System.out.println("===============================================");
            if (i == 9714){
                System.out.println(i);
            }
            Dictionaries dictionaries = new Dictionaries();
            dictionaries = dictionariesList.get(i);
            CommodityData commodityData = dictionaries.getCommodityData();
            commodityData.setId(null);
            List<CommodityData> commodityDataList= iCommodityData.findCommodityDataAll(commodityData);
            dictionaries.setId(null);
            dictionaries.setCommodityDataId(commodityDataList.get(0).getId());
            iDictionariesDao.insertdictionaries(dictionaries);
        }
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("             导入表Dictionaries成功！          ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }

    @RequestMapping("resultdata")
    public void ResultData(){
        List<ResultData> resultDataList = mongoTemplate.findAll(ResultData.class);
        for (int i = 0; i < resultDataList.size(); i++){
            System.out.println("===============================================");
            System.out.println("                                               ");
            System.out.println("              导入第" + i +"条数据             ");
            System.out.println("                                               ");
            System.out.println("===============================================");
            if (i == 9714){
                System.out.println(i);
            }
            ResultData resultData = new ResultData();
            resultData = resultDataList.get(i);
            CommodityData commodityData = mongoTemplate.findById(resultData.getCommodityDataId(),CommodityData.class);
            commodityData.setId(null);
            List<CommodityData> commodityDataList= iCommodityData.findCommodityDataAll(commodityData);
            resultData.setId(null);
            resultData.setCommodityDataId(commodityDataList.get(0).getId());
            iResultData.insertresultData(resultData);
        }
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("              导入表ResultData成功！           ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }

    @RequestMapping("resultDataFactory")
    public void ResultDataFactory(){
        List<ResultDataFactory> resultDataFactoryList = mongoTemplate.findAll(ResultDataFactory.class);
        for (int i = 0; i < resultDataFactoryList.size(); i++){
            System.out.println("===============================================");
            System.out.println("                                               ");
            System.out.println("              导入第" + i +"条数据             ");
            System.out.println("                                               ");
            System.out.println("===============================================");
            if (i == 9714){
                System.out.println(i);
            }
            ResultDataFactory resultDataFactory = new ResultDataFactory();
            resultDataFactory = resultDataFactoryList.get(i);
            CommodityData commodityData = mongoTemplate.findById(resultDataFactory.getCommodityDataId(),CommodityData.class);
            commodityData.setId(null);
            List<CommodityData> commodityDataList= iCommodityData.findCommodityDataAll(commodityData);
            resultDataFactory.setId(null);
            resultDataFactory.setCommodityDataId(commodityDataList.get(0).getId());
            iResultDataFactory.insertresultData(resultDataFactory);
        }
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("          导入表ResultDataFactory成功！        ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }

    @RequestMapping("commodityDetail")
    public void CommodityDetail(){
        List<CommodityDetail> commodityDetailList = mongoTemplate.findAll(CommodityDetail.class);
        for (int i = 0; i < commodityDetailList.size(); i++){
            System.out.println("===============================================");
            System.out.println("                                               ");
            System.out.println("              导入第" + i +"条数据             ");
            System.out.println("                                               ");
            System.out.println("===============================================");
            if (i == 131920){
                System.out.println(i);
            }
            CommodityDetail commodityDetail = new CommodityDetail();
            commodityDetail = commodityDetailList.get(i);
            CommodityData commodityData = mongoTemplate.findById(commodityDetail.getCommodityDataId(),CommodityData.class);
            commodityData.setId(null);
            List<CommodityData> commodityDataList= iCommodityData.findCommodityDataAll(commodityData);
            commodityDetail.setId(null);
            commodityDetail.setCommodityDataId(commodityDataList.get(0).getId());
            iCommodityDetail.insertCommoditydetail(commodityDetail);
        }
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("          导入表ResultDataFactory成功！        ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }

}
