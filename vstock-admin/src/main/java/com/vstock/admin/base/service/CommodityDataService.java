package com.vstock.admin.base.service;

import com.vstock.db.dao.IDictionariesDao;
import com.vstock.ext.util.DateUtils;
import com.vstock.db.dao.ICommodityData;
import com.vstock.db.dao.ICommodityDetail;
import com.vstock.db.dao.IResultData;
import com.vstock.db.entity.*;
import com.vstock.ext.util.JsonTool;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 商品数据的新增方法
     *
     * Date: 2016/05/12  下午：17:37:41
     * @param cmmodityData
     * @return
     */
//    public int insert(CommodityData cmmodityData){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is("cmmodityData"));
//        List<Ids> idsIsNull = mongoTemplate.find(query,Ids.class);
//        if(idsIsNull.size() == 0){
//            Ids ids = new Ids();
//            ids.setName("cmmodityData");
//            ids.setUpId(1);
//            mongoTemplate.save(ids);
//        }
//        Update update = new Update().inc("upId", 1);
//        Ids ids = mongoTemplate.findAndModify(query,update, Ids.class);
//        cmmodityData.setBid(ids.getUpId());
//        mongoTemplate.save(cmmodityData);
//        return 1;
//    }
    public int insertCommodityData(CommodityData cmmodityData){
        return commodityDataDao.insertcommodityData(cmmodityData);
    }


    /**
     * 商品数据详情
     */
//    public int insertInfo(CommodityDetail commodityDetail){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is("commodityDetail"));
//        List<Ids> idsIsNull = mongoTemplate.find(query,Ids.class);
//        if(idsIsNull.size() == 0){
//            Ids ids = new Ids();
//            ids.setName("commodityDetail");
//            ids.setUpId(1);
//            mongoTemplate.save(ids);
//        }
//        Update update = new Update().inc("upId", 1);
//        Ids ids = mongoTemplate.findAndModify(query,update, Ids.class);
//        commodityDetail.setBid(ids.getUpId());
//        mongoTemplate.save(commodityDetail);
//        return 1;
//    }

    public int insertcommodityDetail(CommodityDetail commodityDetail){
        return commodityDataDao.insertcommodityDetail(commodityDetail);
    }

    /**
     * 模糊查询单个商品
     * @return
     */
//    public CommodityData findByName(String name,String girard){
//        Query query = new Query();
//        if(name != null && !"".equals(name)){
//            query.addCriteria(Criteria.where("commodityName").is(name));
//        }
//        if(girard != null && !"".equals(girard)){
//            query.addCriteria(Criteria.where("girard").is(girard));
//        }
//        List<CommodityData> result = mongoTemplate.find(query,CommodityData.class);
//
//        if(result.size() == 0){
//            return null;
//        }
//        return result.get(0);
//    }
    public CommodityData findByNames(String name,String girard){
        List<CommodityData> commodityDataList = commodityDataDao.findByName(name,girard);
        if(commodityDataList.size()==0){
            return null;
        }
        return commodityDataDao.findByName(name,girard).get(0);
    }

    public List<CommodityData> findList(){
        List<CommodityData> list = new ArrayList<CommodityData>();
        try {
            list = commodityDataDao.findCommodityDatalist();
        }catch (Exception e){
            System.out.print(e.getMessage());
        }

        return list;
    }

    //获取页面总数
    public Long getCount(CommodityData recode,String startTime,String endTime){
        return commodityDataDao.getCommodityCount(recode,startTime,endTime);
    }

    //进行分页处理
    public List<CommodityData> queryByPage(Page page,CommodityData commodityData,String startTime,String endTime){return commodityDataDao.findAll(commodityData,page.getStartPos(),page.getPageSize(),startTime,endTime);}

    /**
     * 查询尺码数据方法
     *
     * @Date 2016：06:23  下午14:36:32
     * @param record
     * @param page
     * @return
     */
    public List<CommodityDetail> findPageAll( CommodityDetail record,Page page){
        return commodityDetailDao.findPageAll(record,page.getStartPos(),page.getPageSize());
    }

    public List<CommodityDetail> findAllComm( CommodityDetail record,String createDateStart,String createDateEnd){
        return commodityDetailDao.findAllComm(record,createDateStart,createDateEnd);
    }

    /**
     * 公共查询带条件总记录数
     *
     * @Date 2016.06.23 下午 14:48:52
     * @param record
     * @return
     */
    public Long findCount(CommodityData record){return commodityDataDao.getAllCount(record);}

    /**
     * 公共查询商品详细信息方法
     *
     * @Date 2016.06.23 上午 11:33:10
     * @param record
     * @return
     */
    public List<CommodityDetail> findCommdityDetail(CommodityDetail record){return commodityDetailDao.findCommAll(record);}

    /**
     * 返回一个集合
     *
     * @Date 2016.06.24 下午 16:10:39
     * @param record
     * @return
     */
    public List<CommodityData> findCommodityData(CommodityData record){return commodityDataDao.findCommodityDataAll(record);}

    /**
     * 查询查询详细信息表
     *
     * @Date 2016.06.24 下午 18:21:22
     * @param record
     * @return
     */
    public Long findAllCount(CommodityDetail record){return commodityDetailDao.findAllCount(record);}

    /**
     * 判断字典数据是否存在
     */
//    public List<Dictionaries> finddictionariesList(String commodityId){
//        Query query = new Query();
//        if(commodityId != null && !"".equals(commodityId)){
//            query.addCriteria(Criteria.where("commodityDataId").regex(commodityId));
//        }
//        List<Dictionaries> dictionariesList = mongoTemplate.find(query,Dictionaries.class);
//        return dictionariesList;
//    }
    public List<Dictionaries> finddictionaries(String commodityId){
        return dictionariesDao.findByCommodityId(commodityId);
    }

    /**
     * 获取工厂数据
     */
    public List<ResultDataFactory> getFactory(String productName){
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createTime")));
        query.addCriteria(Criteria.where("productName").is(productName));
        List<ResultDataFactory> resultDataFactoryList = mongoTemplate.find(query,ResultDataFactory.class);
        return resultDataFactoryList;
    }

    /**
     * 添加字典数据
     */
//    public int insertDic(Dictionaries dictionarie){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is("dictionarie"));
//        List<Ids> idsIsNull = mongoTemplate.find(query,Ids.class);
//        if(idsIsNull.size() == 0){
//            Ids ids = new Ids();
//            ids.setName("dictionarie");
//            ids.setUpId(1);
//            mongoTemplate.save(ids);
//        }
//        Update update = new Update().inc("upId", 1);
//        Ids ids = mongoTemplate.findAndModify(query,update, Ids.class);
//        dictionarie.setBid(ids.getUpId());
//        mongoTemplate.insert(dictionarie);
//        return 1;
//    }
    public int insertDicInfo(Dictionaries dictionarie){
        return dictionariesDao.insertdictionaries(dictionarie);
    }


    /**
     * 添加最终结果数据
     */
//    public int saveResultData(ResultData resultData){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is("resultData"));
//        List<Ids> idsIsNull = mongoTemplate.find(query,Ids.class);
//        if(idsIsNull.size() == 0){
//            Ids ids = new Ids();
//            ids.setName("resultData");
//            ids.setUpId(1);
//            mongoTemplate.save(ids);
//        }
//        Update update = new Update().inc("upId", 1);
//        Ids ids = mongoTemplate.findAndModify(query,update, Ids.class);
//        resultData.setBid(ids.getUpId());
//        mongoTemplate.insert(resultData);
//        return 1;
//    }
    public int saveResultDatas(ResultData resultData){
        return resultDataDao.insertresultData(resultData);
    }

    /**
     * 添加最终结果数据
     */
    public int saveResultDataFactory(ResultDataFactory resultDataFactory){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("resultDataFactory"));
        List<Ids> idsIsNull = mongoTemplate.find(query,Ids.class);
        if(idsIsNull.size() == 0){
            Ids ids = new Ids();
            ids.setName("resultDataFactory");
            ids.setUpId(1);
            mongoTemplate.save(ids);
        }
        Update update = new Update().inc("upId", 1);
        Ids ids = mongoTemplate.findAndModify(query,update, Ids.class);
        resultDataFactory.setBid(ids.getUpId());
        mongoTemplate.insert(resultDataFactory);
        return 1;
    }

    /**
     * 公共调用取json里面的值，分开保存
     *
     * @Date 2016.06.28 下午 18:39:10
     * @param CommodityDetaillist
     * @return
     * @throws Exception
     */
    public List<CommodityDetailys> takeJson(CommodityDetail CommodityDetaillist) throws Exception{
        //初始化值
        List<CommodityDetailys> commodityList = new ArrayList<CommodityDetailys>();
        //把颜色尺码的值转换为json对象
        JSONObject jsonObject = new JSONObject(CommodityDetaillist.getColorSize());
        //调用方法获取json中所有的key和value的值
        Map<String,Object> valueMap = JsonTool.jsonObtainKey(jsonObject);
        //循环取key和value(颜色和尺码对应的价格信息)
        for (String key : valueMap.keySet()){
            //根据key获取vaule
            Object obj = valueMap.get(key);
            JSONObject json = new JSONObject(obj.toString());
            Map<String, Object> value = JsonTool.jsonObtainKey(json);
            //循环取map中的key和value（尺码信息和对应的价格信息）
            for (String str : value.keySet()){
                CommodityDetailys commoditys = new CommodityDetailys();
                commoditys.setColorly(key);
                commoditys.setAveragePrice(value.get(str).toString());
                commoditys.setFootage(str);
                commodityList.add(commoditys);
            }
        }
        return commodityList;
    }

    /**
     * 获取商品所有的店铺所有尺码详细信息
     *
     * @Date 2016.06.28 下午 18:53:33
     * @param record
     * @return
     */
    public List<List> takeOtherShop(CommodityData record){

        //初始化值
        CommodityData commodityDatas = new CommodityData();
        List<CommodityData> commodityData = new ArrayList<CommodityData>();
        List<List> commodiList = new ArrayList<List>();
        List<CommodityDetail> CommodityDetaillist = new ArrayList<CommodityDetail>();
        CommodityDetail commodityDetail = new CommodityDetail();
        //获取截取后的商品名称
        commodityDatas.setCommodityName(StringUtil.takeIn(record.getCommodityName()));
        //获取商品名模糊查询的同一商品
        commodityData = this.findCommodityData(commodityDatas);
        //判断是否存在值
        if (commodityData.size() > 0) {
            //循环取值
            for (CommodityData commod : commodityData) {
                //获取截取后的商品名称
                commod.setCommodityName(StringUtil.takeIn(commod.getCommodityName()));
                //获取商品名称的对比值
                float i = StringUtil.getSimilarityRatio(commodityDatas.getCommodityName(),commod.getCommodityName());
                //判断相识度是否超过50%
                if (i > 0.5){
                    //赋值为查询
                    commodityDetail.setCommodityDataId(commod.getId());
                    if (record.getCreateDate() == null || record.getCreateDate().equals("")){
                        //查询数据
                        CommodityDetaillist = this.findCommdityDetail(commodityDetail);
                    }else {
                        String createDateStart = record.getCreateDate() +" 00:00:00";
                        String createDateEnd = record.getCreateDate() +" 23:59:59";
                        CommodityDetaillist = this.findAllComm(commodityDetail,createDateStart,createDateEnd);
                    }
                    try {
                        //判断是否有值
                        if (CommodityDetaillist.size() > 0) {
                            //获取解析的鞋码和价格
                            commodiList.add(this.takeJson(CommodityDetaillist.get(0)));
                        }
                    }catch (Exception e){
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
        return commodiList;
    }

    /**
     * 选好获取球鞋不同颜色的均价
     *
     * @Date 2016.06.29 下午 15:11:33
     * @param commodiList
     * @param commodityDetailys
     * @return
     */
    public CommodityDetailys commodityDetai(List<List> commodiList,CommodityDetailys commodityDetailys){
        //初始化变量
        CommodityDetailys commodity = new CommodityDetailys();
        DecimalFormat df = new DecimalFormat("####.00");
        List<Double> stringList = new ArrayList<Double>();
        List<CommodityDetailys> commodityList = new ArrayList<CommodityDetailys>();
        Double z = new Double(0) ;
        //获取颜色和尺码
        String str = commodityDetailys.getFootage();
        String colorly = commodityDetailys.getColorly();
        //判断是否存在值
        if (commodiList.size() > 0) {//存在
            //循环取值
            for (List<CommodityDetailys> list : commodiList) {
                //遍历取值
                for (int x = 0; x < list.size(); x++) {
                    //判断尺码和颜色是否是一样
                    if (str.equals(list.get(x).getFootage()) && colorly.equals(list.get(x).getColorly())) {
                        //保存价格
                        stringList.add(Double.parseDouble(list.get(x).getAveragePrice()));
                    }
                }
            }
        }
        //判断是否存在值
        if (stringList.size() > 0) {
            //遍历取值
            for (int y = 0; y < stringList.size(); y++) {
                //值相加
                z += stringList.get(y);
            }
            //获取平均值
            z = z / stringList.size();
            //赋值传回前台
            commodity.setColorly(colorly);
            commodity.setFootage(str);
            commodity.setAveragePrice(df.format(z).toString());
        }else {
            //赋值传回前台
            commodity.setColorly(colorly);
            commodity.setFootage(str);
            commodity.setAveragePrice(commodityDetailys.getAveragePrice());
        }
        return commodity;
    }

    /**
     * 过滤表中的数据
     */
    //获取规则处理后的所有数据
    public List<ResultData> findResultData(ResultData record,Page page,String startTime,String endTime){
        Query query = new Query();
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        if(record.getBid() == 0 && record.getCommodityDataId()==null && record.getStoreId()==null
                && record.getStoreName() ==null && record.getProductName()==null && record.getBrand()==null
                && record.getGirard()==null && record.getSizePrice()==null && record.getTransactionRecord()==null
                && startTime==null && endTime==null){
            query.skip(page.getStartPos());
            query.limit(page.getPageSize());
            resultDataList = mongoTemplate.find(query,ResultData.class);
        }else {
            if(record.getBid() != 0){
                query.addCriteria(Criteria.where("bid").is(record.getBid()));
            }
            if(record.getCommodityDataId() != null && !"".equals(record.getCommodityDataId())){
                query.addCriteria(Criteria.where("commodityDataId").regex(record.getCommodityDataId()));
            }
            if(record.getStoreId() != null && !"".equals(record.getStoreId())){
                query.addCriteria(Criteria.where("storeId").regex(record.getStoreId()));
            }
            if(record.getStoreName() != null && !"".equals(record.getStoreName())){
                query.addCriteria(Criteria.where("storeName").regex(record.getStoreName()));
            }
            if(record.getProductName() != null && !"".equals(record.getProductName())){
                query.addCriteria(Criteria.where("productName").regex(record.getProductName()));
            }
            if(record.getBrand() != null && !"".equals(record.getBrand())){
                query.addCriteria(Criteria.where("brand").regex(record.getBrand()));
            }
            if(record.getGirard() != null && !"".equals(record.getGirard())){
                query.addCriteria(Criteria.where("girard").regex(record.getGirard()));
            }
            if(record.getSizePrice() != null && !"".equals(record.getSizePrice())){
                query.addCriteria(Criteria.where("sizePrice").regex(record.getSizePrice()));
            }
            if(record.getTransactionRecord() != null && !"".equals(record.getTransactionRecord())){
                query.addCriteria(Criteria.where("transactionRecord").regex(record.getTransactionRecord()));
            }
            if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").gte(startTime).lte(endTime));
            }else if(startTime != null && !"".equals(startTime)){
                query.addCriteria(Criteria.where("createTime").gt(startTime));
            }else if(endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").lt(endTime));
            }
            query.skip(page.getStartPos());
            query.limit(page.getPageSize());
            resultDataList = mongoTemplate.find(query,ResultData.class);
        }
        List<ResultData> list1 = new ArrayList<ResultData>();
        Set<String> set = new HashSet<String>();
        for (ResultData resileData : resultDataList) {
            if(resileData == null){
                continue;
            }
            String productName = resileData.getProductName();
            if(productName != null){
                if(!set.contains(productName)){
                    set.add(productName);
                    list1.add(resileData);
                }else{
                    continue;
                }
            }
        }
        return list1;
    }

    /**
     * 过滤表中的数据
     */
    //获取规则处理后的所有数据
    public List<ResultDataFactory> findResultDataFactory(ResultDataFactory resultDataFactory,Page page,String startTime,String endTime){
        Query query = new Query();
        List<ResultDataFactory> resultDataList = new ArrayList<ResultDataFactory>();
        if(resultDataFactory.getBid() == 0 && resultDataFactory.getCommodityDataId()==null
                && resultDataFactory.getProductName()==null
                && resultDataFactory.getBrand()==null
                && resultDataFactory.getGirard()==null
                && resultDataFactory.getSizePrice()==null
                && resultDataFactory.getTransactionRecord()==null
                && startTime==null && endTime==null){
            query.skip(page.getStartPos());
            query.limit(page.getPageSize());
            resultDataList = mongoTemplate.find(query,ResultDataFactory.class);
        }else {
            if(resultDataFactory.getBid() != 0){
                query.addCriteria(Criteria.where("bid").is(resultDataFactory.getBid()));
            }
            if(resultDataFactory.getCommodityDataId() != null && !"".equals(resultDataFactory.getCommodityDataId())){
                query.addCriteria(Criteria.where("commodityDataId").regex(resultDataFactory.getCommodityDataId()));
            }
            if(resultDataFactory.getProductName() != null && !"".equals(resultDataFactory.getProductName())){
                query.addCriteria(Criteria.where("productName").regex(".*?" + resultDataFactory.getProductName() + ".*", "i"));
            }
            if(resultDataFactory.getBrand() != null && !"".equals(resultDataFactory.getBrand())){
                query.addCriteria(Criteria.where("brand").regex(resultDataFactory.getBrand()));
            }
            if(resultDataFactory.getGirard() != null && !"".equals(resultDataFactory.getGirard())){
                query.addCriteria(Criteria.where("girard").regex(resultDataFactory.getGirard()));
            }
            if(resultDataFactory.getSizePrice() != null && !"".equals(resultDataFactory.getSizePrice())){
                query.addCriteria(Criteria.where("sizePrice").regex(resultDataFactory.getSizePrice()));
            }
            if(resultDataFactory.getTransactionRecord() != null && !"".equals(resultDataFactory.getTransactionRecord())){
                query.addCriteria(Criteria.where("transactionRecord").regex(resultDataFactory.getTransactionRecord()));
            }
            if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").gte(startTime).lte(endTime));
            }else if(startTime != null && !"".equals(startTime)){
                query.addCriteria(Criteria.where("createTime").gt(startTime));
            }else if(endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").lt(endTime));
            }
            query.skip(page.getStartPos());
            query.limit(page.getPageSize());
            resultDataList = mongoTemplate.find(query,ResultDataFactory.class);
        }
//        List<ResultDataFactory> list1 = new ArrayList<ResultDataFactory>();
//        Set<String> set = new HashSet<String>();
//        for (ResultDataFactory resileDataFactory : resultDataList) {
//            if(resileDataFactory == null){
//                continue;
//            }
//            String productName = resileDataFactory.getProductName();
//            if(productName != null){
//                if(!set.contains(productName)){
//                    set.add(productName);
//                    list1.add(resileDataFactory);
//                }else{
//                    continue;
//                }
//            }
//        }
        return resultDataList;
    }

    //获取所有数据（不分页）
    public List<ResultData> findResultDataAll(ResultData record){
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"bid")));
        if(record.getId() == null && record.getCommodityDataId()==null && record.getStoreId()==null
                && record.getStoreName() ==null && record.getProductName()==null && record.getBrand()==null
                && record.getGirard()==null && record.getSizePrice()==null && record.getTransactionRecord()==null
                && record.getCreateTime()==null){
            resultDataList = mongoTemplate.findAll(ResultData.class);
        }else {
            if(record.getId() != null && !"".equals(record.getId())){
                ResultData resultData = new ResultData();
                resultData = mongoTemplate.findById((Object) record.getId(), ResultData.class);
                resultDataList.add(resultData);
                return resultDataList;
            }
            if(record.getCommodityDataId() != null && !"".equals(record.getCommodityDataId())){
                query.addCriteria(Criteria.where("commodityDataId").regex(record.getCommodityDataId()));
            }
            if(record.getStoreId() != null && !"".equals(record.getStoreId())){
                query.addCriteria(Criteria.where("storeId").regex(record.getStoreId()));
            }
            if(record.getStoreName() != null && !"".equals(record.getStoreName())){
                query.addCriteria(Criteria.where("storeName").is(record.getStoreName()));
            }
            if(record.getProductName() != null && !"".equals(record.getProductName())){
                query.addCriteria(Criteria.where("productName").is(record.getProductName()));
            }
            if(record.getBrand() != null && !"".equals(record.getBrand())){
                query.addCriteria(Criteria.where("brand").regex(record.getBrand()));
            }
            if(record.getGirard() != null && !"".equals(record.getGirard())){
                query.addCriteria(Criteria.where("girard").regex(record.getGirard()));
            }
            if(record.getSizePrice() != null && !"".equals(record.getSizePrice())){
                query.addCriteria(Criteria.where("sizePrice").regex(record.getSizePrice()));
            }
            if(record.getTransactionRecord() != null && !"".equals(record.getTransactionRecord())){
                query.addCriteria(Criteria.where("transactionRecord").regex(record.getTransactionRecord()));
            }
            if(record.getCreateTime() != null && !"".equals(record.getCreateTime())){
                query.addCriteria(Criteria.where("createTime").regex(record.getCreateTime()));
            }
            resultDataList = mongoTemplate.find(query,ResultData.class);
        }
        return resultDataList;
    }

    public Long findResultDataCount(ResultData record,String startTime,String endTime){
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        if(record.getBid() == 0 && record.getCommodityDataId()==null && record.getStoreId()==null
                && record.getStoreName() ==null && record.getProductName()==null && record.getBrand()==null
                && record.getGirard()==null && record.getSizePrice()==null && record.getTransactionRecord()==null
                && startTime==null && endTime==null){
            resultDataList = mongoTemplate.findAll(ResultData.class);
        }else {
            Query query = new Query();
            if(record.getBid() != 0){
                query.addCriteria(Criteria.where("bid").is(record.getBid()));
            }
            if(record.getCommodityDataId() != null && !"".equals(record.getCommodityDataId())){
                query.addCriteria(Criteria.where("commodityDataId").regex(record.getCommodityDataId()));
            }
            if(record.getStoreId() != null && !"".equals(record.getStoreId())){
                query.addCriteria(Criteria.where("storeId").regex(record.getStoreId()));
            }
            if(record.getStoreName() != null && !"".equals(record.getStoreName())){
                query.addCriteria(Criteria.where("storeName").regex(record.getStoreName()));
            }
            if(record.getProductName() != null && !"".equals(record.getProductName())){
                query.addCriteria(Criteria.where("productName").regex(record.getProductName()));
            }
            if(record.getBrand() != null && !"".equals(record.getBrand())){
                query.addCriteria(Criteria.where("brand").regex(record.getBrand()));
            }
            if(record.getGirard() != null && !"".equals(record.getGirard())){
                query.addCriteria(Criteria.where("girard").regex(record.getGirard()));
            }
            if(record.getSizePrice() != null && !"".equals(record.getSizePrice())){
                query.addCriteria(Criteria.where("sizePrice").regex(record.getSizePrice()));
            }
            if(record.getTransactionRecord() != null && !"".equals(record.getTransactionRecord())){
                query.addCriteria(Criteria.where("transactionRecord").regex(record.getTransactionRecord()));
            }
            if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").gte(startTime).lte(endTime));
            }else if(startTime != null && !"".equals(startTime)){
                query.addCriteria(Criteria.where("createTime").gt(startTime));
            }else if(endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").lt(endTime));
            }
            resultDataList = mongoTemplate.find(query,ResultData.class);
        }
        List<ResultData> list1 = new ArrayList<ResultData>();
        Set<String> set = new HashSet<String>();
        for (ResultData resileData : resultDataList) {
            if(resileData == null){
                continue;
            }
            String productName = resileData.getProductName();
            if(productName != null){
                if(!set.contains(productName)){
                    set.add(productName);
                    list1.add(resileData);
                }else{
                    continue;
                }
            }
        }
        return Long.valueOf(list1.size());
    }

    public Long findResultDataFactoryCount(ResultDataFactory resultDataFactory,String startTime,String endTime){
        List<ResultDataFactory> resultDataFactoryListList = new ArrayList<ResultDataFactory>();
        if(resultDataFactory.getBid() == 0 && resultDataFactory.getCommodityDataId()==null
                && resultDataFactory.getProductName()==null && resultDataFactory.getBrand()==null
                && resultDataFactory.getGirard()==null && resultDataFactory.getSizePrice()==null && resultDataFactory.getTransactionRecord()==null
                && startTime==null && endTime==null){
            resultDataFactoryListList = mongoTemplate.findAll(ResultDataFactory.class);
        }else {
            Query query = new Query();
            if(resultDataFactory.getBid() != 0){
                query.addCriteria(Criteria.where("bid").is(resultDataFactory.getBid()));
            }
            if(resultDataFactory.getCommodityDataId() != null && !"".equals(resultDataFactory.getCommodityDataId())){
                query.addCriteria(Criteria.where("commodityDataId").regex(resultDataFactory.getCommodityDataId()));
            }
            if(resultDataFactory.getProductName() != null && !"".equals(resultDataFactory.getProductName())){
                query.addCriteria(Criteria.where("productName").regex(".*?" + resultDataFactory.getProductName() + ".*", "i"));
            }
            if(resultDataFactory.getBrand() != null && !"".equals(resultDataFactory.getBrand())){
                query.addCriteria(Criteria.where("brand").regex(resultDataFactory.getBrand()));
            }
            if(resultDataFactory.getGirard() != null && !"".equals(resultDataFactory.getGirard())){
                query.addCriteria(Criteria.where("girard").regex(resultDataFactory.getGirard()));
            }
            if(resultDataFactory.getSizePrice() != null && !"".equals(resultDataFactory.getSizePrice())){
                query.addCriteria(Criteria.where("sizePrice").regex(resultDataFactory.getSizePrice()));
            }
            if(resultDataFactory.getTransactionRecord() != null && !"".equals(resultDataFactory.getTransactionRecord())){
                query.addCriteria(Criteria.where("transactionRecord").regex(resultDataFactory.getTransactionRecord()));
            }
            if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").gte(startTime).lte(endTime));
            }else if(startTime != null && !"".equals(startTime)){
                query.addCriteria(Criteria.where("createTime").gt(startTime));
            }else if(endTime != null && !"".equals(endTime)){
                query.addCriteria(Criteria.where("createTime").lt(endTime));
            }
            resultDataFactoryListList = mongoTemplate.find(query,ResultDataFactory.class);
        }
//        List<ResultDataFactory> list1 = new ArrayList<ResultDataFactory>();
//        Set<String> set = new HashSet<String>();
//        for (ResultDataFactory resileData : resultDataFactoryListList) {
//            if(resileData == null){
//                continue;
//            }
//            String productName = resileData.getProductName();
//            if(productName != null){
//                if(!set.contains(productName)){
//                    set.add(productName);
//                    list1.add(resileData);
//                }else{
//                    continue;
//                }
//            }
//        }
        return Long.valueOf(resultDataFactoryListList.size());
    }

    //带时间区间查询
    public List<ResultData> findResultDataTime(ResultData record,String startTime,String endtime){
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        if(record.getId() == null && record.getCommodityDataId()==null && record.getStoreId()==null
                && record.getStoreName() ==null && record.getProductName()==null && record.getBrand()==null
                && record.getGirard()==null && record.getSizePrice()==null && record.getTransactionRecord()==null
                && startTime==null && endtime==null){
            resultDataList = mongoTemplate.findAll(ResultData.class);
        }else {
            Query query = new Query();
            if(record.getId() != null && !"".equals(record.getId())){
                ResultData resultData = mongoTemplate.findById(record.getId(),ResultData.class);
                resultDataList.add(resultData);
                return resultDataList;
            }
            if(record.getCommodityDataId() != null && !"".equals(record.getCommodityDataId())){
                query.addCriteria(Criteria.where("commodityDataId").regex(record.getCommodityDataId()));
            }
            if(record.getStoreId() != null && !"".equals(record.getStoreId())){
                query.addCriteria(Criteria.where("storeId").regex(record.getStoreId()));
            }
            if(record.getStoreName() != null && !"".equals(record.getStoreName())){
                query.addCriteria(Criteria.where("storeName").regex(record.getStoreName()));
            }
            if(record.getProductName() != null && !"".equals(record.getProductName())){
                query.addCriteria(Criteria.where("productName").is(record.getProductName()));
            }
            if(record.getBrand() != null && !"".equals(record.getBrand())){
                query.addCriteria(Criteria.where("brand").regex(record.getBrand()));
            }
            if(record.getGirard() != null && !"".equals(record.getGirard())){
                query.addCriteria(Criteria.where("girard").is(record.getGirard()));
            }
            if(record.getSizePrice() != null && !"".equals(record.getSizePrice())){
                query.addCriteria(Criteria.where("sizePrice").regex(record.getSizePrice()));
            }
            if(record.getTransactionRecord() != null && !"".equals(record.getTransactionRecord())){
                query.addCriteria(Criteria.where("transactionRecord").regex(record.getTransactionRecord()));
            }
            if (startTime != null && !"".equals(startTime) && endtime != null && !"".equals(endtime)){
                query.addCriteria(Criteria.where("createTime").gte(startTime).lte(endtime));
            }else if(startTime != null && !"".equals(startTime)){
                query.addCriteria(Criteria.where("createTime").gt(startTime));
            }else if(endtime != null && !"".equals(endtime)){
                query.addCriteria(Criteria.where("createTime").lt(endtime));
            }
            resultDataList = mongoTemplate.find(query,ResultData.class);
        }
        return resultDataList;
    }

    //根据商品名称查询
    public List<ResultData> findByProductName(String productName,String storeName){
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"bid")));
//        query.skip(page.getStartPos());
//        query.limit(page.getPageSize());
        if(productName != null && !"".equals(productName)){
            query.addCriteria(Criteria.where("productName").is(productName));
        }
        if(storeName != null && !"".equals(storeName)){
            query.addCriteria(Criteria.where("storeName").is(storeName));
        }
        resultDataList = mongoTemplate.find(query,ResultData.class);
        return resultDataList;
    }

    //获取分类总数
    public Long getStoreResultDataCount(String productName,String storeId){
        Query query = new Query();

        if(productName != null && !"".equals(productName)){
            query.addCriteria(Criteria.where("productName").is(productName));
        }
        if(storeId != null && !"".equals(storeId)){
            query.addCriteria(Criteria.where("storeId").is(storeId));
        }
        List<ResultData> resultDataList = mongoTemplate.find(query,ResultData.class);
        return Long.valueOf(resultDataList.size());
    }

    //根据商品名称修改
    public int updateResultData(String productName,String productNameNew){return resultDataDao.update(productName,productNameNew);}

    //获取尺码信息
    public StringBuffer shoeCodeInformation(List<CommodityDetailys> list){
        //默认显示的尺码信息
        String[] str = new String[]{"35","35.5","36","36.5","37","37.5","38","38.5","39","39.5","40","40.5","41","41.5",
                "42","42.5","43","43.5","44","44.5","45","45.5","46","46.5","47","47.5","48","48.5"};
        //初始化变量
        StringBuffer divTr = new StringBuffer();
        //根据要显示的尺码循环遍历
        for (String string : str){
            //获取数据库中集合的值
            int x = 0;
            List<CommodityDetailys> commodityDetailys = new ArrayList<CommodityDetailys>();
            CommodityDetailys commodity = new CommodityDetailys();
            if (list.size() > 0) {
                for (CommodityDetailys comm : list) {
                    //判断是否包含当前尺码
                    if (comm.getFootage().contains(string)) {//包含
                        x++;
                        commodity = comm;
                        commodityDetailys.add(comm);
                    }
                }
            }
            if (x >= 1){
                divTr.append(this.verification(string,commodityDetailys));
            }
            else {
                divTr.append("<tr>\n" +
                        " <td><input class=\"lineGraph\" type=\"checkbox\" disabled=\"disabled\"/></td>\n" +
                        " <td>"+string+"</td>\n" +
                        " <td>"+"—"+"</td>\n" +
                        " <td><a href=\"javascript:void(0)\">详情</a></td>\n" +
                        " </tr>");
            }
        }
        return divTr;
    }

    //验证出现同时都包含的情况
    public StringBuffer verification(String string,List<CommodityDetailys> list){
        //初始化变量
        StringBuffer divTr = new StringBuffer();
        String nowDate = DateUtils.getCurrentTimeAsString();
        nowDate = nowDate.substring(0,10) + " 00:00:00";
        for (CommodityDetailys comm : list) {
            String createDate = DateUtils.compareDateDown(nowDate,comm.getCreateDate());
            //获取字符所在的位置
            int a = comm.getFootage().indexOf(string);
            //判断字符的长度是否超过下标的位置
            if (comm.getFootage().length() > a+2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = comm.getFootage().substring(a+2, a + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = comm.getFootage().substring(a + 3, a + 4);
                    if (!"5".equals(shoeCode)) {
                        if (nowDate.equals(createDate)) {
                            return divTr.append("<tr>\n" +
                                    " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                    " <td>" + string + "</td>\n" +
                                    " <td>" + comm.getAveragePrice() + "</td>\n" +
                                    " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                    " </tr>");
                        }else {
                            return divTr.append("<tr>\n" +
                                    " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                    " <td>"+string+"</td>\n" +
                                    " <td>"+comm.getAveragePrice()+"</td>\n" +
                                    " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                    " </tr>");
                        }
                    }else if ("5".equals(shoeCode)){
                        if (a >= 1) {
                            if (comm.getFootage().substring(a - 1, a + 4).trim().equals(string)) {
                                if (nowDate.equals(createDate)) {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>" + string + "</td>\n" +
                                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                                            " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                }else {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>"+string+"</td>\n" +
                                            " <td>"+comm.getAveragePrice()+"</td>\n" +
                                            " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                }
                            }else {
                                return divTr.append("<tr>\n" +
                                        " <td><input class=\"lineGraph\" type=\"checkbox\" disabled=\"disabled\"/></td>\n" +
                                        " <td>"+string+"</td>\n" +
                                        " <td>"+"—"+"</td>\n" +
                                        " <td><a href=\"javascript:void(0)\">详情</a></td>\n" +
                                        " </tr>");
                            }
                        }else {
                            if (comm.getFootage().substring(a, a + 4).trim().equals(string)) {
                                if (nowDate.equals(createDate)) {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>" + string + "</td>\n" +
                                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                                            " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                }else {
                                    return divTr.append("<tr>\n" +
                                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                            " <td>"+string+"</td>\n" +
                                            " <td>"+comm.getAveragePrice()+"</td>\n" +
                                            " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                            " </tr>");
                                }
                            }else {
                                return divTr.append("<tr>\n" +
                                        " <td><input class=\"lineGraph\" type=\"checkbox\" disabled=\"disabled\"/></td>\n" +
                                        " <td>"+string+"</td>\n" +
                                        " <td>"+"—"+"</td>\n" +
                                        " <td><a href=\"javascript:void(0)\">详情</a></td>\n" +
                                        " </tr>");
                            }
                        }
                    }
                } else {//不是
                    if (nowDate.equals(createDate)) {
                        return divTr.append("<tr>\n" +
                                " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                " <td>" + string + "</td>\n" +
                                " <td>" + comm.getAveragePrice() + "</td>\n" +
                                " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                " </tr>");
                    }else {
                        return divTr.append("<tr>\n" +
                                " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                                " <td>" + string + "</td>\n" +
                                " <td>" + comm.getAveragePrice() + "</td>\n" +
                                " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                                " </tr>");
                    }
                }
            } else {
                if (nowDate.equals(createDate)) {
                    return divTr.append("<tr>\n" +
                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                            " <td>" + string + "</td>\n" +
                            " <td>" + comm.getAveragePrice() + "</td>\n" +
                            " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                            " </tr>");
                }else {
                    return divTr.append("<tr>\n" +
                            " <td><input class=\"lineGraph\" type=\"checkbox\"/></td>\n" +
                            " <td>"+string+"</td>\n" +
                            " <td>"+comm.getAveragePrice()+"</td>\n" +
                            " <td><a href=\"/stockx/dataCore/storeDataAnalysis?shopName=" + comm.getShopName() + "&footage=" + string + "\">详情</a></td>\n" +
                            " </tr>");
                }
            }
        }
        return divTr;
    }

    public String[] judgeprice(List<String[]> sizePriceList, CommodityDetailys commodityDetailys){
        String[] str = new String[2];
        int i = 0;
        Double price = Double.parseDouble("0");
        for (String[] sizePrice : sizePriceList) {
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(commodityDetailys.getFootage());
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        if (sizePrice.length > 1) {
                            price = this.price(sizePrice[1],price);
                            i++;
                        }
                    }else if ("5".equals(shoeCode)){
                        if (sizePrice[0].substring(0, index + 4).trim().equals(commodityDetailys.getFootage())) {
                            if (sizePrice.length > 1) {
                                price = this.price(sizePrice[1],price);
                                i++;
                            }
                        }
                    }
                } else {//不是
                    if (sizePrice.length > 1) {
                        price = this.price(sizePrice[1],price);
                        i++;
                    }
                }
            } else {
                if (sizePrice.length > 1) {
                    price = this.price(sizePrice[1],price);
                    i++;
                }
            }
        }
        str[0] = price.toString();
        str[1] = Integer.toString(i);
        return str;
    }

    //获取价格相加
    public Double price(String str,Double price){
        if (str.contains("-")){
            price = price + Double.parseDouble("0");
        }else {
            price = price + Double.parseDouble(str);
        }
        return price;
    }

    //验证店铺包含鞋码信息
    public List<ResultData> shopName(List<String[]> sizePriceList,ResultData res,String footage){
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        List<ResultData> resultDa = new ArrayList<ResultData>();
        String sizePrce = res.getSizePrice();
        String id = res.getId();
        int bid = res.getBid();
        String commodityDataId = res.getCommodityDataId();
        String storeId = res.getStoreId();
        String storeName = res.getStoreName();
        String productName = res.getProductName();
        String brand = res.getBrand();
        String girard = res.getGirard();
        String sizePrices = res.getSizePrice();
        String transactionRecord = res.getTransactionRecord();
        String createTime = res.getCreateTime();
        String reservedField = res.getReservedField();

        for (String[] sizePrice : sizePriceList) {
            ResultData resultDatas = new ResultData();
            resultDatas.setId(id);
            resultDatas.setBid(bid);
            resultDatas.setCommodityDataId(commodityDataId);
            resultDatas.setStoreId(storeId);
            resultDatas.setStoreName(storeName);
            resultDatas.setProductName(productName);
            resultDatas.setBrand(brand);
            resultDatas.setGirard(girard);
            resultDatas.setSizePrice(sizePrices);
            resultDatas.setTransactionRecord(transactionRecord);
            resultDatas.setCreateTime(createTime);
            resultDatas.setReservedField(reservedField);
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(footage);
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        res.setSizePrice(sizePrice[0]);
                        if (sizePrice.length > 1) {
                            resultDatas.setReservedField(sizePrice[1]);
                        } else {
                            resultDatas.setReservedField("0");
                        }
                        resultDataList.add(resultDatas);
                    }else if ("5".equals(shoeCode)){
                        if (index >= 1){
                            if (sizePrice[0].substring(index - 1, index + 4).trim().equals(footage)){
                                resultDatas.setSizePrice(sizePrice[0]);
                                if (sizePrice.length > 1) {
                                    resultDatas.setReservedField(sizePrice[1]);
                                } else {
                                    resultDatas.setReservedField("0");
                                }
                                resultDataList.add(resultDatas);
                            }
                        }else {
                            if (sizePrice[0].substring(index, index + 4).trim().equals(footage)){
                                resultDatas.setSizePrice(sizePrice[0]);
                                if (sizePrice.length > 1) {
                                    resultDatas.setReservedField(sizePrice[1]);
                                } else {
                                    resultDatas.setReservedField("0");
                                }
                                resultDataList.add(resultDatas);
                            }
                        }
                    } else {//不是
                        resultDatas.setSizePrice(sizePrice[0]);
                        if (sizePrice.length > 1) {
                            resultDatas.setReservedField(sizePrice[1]);
                        } else {
                            resultDatas.setReservedField("0");
                        }
                        resultDataList.add(resultDatas);
                    }
                } else {
                    resultDatas.setSizePrice(sizePrice[0]);
                    if (sizePrice.length > 1) {
                        resultDatas.setReservedField(sizePrice[1]);
                    } else {
                        resultDatas.setReservedField("0");
                    }
                    resultDataList.add(resultDatas);
                }
            }else {
                resultDatas.setSizePrice(sizePrice[0]);
                if (sizePrice.length > 1) {
                    resultDatas.setReservedField(sizePrice[1]);
                } else {
                    resultDatas.setReservedField("0");
                }
                resultDataList.add(resultDatas);
            }
        }
        for (ResultData resultData : resultDataList){
            if (resultData.getSizePrice().equals(sizePrce)) {
                ResultData result = new ResultData();
                resultDa.add(result);
            }else {
                resultDa.add(resultData);
            }
        }
        return resultDa;
    }

    //验证店铺包含鞋码信息
    public ResultData individualPrice(List<String[]> sizePriceList,ResultData reData,String footage){
        for (String[] sizePrice : sizePriceList) {
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(footage);
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        reData.setSizePrice(sizePrice[0]);
                        if(sizePrice.length > 1) {
                            reData.setReservedField(sizePrice[1]);
                        }else {
                            reData.setReservedField("0");
                        }
                    }else if ("5".equals(shoeCode)){
                        if (index >= 1){
                            if (sizePrice[0].substring(index - 1, index + 4).trim().equals(footage)){
                                reData.setSizePrice(sizePrice[0]);
                                if(sizePrice.length > 1) {
                                    reData.setReservedField(sizePrice[1]);
                                }else {
                                    reData.setReservedField("0");
                                }
                            }
                        }else {
                            if (sizePrice[0].substring(index, index + 4).trim().equals(footage)){
                                reData.setSizePrice(sizePrice[0]);
                                if(sizePrice.length > 1) {
                                    reData.setReservedField(sizePrice[1]);
                                }else {
                                    reData.setReservedField("0");
                                }
                            }
                        }
                    }
                } else {//不是
                    reData.setSizePrice(sizePrice[0]);
                    if(sizePrice.length > 1) {
                        reData.setReservedField(sizePrice[1]);
                    }else {
                        reData.setReservedField("0");
                    }
                }
            } else {
                reData.setSizePrice(sizePrice[0]);
                if(sizePrice.length > 1) {
                    reData.setReservedField(sizePrice[1]);
                }else {
                    reData.setReservedField("0");
                }

            }
        }
        return  reData;
    }

    public String shopSize(List<String[]> sizePriceList, String shoeSize){
        int i = 0;
        Double parseDouble = Double.parseDouble("0");
        for (String[] sizePrice : sizePriceList) {
            //获取字符所在的位置
            int index = sizePrice[0].indexOf(shoeSize);
            //判断字符的长度是否超过下标的位置
            if (sizePrice[0].length() > index + 2) {//超过长度
                //获取字符后面第一个的值
                String shoeCode = sizePrice[0].substring(index + 2, index + 3);
                //判断后面一个值是否是.
                if (shoeCode.equals(".")) {//是
                    //获取后面两位的值
                    shoeCode = sizePrice[0].substring(index + 3, index + 4);
                    if (!"5".equals(shoeCode)) {
                        if (sizePrice.length > 1) {
                            i++;
                            parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                        } else {
                            i++;
                            parseDouble = parseDouble + Double.parseDouble("0");
                        }
                    }else if ("5".equals(shoeCode)){
                        if (index >= 1) {
                            if (sizePrice[0].substring(index - 1, index + 4).trim().equals(shoeSize)) {
                                i++;
                                parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                            }
                        }else {
                            if (sizePrice[0].substring(index, index + 4).trim().equals(shoeSize)) {
                                i++;
                                parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                            }
                        }
                    }
                } else {//不是
                    if (sizePrice.length > 1) {
                        i++;
                        parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                    } else {
                        i++;
                        parseDouble = parseDouble + Double.parseDouble("0");
                    }
                }
            } else {
                if (sizePrice.length > 1) {
                    i++;
                    parseDouble = parseDouble + Double.parseDouble(sizePrice[1]);
                } else {
                    i++;
                    parseDouble = parseDouble + Double.parseDouble("0");
                }
            }
        }
        String str = parseDouble.toString() + "=" + i;
        return str;
    }

    public List<CommodityDetailys> shoesCodeInformation(ResultData record){
        //初始化变量
        List<ResultData> resultDatalist = new ArrayList<ResultData>();
        List<CommodityDetailys> commodityDetailysList = new ArrayList<CommodityDetailys>();
        DecimalFormat df = new DecimalFormat("#.00");
        String[] shoeSize = new String[]{"35","35.5","36","36.5","37","37.5","38","38.5","39","39.5","40","40.5","41","41.5",
                "42","42.5","43","43.5","44","44.5","45","45.5","46","46.5","47","47.5","48","48.5"};

        //循环获取固定的鞋码
        for (String shoeSizes : shoeSize) {
            record.setSizePrice(shoeSizes);
            //查询数据
            resultDatalist = this.findResultDataAll(record);
            Double price = Double.parseDouble("0");
            int z = 0;
            if (resultDatalist.size() > 0) {
                String nowDate = "";
                for (int y = 0; y < resultDatalist.size(); y++) {
                    ResultData resultData = resultDatalist.get(resultDatalist.size() - (y + 1));
                    if (!"".equals(nowDate)) {
                        String createDate = DateUtils.compareDateDown(resultData.getCreateTime(),nowDate);
                        if (createDate.equals(nowDate)) {
                            //去掉前后{}
                            String averagePrices = resultData.getSizePrice().substring(1, resultData.getSizePrice().length() - 1);
                            //根据逗号分隔获取鞋码对应的价格
                            String[] strarr = averagePrices.split(",");
                            List<String[]> list = new ArrayList<String[]>();
                            String[] sizePrices = new String[]{};
                            int i = 0;
                            //循环获取鞋码和价格
                            for (int x = 0; x < strarr.length; x++) {
                                //根据等于分隔分别获取鞋码和价格
                                String[] sizePrice = strarr[x].split("=");
                                sizePrice = this.taobaoShoeCodeRule(sizePrice, shoeSizes);
                                //判断是否包含鞋码
                                if (sizePrice[0].contains(shoeSizes)) {
                                    i++;
                                    sizePrices = sizePrice;
                                    list.add(sizePrices);
                                    record.setGirard(resultData.getGirard());
                                }
                            }
                            if (i >= 1) {
                                String str = this.shopSize(list, shoeSizes);
                                String[] strings = str.split("=");
                                if (Double.parseDouble(strings[0]) > 0) {
                                    z = z + Integer.parseInt(strings[1]);
                                    price = price + Double.parseDouble(strings[0]);
                                }
                            } else {
                                price = price + Double.parseDouble("0");
                            }
                        }
                    }else {
                        //去掉前后{}
                        String averagePrices = resultData.getSizePrice().substring(1, resultData.getSizePrice().length() - 1);
                        //根据逗号分隔获取鞋码对应的价格
                        String[] strarr = averagePrices.split(",");
                        List<String[]> list = new ArrayList<String[]>();
                        String[] sizePrices = new String[]{};
                        int i = 0;
                        //循环获取鞋码和价格
                        for (int x = 0; x < strarr.length; x++) {
                            //根据等于分隔分别获取鞋码和价格
                            String[] sizePrice = strarr[x].split("=");
                            sizePrice = this.taobaoShoeCodeRule(sizePrice, shoeSizes);
                            //判断是否包含鞋码
                            if (sizePrice[0].contains(shoeSizes)) {
                                i++;
                                sizePrices = sizePrice;
                                list.add(sizePrices);
                                record.setGirard(resultData.getGirard());
                            }
                        }
                        if (i >= 1) {
                            String str = this.shopSize(list, shoeSizes);
                            String[] strings = str.split("=");
                            if (Double.parseDouble(strings[0]) > 0) {
                                z = z + Integer.parseInt(strings[1]);
                                price = price + Double.parseDouble(strings[0]);
                                nowDate = resultData.getCreateTime().substring(0,10) + " 00:00:00";
                            }
                        } else {
                            price = price + Double.parseDouble("0");
                        }
                    }
                }
                if (price != 0) {
                    CommodityDetailys commodityDetailys = new CommodityDetailys();
                    price = price / z;
                    commodityDetailys.setShopName(record.getProductName());
                    commodityDetailys.setCreateDate(nowDate);
                    commodityDetailys.setFootage(shoeSizes);
                    commodityDetailys.setColorly(record.getGirard());
                    commodityDetailys.setAveragePrice(df.format(price));
                    commodityDetailysList.add(commodityDetailys);
                }
            }
        }
        return  commodityDetailysList;
    }

    //淘宝店铺鞋子鞋码匹配规则
    public String[] taobaoShoeCodeRule(String[] str,String shoeSizes){
        //初始化变量
        String rule = "1/3";
        String taobaoRule= "2/3";
        int i = 0;
        int x = 0;
        String shoe = null;
        String size = null;
        //判断是否包含1/3
        if (str[0].contains(rule)){//包含
            //获取鞋码和1/3所在位置下标
            i = str[0].indexOf(shoeSizes);
            x = str[0].indexOf(rule);
            if (i == -1){
                i=1;
            }
            //是否是在鞋码后面
            if (x-i < 4){//存在
                //获取鞋码
                shoe = str[0].substring(0,i+2);
                //拼接鞋码加上把1/3换成0.5
                shoe = shoe+ ".5";
                //判断数组长度是否超过要取值的长度
                if (str[0].length() > x+3) {//没有
                    //获取1/3后面的值
                    size = str[0].substring(x + 3, str[0].length());
                    str[0] = shoe + size;
                }else {
                    str[0] = shoe;
                }
            }
        }//判断是否包含2/3
        else if (str[0].contains(taobaoRule)){
            i = str[0].indexOf(shoeSizes);
            x = str[0].indexOf(taobaoRule);
            if (i == -1){
                i=1;
            }
            if (x-i < 4){
                shoe = str[0].substring(0,i+2);
                shoe = shoe+ ".5";
                if (str[0].length() > x+3) {
                    size = str[0].substring(x + 3, str[0].length());
                    str[0] = shoe + size;
                }else {
                    str[0] = shoe;
                }
            }
        }
        return str;
    }

    //验证相同鞋码的数据
    public List<ResultData> verificationDateShoeCode (List<ResultData> list, String sizePrice){
        List<ResultData> resultDataList = new ArrayList<ResultData>();
        for (ResultData resultData : list){
            if (resultData.getSizePrice() != null && !"".equals(resultData.getSizePrice())) {
                resultData.setSizePrice(resultData.getSizePrice().substring(1,resultData.getSizePrice().length() - 1));
                String[] strings = resultData.getSizePrice().split(",");
                for (int i = 0; i < strings.length; i++){
                    String[] str = strings[i].split("=");
                    String size = str[0].trim();
                    if (size.contains(sizePrice)){
                        int x = size.indexOf(sizePrice);
                        if (x == -1){
                            x = 0;
                        }
                        if (size.length() > x+3) {
                            String sizeO = size.substring(x + 2, x + 3);
                            if (".".equals(sizeO)) {
                                String sizeT = size.substring(x + 3, x + 4);
                                if (!"5".equals(sizeT)) {
                                    if (x > 1) {
                                        if (size.substring(x - 1, x + 1).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    } else {
                                        if (size.substring(x, x + 2).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    }
                                } else if ("5".equals(sizeT)) {
                                    if (x > 1) {
                                        if (size.substring(x - 1, x + 3).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    } else {
                                        if (size.substring(x, x + 4).equals(sizePrice)) {
                                            resultDataList.add(resultData);
                                        }
                                    }
                                }
                            } else {
                                if (x > 1) {
                                    if (size.substring(x - 1, x + 1).equals(sizePrice)) {
                                        resultDataList.add(resultData);
                                    }
                                } else {
                                    if (size.substring(x, x + 2).equals(sizePrice)) {
                                        resultDataList.add(resultData);
                                    }
                                }
                            }
                        }else {
                            if (size.equals(sizePrice)) {
                                resultDataList.add(resultData);
                            }

                        }
                    }
                }
            }
        }
        return resultDataList;
    }

    //鞋码图表字段拼接
    public Map<String, Object> shoeCodeChartMosaic(Map<String, Map<String, CommodityDetailys>> prams,String[] arr){
        //初始化变量
        List<String> list = new ArrayList<String>();
        List<String[]> prices = new ArrayList<String[]>();
        //把map转换为set
        Set<Map.Entry<String, Map<String, CommodityDetailys>>> entries = prams.entrySet();
        //循环获取map中的值
        for (Map.Entry<String, Map<String, CommodityDetailys>> entry : entries){
            //获取key和vaule
            String shoe = entry.getKey();
            Map<String, CommodityDetailys> sizeMap = entry.getValue();
            //把map转换为set
            Set<Map.Entry<String,CommodityDetailys>> entrySet = sizeMap.entrySet();
            String[] string = new String[64];

            //循环获取时间的值
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i];
                //循环获取map中的值
                for (Map.Entry<String, CommodityDetailys> detailysEntry : entrySet) {
                    //判断鞋码、时间是否一样
                    if (shoe.equals(detailysEntry.getValue().getFootage()) && str.substring(2,str.length()).equals(detailysEntry.getKey().substring(8,10))) {//相同把价格赋值给数组
                        String sizeprice = detailysEntry.getValue().getAveragePrice();
                        string[i] = sizeprice;
                    }
                }
                //判断数组的长度是否跟时间数组长度一样
                if (string[i] == null){//不一样
                    string[i] = "0";
                }
            }
            list.add(shoe);
            prices.add(string);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("listprice", list);
        params.put("prices", prices);
        params.put("arr", arr);
        return params;
    }
}
