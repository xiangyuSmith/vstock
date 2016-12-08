package com.vstock.db.dao;

import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.ResultData;
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

    List<Basicinformation> findrdfAndBasiAll(@Param("obj")ResultData record, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    List<Integer> findrdfAndBasiCount(@Param("obj")ResultData record, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
