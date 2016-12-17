package com.vstock.front.service;

import com.vstock.db.dao.IBasicinformation;
import com.vstock.db.dao.IBasicinformationRoseDao;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.Trade;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class BasicinformationService {

    private static Logger logger = Logger.getLogger(BasicinformationService.class);

    @Autowired
    IBasicinformation basicinformationDao;
    @Autowired
    IBasicinformationRoseDao basicinformationRoseDao;

    /**
     * 根据分类查询鞋库的基本数据
     * @param type  -1为查询除默认类型外所有类型
     * @return
     */
    public List<Basicinformation> findByType(int type){
        return basicinformationDao.findByType(type);
    }

    public Basicinformation findObj(Basicinformation basicinformation){
        return basicinformationDao.find(basicinformation);
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
        return basicinformationRoseDao.findRose(basicinformationRose);
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
                double difference = BasicinformationRose.getDifference(market,transactionMoney).doubleValue();
                double percentag = BasicinformationRose.getPercentag(market,transactionMoney).doubleValue();
                resParams.put("roseType",roseType);
                resParams.put("difference",difference);
                resParams.put("percentag",percentag);
            }
        }
        return resParams;
    }
}
