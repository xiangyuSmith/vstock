package com.vstock.admin.base.service;

import com.vstock.db.dao.IBasicinformation;
import com.vstock.db.entity.Basicinformation;
import com.vstock.ext.util.Page;
import com.vstock.db.entity.Ids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administor on 2016/7/12.
 */
@Service("basicinformation")
public class BasicinformationService {
    @Autowired
    IBasicinformation basicinformationDao;
    @Autowired
    MongoTemplate mongoTemplate;

    //根据时间区间条件分页查询所有
    public List<Basicinformation> findbasicAll(Basicinformation basicinformation,
                                               Page page,String startCsaledate,
                                               String endCsaledate,String startEsaledate,
                                               String endEsaledate,String startCreatetime,
                                               String endCreatetime){
        return basicinformationDao.findLimitAll(basicinformation,page.getStartPos(),page.getPageSize(),
                startCsaledate,endCsaledate,startEsaledate,endEsaledate,startCreatetime,endCreatetime);
    }

    //获取带参数条件的记录总数
    public Long findLimitCount(Basicinformation basicinformation,
                               String startCsaledate,String endCsaledate,
                               String startEsaledate,String endEsaledate,
                               String startCreatetime,String endCreatetime){
        return basicinformationDao.findLimitCount(basicinformation,startCsaledate,endCsaledate,startEsaledate,
                endEsaledate,startCreatetime,endCreatetime);
    }

    //添加数据
    public int insertbasicinfrom(Basicinformation record){
        return basicinformationDao.insert(record);
    }

    //修改数据
    public int updatebasicinfrom(Basicinformation record){
        return basicinformationDao.update(record,record.getId());
    }

    //修改状态
    public int updatesicinState(Basicinformation basicinformation){
        mongoTemplate.save(basicinformation);
        return 1;
    }

    //不带分页查询
    public List<Basicinformation> findAll(Basicinformation record){
        return basicinformationDao.findAll(record);
    }

    //获取所有名称
    public List<Basicinformation> findNames(String artNo){
        Basicinformation basicinformation = new Basicinformation();
        basicinformation.setArtNo(artNo);
        return basicinformationDao.findAll(basicinformation);
    }

    //根据名称获取货号
    public List<Basicinformation> findGirard(String productName){
        Basicinformation basicinformation = new Basicinformation();
        basicinformation.setName(productName);
        return basicinformationDao.findAll(basicinformation);
    }

    //获取所有数据
    public List<Basicinformation> moveData(){
        Basicinformation basicinformation = new Basicinformation();
        return basicinformationDao.findAll(basicinformation);
    }
}
