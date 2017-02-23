package com.vstock.front.service;

import com.vstock.db.dao.IUserAssetsDao;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.UserAssets;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.ext.util.security.md.ToolMD5;
import com.vstock.front.service.interfaces.IVstockConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
//        page.setPageSize(1);
        return this.findAll(record,page);
    }

    //计算个人资产总资产及平均值等
    public BasicinformationRose findUserAssBasRose(UserAssets record){
        BasicinformationRose basicinformationRose = new BasicinformationRose();
        BigDecimal money = new BigDecimal(0);
        BigDecimal changeMoney = new BigDecimal(0);
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        time = DateUtils.wantToLose(time,1);
        String startDate = sdf.format(time);
        List<UserAssets> userAssetsList = this.findBasicinformationRoseAll(record,startDate);
        if (userAssetsList.size() > 0) {
            basicinformationRose.setId(userAssetsList.size());
            for (UserAssets userAssets : userAssetsList){
                BasicinformationRose basRose = userAssets.getBasicinformationRose();
                if (basRose != null) {
                    money = money.add(basRose.getCurrent_market_value());
                    changeMoney = changeMoney.add(userAssets.getMoney());
                }
            }
            if (money.compareTo(changeMoney) == 1) {
                basicinformationRose.setChange_range(money.divide(changeMoney,2, RoundingMode.HALF_UP));
            } else if (money.compareTo(changeMoney) == -1){
                basicinformationRose.setChange_range(new BigDecimal("-"+money.divide(changeMoney,2, RoundingMode.HALF_UP)));
            }else {
                basicinformationRose.setChange_range(money.divide(changeMoney,2, RoundingMode.HALF_UP));
            }
            basicinformationRose.setId(userAssetsList.size());
            basicinformationRose.setCurrent_market_value(money);
            basicinformationRose.setPercentage_change(money.divide(new BigDecimal(userAssetsList.size()),2, RoundingMode.HALF_UP));
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

    /**
     * 获取验证码
     * @param userId  用户ID
     * @param basicinformationId  鞋库ID
     * @param userAssetsSize   鞋码
     * @param money   金额
     * @return   sgin加密码
     */
    public String verification(Integer userId, Integer basicinformationId, String userAssetsSize, double money){
        return ToolMD5.encodeMD5Hex(new StringBuilder()
                .append("userId=").append(userId)
                .append(UserAssets.USER_ASSETS_MD5_MARK).append("userAssetsSize=").append(userAssetsSize)
                .append(UserAssets.USER_ASSETS_MD5_MARK_NOTIFY).append("basicinformationId=").append(basicinformationId)
                .append(UserAssets.USER_ASSETS_MD5_MARK_NOTIFY).append("amount=").append(money)
                .append(UserAssets.USER_ASSETS_MD5_MARK).append("Md5Sign=").append(VstockConfigService.getConfig(IVstockConfigService.USER_ASSETS_MD5KEY))
                .toString());
    }

    //验证是否通过
    public boolean judgment(UserAssets record){
        UserAssets userAssets = new UserAssets();
        userAssets.setId(record.getId());
        List<UserAssets> userAssetsList = this.findUserAssets(userAssets);
        if (userAssetsList.size() > 0){
            String s = this.verification(record.getUserId(), record.getBasicinformationId(),
                    record.getUserAssetsSize(), Double.valueOf(record.getMoney().toString()));
            if (userAssetsList.get(0).getSgin().equals(this.verification(record.getUserId(), record.getBasicinformationId(),
                    record.getUserAssetsSize(), Double.valueOf(record.getMoney().toString())))) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    //保存方法
    public int save(UserAssets record){
        int i = 0;
        if (record.getId() != null && !"".equals(record.getId())){
            if (this.judgment(record)) {
                i = this.update(record);
            }else {
                return i;
            }
        }else {
            record.setSgin(this.verification(record.getUserId(),record.getBasicinformationId(),record.getUserAssetsSize(),Double.valueOf(record.getMoney().toString())));
            if (record.getStatus() == null || "".equals(record.getStatus())){
                record.setStatus(0);
            }
            if (record.getCreateDate() == null || "".equals(record.getCreateDate())){
                record.setCreateDate(DateUtils.getCurrentTimeAsString());
            }
            i = this.insert(record);
        }
        return i;
    }

}
