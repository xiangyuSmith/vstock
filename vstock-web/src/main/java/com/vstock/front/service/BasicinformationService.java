package com.vstock.front.service;

import com.vstock.db.dao.IBasicinformation;
import com.vstock.db.entity.Basicinformation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicinformationService {

    private static Logger logger = Logger.getLogger(BasicinformationService.class);

    @Autowired
    IBasicinformation basicinformationDao;

    /**
     * 根据分类查询鞋库的基本数据
     * @param type  -1为查询除默认类型外所有类型
     * @return
     */
    public List<Basicinformation> findByType(int type){
        return basicinformationDao.findByType(type);
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
}
