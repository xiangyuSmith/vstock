package com.vstock.admin.service;

import com.vstock.db.dao.*;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by administor on 2016/7/12.
 */
@Service("basicinformation")
public class BasicinformationService {

    @Autowired
    IBasicinformation basicinformationDao;
    @Autowired
    IResultData resultDataDao;
    @Autowired
    IPricePeakDao pricePeakDao;
    @Autowired
    IBasicinformationRoseDao basicinformationRoseDao;
    @Autowired
    IBasicinformationTemporary basicinformationTemporaryDao;

    static List<Basicinformation> bftList = new ArrayList<Basicinformation>();

    public static List<Basicinformation> getBftList() {
        return bftList;
    }

    public void finaBftList(){
        bftList = findNames("");
    }

    //根据时间区间条件分页查询所有
    public List<Basicinformation> findbasicAll(Basicinformation basicinformation,
                                               Page page, String startCsaledate,
                                               String endCsaledate, String startEsaledate,
                                               String endEsaledate, String startCreatetime,
                                               String endCreatetime) {
        return basicinformationDao.findLimitAll(basicinformation, page.getStartPos(), page.getPageSize(),
                startCsaledate, endCsaledate, startEsaledate, endEsaledate, startCreatetime, endCreatetime);
    }

    //获取带参数条件的记录总数
    public Long findLimitCount(Basicinformation basicinformation,
                               String startCsaledate, String endCsaledate,
                               String startEsaledate, String endEsaledate,
                               String startCreatetime, String endCreatetime) {
        return basicinformationDao.findLimitCount(basicinformation, startCsaledate, endCsaledate, startEsaledate,
                endEsaledate, startCreatetime, endCreatetime);
    }

    //添加数据
    public int insertbasicinfrom(Basicinformation record) {
        return basicinformationDao.insert(record);
    }

    //修改数据
    public int updatebasicinfrom(Basicinformation record) {
        return basicinformationDao.update(record, record.getId());
    }

    //不带分页查询
    public List<Basicinformation> findAll(Basicinformation record) {
        return basicinformationDao.findAll(record);
    }

    //获取所有名称
    public List<Basicinformation> findNames(String artNo) {
        Basicinformation basicinformation = new Basicinformation();
        basicinformation.setArtNo(artNo);
        return basicinformationDao.findAll(basicinformation);
    }

    //根据名称获取货号
    public List<Basicinformation> findGirard(String productName) {
        Basicinformation basicinformation = new Basicinformation();
        basicinformation.setName(productName);
        return basicinformationDao.findAll(basicinformation);
    }

    //获取所有数据
    public List<Basicinformation> moveData() {
        Basicinformation basicinformation = new Basicinformation();
        return basicinformationDao.findAll(basicinformation);
    }

    /**
     * 判断是否置顶前台或者是取消前台置顶
     * @param record
     * @param type
     * @return
     */
    public Basicinformation saveBasicinfromtemporary(Basicinformation record,int type){
        int i = 0 ;
        int delType = 0;
        //判断是否是取消置顶
        if (type != 0) {
            //修改鞋库信息表
            i = this.updatebasicinfrom(record);
            if (i > 0) {//修改成功，获取鞋子信息
                Basicinformation basicinformation = new Basicinformation();
                basicinformation.setId(record.getId());
                List<Basicinformation> basicinformationList = this.findAll(basicinformation);
                if (basicinformationList.size() > 0) {//赋值给置顶表存储
                    basicinformation = basicinformationList.get(0);
                    BasicinformationTemporary basicinformationTemporary = new BasicinformationTemporary();
                    basicinformationTemporary.setId(Integer.parseInt(basicinformation.getId()));
                    basicinformationTemporary.setBrand(basicinformation.getBrand());
                    basicinformationTemporary.setArtNo(basicinformation.getArtNo());
                    basicinformationTemporary.setName(basicinformation.getName());
                    basicinformationTemporary.setType(basicinformation.getType());
                    basicinformationTemporary.setChineselogo(basicinformation.getChineselogo());
                    basicinformationTemporary.setColores(basicinformation.getColores());
                    basicinformationTemporary.setCsaledate(basicinformation.getCsaledate());
                    if (basicinformation.getCofferprice() != null && !"".equals(basicinformation.getCofferprice())) {
                        basicinformationTemporary.setCofferprice(new BigDecimal(basicinformation.getCofferprice()));
                    }
                    basicinformationTemporary.setEsaledate(basicinformation.getEsaledate());
                    if (basicinformation.getEofferprice() != null && !"".equals(basicinformation.getEofferprice())) {
                        basicinformationTemporary.setEofferprice(new BigDecimal(basicinformation.getEofferprice()));
                    }
                    basicinformationTemporary.setImgUrl(basicinformation.getImgUrl());
                    basicinformationTemporary.setSmallImgUrl(basicinformation.getSmallImgUrl());
                    basicinformationTemporary.setState(basicinformation.getState());
                    //查询获取30天的销售量，查询淘宝数据
                    ResultData resultData = new ResultData();
                    resultData.setBasiciformationId(basicinformation.getId());
                    String startTime = DateUtils.dateToString(DateUtils.wantToLose(new Date(), 30), "yyyy-MM-dd") + " 00:00:00";
                    String endTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd") + " 23:59:59";
                    List<ResultData> resultDataList = resultDataDao.findResultDataTime(resultData, startTime, endTime);
                    if (resultDataList.size() > 0) {
                        BigDecimal transactionRecord = new BigDecimal("0");
                        for (ResultData resultDatas : resultDataList) {
                            if (resultDatas.getTransactionRecord() != null && !"".equals(resultDatas.getTransactionRecord())) {
                                transactionRecord = transactionRecord.add(new BigDecimal(resultDatas.getTransactionRecord()));
                            }
                        }
                        basicinformationTemporary.setTransactionRecord(transactionRecord.toString());
                    }
                    //最高出价和最低叫价
                    PricePeak pricePeak = new PricePeak();
                    pricePeak.setBasicinformationId(Integer.parseInt(basicinformation.getId()));
                    String pstartDate = DateUtils.dateToString(new Date(), "yyyy-MM-dd") + " 00:00:00";
                    List<PricePeak> pricePeakList = pricePeakDao.findByType(pricePeak, 1, 0, 1000, pstartDate, endTime);
                    if (pricePeakList.size() > 0) {
                        basicinformationTemporary.setHighestBid(pricePeakList.get(0).getHighestBid());
                    }
                    pricePeakList = pricePeakDao.findByType(pricePeak, 2, 0, 1000, pstartDate, endTime);
                    if (pricePeakList.size() > 0) {
                        basicinformationTemporary.setMinimumSellingPrice(pricePeakList.get(0).getMinimumSellingPrice());
                    }
                    //获取最大涨幅和涨跌百分比
                    BasicinformationRose basicinformationRose = new BasicinformationRose();
                    basicinformationRose.setBasicinformation_id(Integer.parseInt(basicinformation.getId()));
                    List<BasicinformationRose> basicinformationRoseList = basicinformationRoseDao.findNewRose(basicinformationRose, 0, 1000);
                    if (basicinformationRoseList.size() > 0) {
                        basicinformationTemporary.setChangeRange(basicinformationRoseList.get(0).getChange_range());
                        basicinformationTemporary.setPercentageChange(basicinformationRoseList.get(0).getPercentage_change());
                    }
                    i = basicinformationTemporaryDao.insert(basicinformationTemporary);
                }
            }
        }else {
            i = this.updatebasicinfrom(record);
            if (i > 0){
                BasicinformationTemporary basicinformationTemporary = new BasicinformationTemporary();
                basicinformationTemporary.setId(Integer.parseInt(record.getId()));
                List<BasicinformationTemporary> basicinformationTemporaryList = basicinformationTemporaryDao.findAll(basicinformationTemporary);
                if (basicinformationTemporaryList.size() > 0){
                    delType = basicinformationTemporaryList.get(0).getType();
                }
                i = basicinformationTemporaryDao.delete(Integer.parseInt(record.getId()));
            }
        }
        record = new Basicinformation();
        if (i > 0 && type != 0){
            BasicinformationTemporary basicinformationTemporary = new BasicinformationTemporary();
            basicinformationTemporary.setType(type);
            List<BasicinformationTemporary> basicinformationTemporaryList = basicinformationTemporaryDao.findAll(basicinformationTemporary);
            i = basicinformationTemporaryList.size();
            record.setType(i);
        }else {
            if (delType != 0){
                BasicinformationTemporary bas = new BasicinformationTemporary();
                bas.setType(delType);
                List<BasicinformationTemporary> basicinformationTemporaryList = basicinformationTemporaryDao.findAll(bas);
                i = basicinformationTemporaryList.size();
                record.setType(i);
            }else {
                record.setType(4);
            }
        }
        return record;
    }
}
