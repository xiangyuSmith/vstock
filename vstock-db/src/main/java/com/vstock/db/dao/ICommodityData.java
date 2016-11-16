package com.vstock.db.dao;

import com.vstock.db.entity.CommodityData;
import com.vstock.db.entity.CommodityDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/5/12.
 */
public interface ICommodityData {

    int insertcommodityData(CommodityData record);

    int insertcommodityDetail(CommodityDetail commodityDetail);

    List<CommodityData> findCommodityDatalist();

    List<CommodityData> findByName(@Param("name") String name,@Param("girard") String girard);

    List<CommodityData> findCommodityData(@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    Long getCommodityCount(@Param("commodityData")CommodityData commodityData,@Param(value = "startTime") String startTime,@Param(value = "endTime") String endTime);

    Long getAllCount(CommodityData record);

    List<CommodityData> findAll(@Param("commodityData")CommodityData commodityData,@Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize,@Param(value = "startTime") String startTime,@Param(value = "endTime") String endTime);

    List<CommodityData> findCommodityDataAll(@Param("commodityData")CommodityData commodityData);
}
