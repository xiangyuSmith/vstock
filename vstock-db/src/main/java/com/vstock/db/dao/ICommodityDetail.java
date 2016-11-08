package com.vstock.db.dao;

import com.vstock.db.entity.CommodityDetail;
import com.vstock.db.entity.Dictionaries;
import com.vstock.db.entity.ResultData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/6/28.
 */
public interface ICommodityDetail {

    //公共查询方法
    List<CommodityDetail> findCommAll(CommodityDetail record);

    //添加字典数据
    int insertDic(Dictionaries dictionarie);

    //添加最终结果数据
    int saveResultData(ResultData resultData);

    //公共分页查询方法
    List<CommodityDetail> findPageAll(@Param("commodityDetail") CommodityDetail commodityDetail, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    //公共查询总记录数
    Long findAllCount(CommodityDetail record);

    //查询字典表数据
    List<Dictionaries> findDictionaries(@Param("commodityId") String commodityId);

    //带时间区间查询
    List<CommodityDetail> findAllComm(@Param("commodityDetail") CommodityDetail commodityDetail, @Param("createDateStart") String createDateStart, @Param("createDateEnd") String createDateEnd);
}
