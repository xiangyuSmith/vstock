package com.vstock.front.service;

import com.vstock.db.dao.*;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.Point;
import com.vstock.db.entity.ResultData;
import com.vstock.ext.util.DateUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service("resultData")
public class ResultDataService {

    private static Logger logger = Logger.getLogger(ResultDataService.class);

    @Value("${brandMarketDate}")
    String brandMarketDate;

    @Autowired
    IResultData resultDataDao;

    @Autowired
    IBasicinformation basicinformationDao;

    @Autowired
    IBasicinformationRoseDao basicinformationRoseDao;

    /**
     * 查询所有
     * @param record
     * @return
     */
    public List<ResultData> findResultDataAll(ResultData record){return resultDataDao.findResultDataAll(record);}

    /**
     * 计算品牌历史价格趋势（精确到前一天）
     */
    public void getBrandMarket(){
        for (String brand : BasicinformationRose.brandStr) {
            List<Point> brad = brandMarket(brand);
            if (brad != null && !"".equals(brad)) {
                VstockConfigService.setBrandMap(brand,brad);
            }
        }
    }

    /**
     * 根据品牌获取所有总销售价格
     * @param brand
     * @return
     */
    public List<Point> brandMarket(String brand){
        List<Point> brandStr = new ArrayList<Point>();
        for (int i = Integer.parseInt(brandMarketDate); i > 0; i--){
            ResultData record = new ResultData();
            record.setBrand(brand);
            record.setCreateTime(DateUtils.dateToString(DateUtils.wantToLose(DateUtils.getDate(DateUtils.getCurrentTimeAsString(),"yyyy-MM-dd"),i),"yyyy-MM-dd"));
            List<ResultData> resultDataList = this.findResultDataAll(record);
            Map<String,Double> sizePrice = this.sizePrice(resultDataList);
            if (sizePrice.size() > 0){
                double money = 0;
                for (String key : sizePrice.keySet()){
                    money = money + sizePrice.get(key);
                }
                Long dates = DateUtils.getDate(record.getCreateTime()+" 08:00:00","yyyy-MM-dd HH:mm:ss").getTime();
                Point strBrand = new Point(dates,(int)money);
                brandStr.add(strBrand);
            }
        }
        return brandStr;
    }

    /**
     * 分割鞋码和价格
     * @param resultDataList
     * @return
     */
    public Map<String,Double> sizePrice(List<ResultData> resultDataList){
        Map<String,Double> sizePrice = new HashMap<String,Double>();
        if (resultDataList.size() > 0) {
            for (ResultData resultData : resultDataList) {
                if (resultData.getSizePrice().contains("=")) {
                String[] sizePrices = resultData.getSizePrice().substring(1,resultData.getSizePrice().length()-1).split(",");
                    for (int i = 0; i < sizePrices.length; i++) {
                        String[] size = sizePrices[i].split("=");
                        if (size.length > 1) {
                            if (size[0] != null && !"".equals(size[0]) || size[1] != null && !"".equals(size[1])) {
                                if (size[1].contains("-")) {
                                    String[] price = size[1].split("-");
                                    size[1] = price[1];
                                }
                                sizePrice.put(size[0], Double.valueOf(size[1]));
                            }
                        }
                    }
                }else {
                    JSONObject jsonObject = new JSONObject(resultData.getSizePrice());
                    Iterator iterator = jsonObject.keySet().iterator();
                    while (iterator.hasNext()){
                        String key = (String)iterator.next();
                        String value = jsonObject.get(key).toString();
                        if (value != null && value != "") {
                            if (value.contains("-")){
                                String[] strVaule = value.split("-");
                                value = strVaule[1].substring(1,strVaule[1].length());
                            }
                            sizePrice.put(key, Double.parseDouble(value));
                        }
                    }
                }
            }
        }
        return sizePrice;
    }

    /**
     * 每天更新 每个尺码 & 每双鞋 & 涨幅数值 & 百分比
     * @return
     */
    public int insertRose(){
        Basicinformation record = new Basicinformation();
        DecimalFormat df = new DecimalFormat("######0.00");
        int i = 0;
        List<Basicinformation> basicinformationList = basicinformationDao.findAll(record);
        for (Basicinformation basicinformation : basicinformationList){
            ResultData resultData = new ResultData();
            resultData.setBasiciformationId(basicinformation.getId());
            resultData.setCreateTime(DateUtils.dateToString(DateUtils.wantToLose(new Date(),1),"yyyy-MM-dd"));
            List<ResultData> resultDatas= this.findResultDataAll(resultData);
            Map<String,Double> resultDataList = this.sizePrice(resultDatas);
            resultData.setCreateTime(DateUtils.dateToString(DateUtils.wantToLose(new Date(),2),"yyyy-MM-dd"));
            List<ResultData> resultDataes = this.findResultDataAll(resultData);
            Map<String,Double> resultDataLists = this.sizePrice(resultDataes);
            for (String size : Basicinformation.sizes){
                BasicinformationRose basicinformationRose = new BasicinformationRose();
                double totalAmount = 0;
                totalAmount = this.price(size,resultDataList);
                basicinformationRose.setCurrent_market_value(new BigDecimal(df.format(totalAmount)));
                totalAmount = 0;
                totalAmount = this.price(size,resultDataLists);
                String nowAmount = basicinformationRose.getCurrent_market_value().subtract(new BigDecimal(totalAmount)).toString();
                if (nowAmount.contains("-")){
                    basicinformationRose.setType(0);
                    logger.info("相减的涨幅度的值"+nowAmount);
                    basicinformationRose.setChange_range(new BigDecimal(nowAmount.substring(1,nowAmount.length())));
                }else {
                    basicinformationRose.setType(1);
                    basicinformationRose.setChange_range(new BigDecimal(nowAmount));
                }
                if (totalAmount == 0){
                    basicinformationRose.setPercentage_change(new BigDecimal(0));
                }else {
                    String percentage_change = df.format(basicinformationRose.getChange_range().doubleValue()/totalAmount);
                    basicinformationRose.setPercentage_change(new BigDecimal(percentage_change));
                }
                basicinformationRose.setBrand(basicinformation.getBrand());
                basicinformationRose.setBasicinformation_id(Integer.parseInt(basicinformation.getId()));
                basicinformationRose.setBasicinformation_size(size);
                basicinformationRose.setCreate_date(DateUtils.dateToString(new Date(),"yyyy-MM-dd"));
                i = basicinformationRoseDao.insert(basicinformationRose);
            }
        }
        return i;
    }

    /**
     * 获取总价
     * @param size   尺码
     * @param resultDataList   解析尺码和价格的map
     * @return
     */
    public double price(String size, Map<String,Double> resultDataList){
        double totalAmount = 0;
        for (String oldSize : resultDataList.keySet()){
            if (oldSize.contains(size)){
                if (size.length() == 2 && !oldSize.contains(".")){
                    totalAmount = totalAmount + resultDataList.get(oldSize);
                }else if (size.length() > 2){
                    totalAmount = totalAmount + resultDataList.get(oldSize);
                }
            }
        }
        return totalAmount;
    }

}
