package com.vstock.admin.base.service;

import com.vstock.db.dao.IBasicinformation;
import com.vstock.db.entity.Basicinformation;
import com.vstock.admin.base.util.Page;
import com.vstock.db.entity.Dictionaries;
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
        List<Basicinformation> basicinformationList = new ArrayList<Basicinformation>();
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"bid")));
        if(basicinformation.getId() != null && !"".equals(basicinformation.getId())){
            Basicinformation basicinformationT = new Basicinformation();
            basicinformationT = mongoTemplate.findById(basicinformation.getId(),Basicinformation.class);
            basicinformationList.add(basicinformationT);
            return basicinformationList;
        }
        if(basicinformation.getName() != null && !"".equals(basicinformation.getName())){
            query.addCriteria(Criteria.where("name").regex(".*?" + basicinformation.getName() + ".*", "i"));
        }
        if(basicinformation.getBrand() != null && !"".equals(basicinformation.getBrand())){
            query.addCriteria(Criteria.where("brand").regex(basicinformation.getBrand()));
        }
        if(basicinformation.getArtNo() != null && !"".equals(basicinformation.getArtNo())){
            query.addCriteria(Criteria.where("artNo").regex(basicinformation.getArtNo()));
        }
        //判断中文标识条件有值，是否有中文标识为全部
        if(basicinformation.getChineselogo() != null && !"".equals(basicinformation.getChineselogo())
                && "0".equals(basicinformation.getChineselogos())){
            //添加中文标识条件的值作为查询条件
            query.addCriteria(Criteria.where("chineselogo").regex(basicinformation.getChineselogo()));
        }//判断中文标识条件有值，是否有中文标识有值
        else if (basicinformation.getChineselogo() != null && !"".equals(basicinformation.getChineselogo())
                && !"0".equals(basicinformation.getChineselogos())) {
            if ("1".equals(basicinformation.getChineselogos())) {//存在中文标识
                //添加中文标识条件的值作为查询条件
                query.addCriteria(Criteria.where("chineselogo").regex(basicinformation.getChineselogo()));
            } //不存在中文标识
            else if ("2".equals(basicinformation.getChineselogos())) {
                //直接返回0
                return null;
            }
        }//中文标识条件没有值
        else if(!"0".equals(basicinformation.getChineselogos())){
            String[] str = new String[]{"",null};
            if ("1".equals(basicinformation.getChineselogos())) {//判断有中文标识
                //添加不包含空查询条件
                query.addCriteria(Criteria.where("chineselogo").nin(str));
            } else if ("2".equals(basicinformation.getChineselogos())) {//判断无中文标识
                //添加包含空查询条件
                query.addCriteria(Criteria.where("chineselogo").in(str));
            }
        }
        //判断国内发售价低到 高存在值，并且是否有国内发售价不为全部
        if(basicinformation.getCofferprices() != null && !"".equals(basicinformation.getCofferprices())
                && basicinformation.getCofferprice() != null && !"".equals(basicinformation.getCofferprice())){
            //判断是否有国内发售价为否
            if ("2".equals(basicinformation.getBscofferprice())) {
                //返回0
                return null;
            }else {//有国内发售价
                //添加发售价低到高为查询条件
                query.addCriteria(Criteria.where("cofferprice").gte(basicinformation.getCofferprice()).lte(basicinformation.getCofferprices()));
            }
        }//判断国内发售价低为空，国内发售价高有值。是否有国内发售价不为全部
        else if (basicinformation.getCofferprices() != null && !"".equals(basicinformation.getCofferprices())
                && !"0".equals(basicinformation.getBscofferprice())
                && (basicinformation.getCofferprice() == null || "".equals(basicinformation.getCofferprice()))){
            //判断无国内发售价
            if ("2".equals(basicinformation.getBscofferprice())) {
                //返回0
                return null;
            }else {//有国内发售价
                //添加小于国内发售价高为条件
                query.addCriteria(Criteria.where("cofferprice").lte(basicinformation.getCofferprices()));
            }
        }//判断国内发售价高不为空，国内发售价低为空。且是否存在国内发售价为全部
        else if (basicinformation.getCofferprices() != null && !"".equals(basicinformation.getCofferprices())
                && "0".equals(basicinformation.getBscofferprice())
                && (basicinformation.getCofferprice() == null || "".equals(basicinformation.getCofferprice()))) {
            //添加小于国内发售价高为条件
            query.addCriteria(Criteria.where("cofferprice").lte(basicinformation.getCofferprices()));
        }//判断国内发售价高为空，国内发售价低不为空，且是否存在国内发售价为全部
        else if (basicinformation.getCofferprice() != null && !"".equals(basicinformation.getCofferprice())
                && "0".equals(basicinformation.getBscofferprice())
                && (basicinformation.getCofferprices() == null || "".equals(basicinformation.getCofferprices()))){
            //添加大于发售价低为条件
            query.addCriteria(Criteria.where("cofferprice").gte(basicinformation.getCofferprice()));
        }//判断国内发售价高为空，国内发售价低不为空，切是否存在国内发售价不为全部
        else if (basicinformation.getCofferprice() != null && !"".equals(basicinformation.getCofferprice())
                && !"0".equals(basicinformation.getBscofferprice())
                && (basicinformation.getCofferprices() == null || "".equals(basicinformation.getCofferprices()))){
            //判断不存在国内发售价
            if ("2".equals(basicinformation.getBscofferprice())) {
                //直接返回0
                return null;
            }else {//存在国内发售价
                //添加大于国内发售价低为条件
                query.addCriteria(Criteria.where("cofferprice").gte(basicinformation.getCofferprice()));
            }
        }//判断国内发售价低、高为空，是否存在国内发售价不是全部
        else if (!"".equals(basicinformation.getBscofferprice()) && basicinformation.getBscofferprice() != null
                && !"0".equals(basicinformation.getBscofferprice())){
            String[] str = new String[]{"",null};
            //不存在国内发售价
            if ("2".equals(basicinformation.getBscofferprice())) {
                //添加包含空为查询条件
                query.addCriteria(Criteria.where("cofferprice").in(str));
            }else {//存在国内发售价
                //添加不包含空位查询条件
                query.addCriteria(Criteria.where("cofferprice").nin(str));
            }
        }
        //判断国内发售开始时间有值，国内发售结束时间有值且是否存在国内发售价不为空
        if(startCsaledate != null && !"".equals(startCsaledate)
                && endCsaledate != null && !"".equals(endCsaledate)){
            //判断不存在国内发售价
            if ("2".equals(basicinformation.getCsaledate())){
                //返回0
                return null;
            }else {//存在国内发售价
                //添加小于开始时间，大于结束时间为条件
                query.addCriteria(Criteria.where("csaledate").gte(startCsaledate).lte(endCsaledate));
            }
        }//判断开始时间为空，结束时间不为空且是否存在值不为全部
        else if(endCsaledate != null && !"".equals(endCsaledate)
                && !"0".equals(basicinformation.getCsaledate())
                && (startCsaledate == null || "".equals(startCsaledate))) {
            //判断不存在国内发售时间
            if ("2".equals(basicinformation.getCsaledate())) {
                //返回0
                return null;
            } else {//存在国内发售时间
                //添加小于结束时间为条件
                query.addCriteria(Criteria.where("csaledate").lte(endCsaledate));
            }
        }//判断开始时间为空、结束时间不为空且是否存在值为全部
        else if(endCsaledate != null && !"".equals(endCsaledate)
                && "0".equals(basicinformation.getCsaledate())
                && (startCsaledate == null || "".equals(startCsaledate))) {
            //添加小于结束时间为条件
            query.addCriteria(Criteria.where("csaledate").lte(endCsaledate));
        }//判断开始时间不为空、结束时间为空切是否存在值不为全部
        else if(startCsaledate != null && !"".equals(startCsaledate)
                && !"0".equals(basicinformation.getCsaledate())
                && (endCsaledate == null || "".equals(endCsaledate))) {
            //判断不存在国内发售日期
            if ("2".equals(basicinformation.getCsaledate())) {
                //返回0
                return null;
            } else {//存在国内发售日期
                //添加大于开始日期为条件
                query.addCriteria(Criteria.where("csaledate").gte(startCsaledate));
            }
        }//判断开始时间不为空、结束时间为空且是否存在国内发售时间为全部
        else if(startCsaledate != null && !"".equals(startCsaledate)
                && "0".equals(basicinformation.getCsaledate())
                && (endCsaledate == null || "".equals(endCsaledate))) {
            //添加大于开始时间为条件
            query.addCriteria(Criteria.where("csaledate").gte(startCsaledate));
        }//判断开始时间为空、结束时间为空且是否存在国内发售价格不为全部
        else if(!"".equals(basicinformation.getCsaledate()) && basicinformation.getCsaledate() != null
                && !"0".equals(basicinformation.getCsaledate())) {
            String[] str = new String[]{"",null};
            //不存在发售时间
            if("2".equals(basicinformation.getCsaledate())) {
                //添加包含空为条件
                query.addCriteria(Criteria.where("csaledate").in(str));
            }else {//存在发售时间
                //添加不包含空为条件
                query.addCriteria(Criteria.where("csaledate").nin(str));
            }
        }
        query.skip(page.getStartPos());
        query.limit(page.getPageSize());
        basicinformationList = mongoTemplate.find(query,Basicinformation.class);
        return basicinformationList;
    }

    //获取带参数条件的记录总数
    public Long findLimitCount(Basicinformation basicinformation,
                               String startCsaledate,String endCsaledate,
                               String startEsaledate,String endEsaledate,
                               String startCreatetime,String endCreatetime){
        //初始化变量
        Query query = null;
        List<Basicinformation> basicinformationList = null;
        //判断是否有查询条件
        if(basicinformation.getId() == null && basicinformation.getState() == null && basicinformation.getName()==null && basicinformation.getBrand()==null
                && basicinformation.getArtNo() ==null && startEsaledate==null && startCsaledate==null){
            //没有，直接查询
            basicinformationList = mongoTemplate.findAll(Basicinformation.class);
        }else{//存在
            query = new Query();
            //判断是否有ID查询
            if(basicinformation.getId() != null && !"".equals(basicinformation.getId())){//存在
                Basicinformation basicinformationT = new Basicinformation();
                //根据ID查询
                basicinformationT = mongoTemplate.findById(basicinformation.getId(),Basicinformation.class);
                if (basicinformationT != null){//存在返回一
                    return Long.valueOf("1");
                }else {//不存在返回0
                    return Long.valueOf("0");
                }
            }
            //判断状态是否有值
            if (basicinformation.getState() != null && !"".equals(basicinformation.getState())){
                //添加包含查询条件
                query.addCriteria(Criteria.where("state").regex(basicinformation.getState()));
            }
            if(basicinformation.getName() != null && !"".equals(basicinformation.getName())){
                query.addCriteria(Criteria.where("name").regex(".*?" + basicinformation.getName() + ".*", "i"));
            }
            if(basicinformation.getBrand() != null && !"".equals(basicinformation.getBrand())){
                query.addCriteria(Criteria.where("brand").regex(basicinformation.getBrand()));
            }
            if(basicinformation.getArtNo() != null && !"".equals(basicinformation.getArtNo())){
                query.addCriteria(Criteria.where("artNo").regex(basicinformation.getArtNo()));
            }
            //判断中文标识条件有值，是否有中文标识为全部
            if(basicinformation.getChineselogo() != null && !"".equals(basicinformation.getChineselogo())
                    && "0".equals(basicinformation.getChineselogos())){
                //添加中文标识条件的值作为查询条件
                query.addCriteria(Criteria.where("chineselogo").regex(basicinformation.getChineselogo()));
            }//判断中文标识条件有值，是否有中文标识有值
            else if (basicinformation.getChineselogo() != null && !"".equals(basicinformation.getChineselogo())
                    && !"0".equals(basicinformation.getChineselogos())) {
                String[] str = new String[]{"",null};

                if ("1".equals(basicinformation.getChineselogos())) {//存在中文标识
                    //添加中文标识条件的值作为查询条件
                    query.addCriteria(Criteria.where("chineselogo").regex(basicinformation.getChineselogo()));
                } //不存在中文标识
                else if ("2".equals(basicinformation.getChineselogos())) {
                    //直接返回0
                    return Long.valueOf("0");
                }
            }//中文标识条件没有值
            else if(basicinformation.getChineselogos() != null && !"".equals(basicinformation.getChineselogos())
                    && !"0".equals(basicinformation.getChineselogos())){
                String[] str = new String[]{"",null};
                if ("1".equals(basicinformation.getChineselogos())) {//判断有中文标识
                    //添加不包含空查询条件
                    query.addCriteria(Criteria.where("chineselogo").nin(str));
                } else if ("2".equals(basicinformation.getChineselogos())) {//判断无中文标识
                    //添加包含空查询条件
                    query.addCriteria(Criteria.where("chineselogo").in(str));
                }
            }
            //判断国内发售价低到 高存在值，并且是否有国内发售价不为全部
            if(basicinformation.getCofferprices() != null && !"".equals(basicinformation.getCofferprices())
                    && basicinformation.getCofferprice() != null && !"".equals(basicinformation.getCofferprice())){
                //判断是否有国内发售价为否
                if ("2".equals(basicinformation.getBscofferprice())) {
                    //返回0
                    return Long.valueOf("0");
                }else {//有国内发售价
                    //添加发售价低到高为查询条件
                    query.addCriteria(Criteria.where("cofferprice").gte(basicinformation.getCofferprice()).lte(basicinformation.getCofferprices()));
                }
            }
            //判断国内发售价低为空，国内发售价高有值。是否有国内发售价不为全部
            else if (basicinformation.getCofferprices() != null && !"".equals(basicinformation.getCofferprices())
                    && !"0".equals(basicinformation.getBscofferprice())
                    && (basicinformation.getCofferprice() == null || "".equals(basicinformation.getCofferprice()))){
                //判断无国内发售价
                if ("2".equals(basicinformation.getBscofferprice())) {
                    //返回0
                    return Long.valueOf("0");
                }else {//有国内发售价
                    //添加小于国内发售价高为条件
                    query.addCriteria(Criteria.where("cofferprice").lte(basicinformation.getCofferprices()));
                }
            }//判断国内发售价高不为空，国内发售价低为空。且是否存在国内发售价为全部
            else if (basicinformation.getCofferprices() != null && !"".equals(basicinformation.getCofferprices())
                    && "0".equals(basicinformation.getBscofferprice())
                    && (basicinformation.getCofferprice() == null || "".equals(basicinformation.getCofferprice()))) {
                //添加小于国内发售价高为条件
                query.addCriteria(Criteria.where("cofferprice").lte(basicinformation.getCofferprices()));
            }//判断国内发售价高为空，国内发售价低不为空，且是否存在国内发售价为全部
            else if (basicinformation.getCofferprice() != null && !"".equals(basicinformation.getCofferprice())
                    && "0".equals(basicinformation.getBscofferprice())
                    && (basicinformation.getCofferprices() == null || "".equals(basicinformation.getCofferprices()))){
                //添加大于发售价低为条件
                query.addCriteria(Criteria.where("cofferprice").gte(basicinformation.getCofferprice()));
            }//判断国内发售价高为空，国内发售价低不为空，切是否存在国内发售价不为全部
            else if (basicinformation.getCofferprice() != null && !"".equals(basicinformation.getCofferprice())
                    && !"0".equals(basicinformation.getBscofferprice())
                    && (basicinformation.getCofferprices() == null || "".equals(basicinformation.getCofferprices()))){
                //判断不存在国内发售价
                if ("2".equals(basicinformation.getBscofferprice())) {
                    //直接返回0
                    return Long.valueOf("0");
                }else {//存在国内发售价
                    //添加大于国内发售价低为条件
                    query.addCriteria(Criteria.where("cofferprice").gte(basicinformation.getCofferprice()));
                }
            }//判断国内发售价低、高为空，是否存在国内发售价不是全部
            else if (!"".equals(basicinformation.getBscofferprice()) && basicinformation.getBscofferprice() != null
                      && !"0".equals(basicinformation.getBscofferprice())){
                String[] str = new String[]{"",null};
                //不存在国内发售价
                if ("2".equals(basicinformation.getBscofferprice())) {
                    //添加包含空为查询条件
                    query.addCriteria(Criteria.where("cofferprice").in(str));
                }else {//存在国内发售价
                    //添加不包含空位查询条件
                    query.addCriteria(Criteria.where("cofferprice").nin(str));
                }
            }
            //判断国内发售开始时间有值，国内发售结束时间有值且是否存在国内发售价不为空
            if(startCsaledate != null && !"".equals(startCsaledate)
                    && endCsaledate != null && !"".equals(endCsaledate)){
                //判断不存在国内发售价
                if ("2".equals(basicinformation.getCsaledate())){
                    //返回0
                    return Long.valueOf("0");
                }else {//存在国内发售价
                    //添加小于开始时间，大于结束时间为条件
                    query.addCriteria(Criteria.where("csaledate").gte(startCsaledate).lte(endCsaledate));
                }
            }//判断开始时间为空，结束时间不为空且是否存在值不为全部
            else if(endCsaledate != null && !"".equals(endCsaledate)
                    && !"0".equals(basicinformation.getCsaledate())
                    && (startCsaledate == null || "".equals(startCsaledate))) {
                //判断不存在国内发售时间
                if ("2".equals(basicinformation.getCsaledate())) {
                    //返回0
                    return Long.valueOf("0");
                } else {//存在国内发售时间
                    //添加小于结束时间为条件
                    query.addCriteria(Criteria.where("csaledate").lte(endCsaledate));
                }
            }//判断开始时间为空、结束时间不为空且是否存在值为全部
            else if(endCsaledate != null && !"".equals(endCsaledate)
                    && "0".equals(basicinformation.getCsaledate())
                    && (startCsaledate == null || "".equals(startCsaledate))) {
                //添加小于结束时间为条件
                query.addCriteria(Criteria.where("csaledate").lte(endCsaledate));
            }//判断开始时间不为空、结束时间为空切是否存在值不为全部
            else if(startCsaledate != null && !"".equals(startCsaledate)
                    && !"0".equals(basicinformation.getCsaledate())
                    && (endCsaledate == null || "".equals(endCsaledate))) {
                //判断不存在国内发售日期
                if ("2".equals(basicinformation.getCsaledate())) {
                    //返回0
                    return Long.valueOf("0");
                } else {//存在国内发售日期
                    //添加大于开始日期为条件
                    query.addCriteria(Criteria.where("csaledate").gte(startCsaledate));
                }
            }//判断开始时间不为空、结束时间为空且是否存在国内发售时间为全部
            else if(startCsaledate != null && !"".equals(startCsaledate)
                    && "0".equals(basicinformation.getCsaledate())
                    && (endCsaledate == null || "".equals(endCsaledate))) {
                //添加大于开始时间为条件
                query.addCriteria(Criteria.where("csaledate").gte(startCsaledate));
            }//判断开始时间为空、结束时间为空且是否存在国内发售价格不为全部
            else if(!"".equals(basicinformation.getCsaledate()) && basicinformation.getCsaledate() != null
                      && !"0".equals(basicinformation.getCsaledate())) {
                String[] str = new String[]{"",null};
                //不存在发售时间
                if("2".equals(basicinformation.getCsaledate())) {
                    //添加包含空为条件
                    query.addCriteria(Criteria.where("csaledate").in(str));
                }else {//存在发售时间
                    //添加不包含空为条件
                    query.addCriteria(Criteria.where("csaledate").nin(str));
                }
            }

            basicinformationList = mongoTemplate.find(query, Basicinformation.class);
        }
        return Long.valueOf(basicinformationList.size());
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
