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
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("basicinformation"));
        List<Ids> idsIsNull = mongoTemplate.find(query,Ids.class);
        if(idsIsNull.size() == 0){
            Ids ids = new Ids();
            ids.setName("basicinformation");
            ids.setUpId(1);
            mongoTemplate.save(ids);
        }
        Update update = new Update().inc("upId", 1);
        Ids ids = mongoTemplate.findAndModify(query,update, Ids.class);
        record.setBid(ids.getUpId());
        mongoTemplate.save(record);
        return 1;
    }

    //修改数据
    public int updatebasicinfrom(Basicinformation record){return basicinformationDao.update(record,record.getId());}

    //删除数据
    public int deletebasicinfrom(String id){return basicinformationDao.delete(id);}

    //修改状态
    public int updatesicinState(Basicinformation basicinformation){
        mongoTemplate.save(basicinformation);
        return 1;
    }

    //不带分页查询
    public List<Basicinformation> findAll(Basicinformation record){
        Query query = new Query();
        if(record.getId() != null && !"".equals(record.getId())){
            query.addCriteria(Criteria.where("id").is(record.getId()));
        }
        if(record.getBrand() != null && !"".equals(record.getBrand())){
            query.addCriteria(Criteria.where("brand").is(record.getBrand()));
        }
        if(record.getName() != null && !"".equals(record.getName())){
            query.addCriteria(Criteria.where("name").is(record.getName()));
        }
        return mongoTemplate.find(query,Basicinformation.class);
    }

    //获取所有名称
    public List<Basicinformation> findNames(String artNo){
        List<Basicinformation> basicinformationList = null;
        if("".equals(artNo) || artNo == null){
            basicinformationList = mongoTemplate.findAll(Basicinformation.class);
        }else{
            Query query = new Query();
            query.addCriteria(Criteria.where("artNo").regex(artNo));
            basicinformationList = mongoTemplate.find(query,Basicinformation.class);
        }
        return basicinformationList;
    }

    //根据名称获取货号
    public List<Basicinformation> findGirard(String productName){
        Query query = new Query();
        if(productName != null && !"".equals(productName)){
            query.addCriteria(Criteria.where("name").is(productName));
        }
        return mongoTemplate.find(query,Basicinformation.class);
    }

    //获取所有数据
    public List<Basicinformation> moveData(){
        Basicinformation basicinformation = new Basicinformation();
        return basicinformationDao.findAll(basicinformation);
    }
}
