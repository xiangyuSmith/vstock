package com.vstock.front.service;

import com.vstock.db.dao.IUserAssetsDao;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.UserAssets;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * Created by administor on 2016/12/7.
 */
@Service
public class UserAssetsService {
    @Autowired
    IUserAssetsDao userAssetsDao;

    /**
     * 分页查询所有记录
     * @param record
     * @param page
     * @return
     */
    public List<UserAssets> findAll(UserAssets record, Page page){
        return userAssetsDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(UserAssets record){return userAssetsDao.findCount(record);}

    /**
     * 根据品牌分组查询
     * @param record
     * @return
     */
    public List<UserAssets> findGroupBrand(UserAssets record,String startDate){return userAssetsDao.findGroupBrand(record,startDate);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(UserAssets record){return userAssetsDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(UserAssets record){
        return userAssetsDao.update(record.getStatus(),record.getInvalidDate(),record.getId());
    }

    /**
     * 关联涨幅表查询所有
     * @param record
     * @return
     */
    public List<UserAssets> findBasicinformationRoseAll(UserAssets record, String startDate){return userAssetsDao.findBasicinformationRoseAll(record,startDate);}

    public List<UserAssets> findUserAssets(UserAssets record){
        Page page = new Page(10,"1");
        page.setPageSize(2);
        return this.findAll(record,page);
    }

    //计算个人资产总资产及平均值等
    public BasicinformationRose findUserAssBasRose(UserAssets record){
        BasicinformationRose basicinformationRose = new BasicinformationRose();
        BigDecimal money = new BigDecimal(0);
        BigDecimal changeMoney = new BigDecimal(0);
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(time);
        List<UserAssets> userAssetsList = this.findBasicinformationRoseAll(record,startDate);
        if (userAssetsList.size() > 0) {
            basicinformationRose.setId(userAssetsList.size());
            for (UserAssets userAssets : userAssetsList){
                BasicinformationRose basRose = new BasicinformationRose();
                basRose = userAssets.getBasicinformationRose();
                if (basRose != null) {
                    money = money.add(basRose.getCurrent_market_value());
                    //判断是涨还是跌
                    if (userAssets.getBasicinformationRose().getType() == 1) {
                        changeMoney = changeMoney.add(basRose.getChange_range());
                    } else {
                        changeMoney = changeMoney.subtract(basRose.getChange_range());
                    }
                }
            }
            basicinformationRose.setCurrent_market_value(money);
            basicinformationRose.setChange_range(changeMoney);
            basicinformationRose.setPercentage_change(money.divide(new BigDecimal(userAssetsList.size())));
        }
        return basicinformationRose;
    }

    //图表数据查询
    public String hChar(UserAssets record){
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = sdf.format(time);
        List<UserAssets> userAssetsList = this.findGroupBrand(record,startDate);
        String strChar = "";
        String moneyChar = "";
        if (userAssetsList.size() > 0){
            for (int i = 0; i < userAssetsList.size(); i++){
                if (i < userAssetsList.size()-1) {
                    strChar = strChar + "['" + userAssetsList.get(i).getUserAssetsSize()+ "'," + userAssetsList.get(i).getId() + "],";
                    moneyChar = moneyChar + "['" + userAssetsList.get(i).getUserAssetsSize() + "'," + userAssetsList.get(i).getMoney() + "],";
                }else {
                    strChar = strChar + "['" + userAssetsList.get(i).getUserAssetsSize() + "'," + userAssetsList.get(i).getId() + "]";
                    moneyChar = moneyChar + "['" + userAssetsList.get(i).getUserAssetsSize() + "'," + userAssetsList.get(i).getMoney() + "]";
                }
            }
            strChar = "["+strChar+"]" + ":["+moneyChar+"]";
        }
        return strChar;
    }

}
