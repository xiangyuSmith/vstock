package com.vstock.db.dao;

import com.vstock.db.entity.ResultDataFactory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/7/6.
 */
public interface IResultDataFactory {
    int insertresultData(ResultDataFactory resultDataFactory);

    int update(@Param(value = "productName") String productName, @Param(value = "productNameNew") String productNameNew);

    List<ResultDataFactory> findAll(@Param("obj")ResultDataFactory record, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    int findCount(@Param("obj")ResultDataFactory record, @Param("startTime") String startTime, @Param("endTime") String endTime);

    List<ResultDataFactory> findrdfAndBasiAll(@Param("obj")ResultDataFactory record, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    int findrdfAndBasiCount(@Param("obj")ResultDataFactory record, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
