package com.vstock.db.dao;

import com.vstock.db.entity.ResultData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/7/6.
 */
public interface IResultData {
    List<ResultData> findResultData(@Param("resultData")ResultData resultData, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);

    int insertresultData(ResultData resultData);

    List<ResultData> findResultDataAll(ResultData resultData);

    List<ResultData> findResultDataTime(@Param("resultData")ResultData resultData, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);

    Long findResultDataCount(@Param("resultData")ResultData resultData,@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);

    List<ResultData> findByProductName(@Param("productName") String productName,@Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    Long getStoreResultDataCount(@Param("productName") String productName);

    int update(@Param(value = "productName") String productName, @Param(value = "productNameNew") String productNameNew);
}
