package com.vstock.front.service;

import com.vstock.db.dao.IBasicinformation;
import com.vstock.db.dao.IBasicinformationRoseDao;
import com.vstock.db.dao.ILanguageDao;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.LanguageControl;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.ToolDateTime;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BasicinformationService {

    private static Logger logger = Logger.getLogger(BasicinformationService.class);

    @Autowired
    IBasicinformation basicinformationDao;
    @Autowired
    IBasicinformationRoseDao basicinformationRoseDao;
    @Autowired
    ILanguageDao languageDao;

    /**
     * 根据分类查询鞋库的基本数据
     * @param type  -1为查询除默认类型外所有类型
     * @return
     */
    public List<Basicinformation> findByType(int type){
        return basicinformationDao.findByType(type);
    }

    /**
     * 首页鞋子分类 - 爆款体检 & 销售量
     * @return
     */
    public List<Basicinformation> findByBao(int type){
        return basicinformationDao.findByBao(type);
    }

    public Basicinformation findObj(Basicinformation basicinformation){
        return basicinformationDao.find(basicinformation).get(0);
    }

    /**
     * @param bftSize 尺码
     * @param year 年份
     * @param brand 品牌
     * @param priceStart 价格区间:start
     * @param priceEnd 价格区间:end
     * @return
     */
    public List<Basicinformation> findBasicinForSorts(String productName,Integer type,String bftSize,String year,String brand,String priceStart,String priceEnd,Integer pageStart,Integer pageSize){
        return basicinformationDao.findBasicinForSorts(productName,type,bftSize,year,brand,priceStart,priceEnd,pageStart,pageSize);
    }

    /**
     * 获取鞋库总数
     * @return
     */
    public Long findCount(){
        return basicinformationDao.findCount();
    }

    /**
     * 鞋库品牌汇总
     * @return
     */
    public List<String> getBrands(){
        return basicinformationDao.getBrands();
    }

    public BasicinformationRose findRose(BasicinformationRose basicinformationRose){
        basicinformationRose.setBasicinformation_size("40");
        String startTime = ToolDateTime.format(new Date(),ToolDateTime.pattern_ymd) + " 00:00:00";
        String endTime = ToolDateTime.format(new Date(),ToolDateTime.pattern_ymd) + " 23:59:59";
        List<BasicinformationRose> BfRoseList = basicinformationRoseDao.findRose(basicinformationRose,startTime,endTime);
        if(BfRoseList.size() > 0){
            return BfRoseList.get(0);
        }
        return null;
    }

    public Map<String,Object> getPricesTrend(int bid,String size, Trade trade){
        BasicinformationRose basicinformationRose = new BasicinformationRose();
        Map<String,Object> resParams = new HashedMap();
        if(trade == null){
            resParams.put("roseType",0);
            resParams.put("difference",0);
            resParams.put("percentag",0);
            return resParams;
        }
        if(trade.getTransactionMoney() != null){
            //获取当前市场均价
            basicinformationRose.setBasicinformation_id(bid);
            basicinformationRose.setBasicinformation_size(size);
            basicinformationRose = findRose(basicinformationRose);
            if(basicinformationRose != null){
                //市场价
                BigDecimal market = basicinformationRose.getCurrent_market_value();
                int roseType = basicinformationRose.getType();
                //成交价
                BigDecimal transactionMoney = trade.getTransactionMoney();
                double difference = 0;
                double percentag = 0;
                if(market.compareTo(BigDecimal.ZERO)!=0){
                    difference = BasicinformationRose.getDifference(market,transactionMoney).doubleValue();
                    percentag = BasicinformationRose.getPercentag(market,transactionMoney).doubleValue();
                }
                resParams.put("roseType",roseType);
                resParams.put("difference",difference);
                resParams.put("percentag",percentag);
            }
        }
        return resParams;
    }

    /**
     * 获取中英文检索关键词
     */
    public List<LanguageControl> findLanguage(LanguageControl record){
        return languageDao.findLanguage(record);
    }

}
