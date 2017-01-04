package com.vstock.front.service;

import com.vstock.db.dao.*;
import com.vstock.db.entity.Point;
import com.vstock.db.entity.ResultData;
import com.vstock.ext.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("resultData")
public class ResultDataService {

    @Value("${brandMarketDate}")
    String brandMarketDate;

    @Autowired
    IResultData resultDataDao;

    /**
     * 查询所有
     * @param record
     * @return
     */
    public List<ResultData> findResultDataAll(ResultData record){return resultDataDao.findResultDataAll(record);}

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
            }
        }
        return sizePrice;
    }

}
