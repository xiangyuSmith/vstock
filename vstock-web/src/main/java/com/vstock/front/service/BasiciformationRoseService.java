package com.vstock.front.service;

import com.vstock.db.dao.IBasicinformationRoseDao;
import com.vstock.db.dao.IUserDao;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
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

}
