package com.vstock.db.dao;

import com.vstock.db.entity.ResultData;
import com.vstock.db.entity.ResultDataFactory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/7/6.
 */
public interface IResultDataFactory {
    List<ResultData> findResultData(@Param("resultDataFactory") ResultDataFactory resultDataFactory, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);

    int insertresultData(ResultDataFactory resultDataFactory);

    List<ResultData> findResultDataAll(ResultDataFactory resultDataFactory);

    List<ResultData> findResultDataTime(@Param("resultDataFactory") ResultDataFactory resultDataFactory, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);

    Long findResultDataCount(@Param("resultDataFactory") ResultDataFactory resultDataFactory, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime);

    List<ResultData> findByProductName(@Param("productName") String productName, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    Long getStoreResultDataCount(@Param("productName") String productName);

    int update(@Param(value = "productName") String productName, @Param(value = "productNameNew") String productNameNew);
}
