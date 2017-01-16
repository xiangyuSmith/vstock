package com.vstock.front.service;

import com.vstock.db.dao.IBasicinformationRoseDao;
import com.vstock.db.dao.IUserDao;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.Point;
import com.vstock.db.entity.User;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basiciformationRose")
public class BasiciformationRoseService {

    @Autowired
    IBasicinformationRoseDao basicinformationRoseDao;

    /**
     * 查询所有
     * @param record
     * @return
     */
    public List<BasicinformationRose> findAllDate(BasicinformationRose record){
        return basicinformationRoseDao.findAllDate(record);
    }

    public List<BasicinformationRose> findNewRose(BasicinformationRose record,Page page){
        return basicinformationRoseDao.findNewRose(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 计算品牌涨幅数值 & 百分比
     */
    public void getRoseDegree(){
        for (String brand : BasicinformationRose.brandStr) {
            Map<String, Object> brad = roseDegree(brand, DateUtils.dateToString(DateUtils.wantToLose(new Date(),1),"yyyy-MM-dd"));
            if (brad != null && !"".equals(brad)) {
                VstockConfigService.setRoesMap(brand,brad);
            }
        }
    }

    /**
     * 根据品牌和时间获取涨幅总额和差价、涨幅度
     * @param brand   品牌名称
     * @param createDate   创建时间（yyyy-MM-dd）
     * @return
     */
    public Map<String, Object> roseDegree(String brand, String createDate){
        Map<String, Object> roseDegree = new HashMap<String, Object>();
        BasicinformationRose record = new BasicinformationRose();
        record.setBrand(brand);
        record.setCreate_date(createDate);
        List<BasicinformationRose> basicinformationRoseList = this.findAllDate(record);
        if (basicinformationRoseList.size() > 0){
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal totalRiseFall = new BigDecimal(0);
            BigDecimal percentageTotal = new BigDecimal(0);
            for (BasicinformationRose basicinformationRose : basicinformationRoseList){
                if (basicinformationRose.getType() == 1){
                    totalAmount = totalAmount.add(basicinformationRose.getCurrent_market_value());
                    totalRiseFall = totalRiseFall.add(basicinformationRose.getChange_range());
                    percentageTotal = percentageTotal.add(basicinformationRose.getPercentage_change());
                }else {
                    totalAmount = totalAmount.subtract(basicinformationRose.getCurrent_market_value());
                    totalRiseFall = totalRiseFall.subtract(basicinformationRose.getChange_range());
                    percentageTotal = percentageTotal.subtract(basicinformationRose.getPercentage_change());
                }
            }
            roseDegree.put("Current_market_value",totalAmount);
            roseDegree.put("Change_range",totalRiseFall);
            roseDegree.put("Percentage_change",percentageTotal);
        }
        return roseDegree;
    }

    /**
     * 根据品牌查询当天总价格、涨幅、百分比
     * @param brand
     * @return
     */
    public Map<String,Object> reseDegreeN(String brand){
        Map<String, Object> resultModel = new HashMap<String, Object>();
        DecimalFormat df = new DecimalFormat("######0.0000");
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal totalRiseFall = new BigDecimal(0);
        BigDecimal percentageTotal = new BigDecimal(0);
        List<Point> jsonArray = VstockConfigService.getBrand(brand);
        if (jsonArray.size() > 0){
            totalAmount = new BigDecimal(jsonArray.get(jsonArray.size()-1).getY());
            if (jsonArray.size() > 1){
                totalRiseFall = new BigDecimal(jsonArray.get(jsonArray.size()-1).getY() - jsonArray.get(jsonArray.size()-2).getY());
                if (totalRiseFall.toString().contains("-")){
                    resultModel.put("Change_range",totalRiseFall);
                    totalRiseFall = new BigDecimal(totalRiseFall.toString().substring(1,totalRiseFall.toString().length()));
                }else {
                    resultModel.put("Change_range",totalRiseFall);
                }
                percentageTotal = new BigDecimal(Double.parseDouble(df.format(totalRiseFall.doubleValue()/jsonArray.get(jsonArray.size()-2).getY()))*100);
            }else {
                totalRiseFall = new BigDecimal(jsonArray.get(jsonArray.size()-1).getY());
                resultModel.put("Change_range",totalRiseFall);
                percentageTotal = new BigDecimal(100);
            }
        }
        resultModel.put("Current_market_value",totalAmount);
        resultModel.put("Percentage_change",percentageTotal);
        return resultModel;
    }

}
