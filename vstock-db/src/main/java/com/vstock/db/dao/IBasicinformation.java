package com.vstock.db.dao;

import com.vstock.db.entity.Basicinformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/7/12.
 */
public interface IBasicinformation {
    List<Basicinformation> findLimitAll(@Param(value = "basicinformation")Basicinformation basicinformation,
                                        @Param(value = "startPos") Integer startPos,
                                        @Param(value = "pageSize") Integer pageSize,
                                        @Param(value = "startCsaledate") String startCsaledate,
                                        @Param(value = "endCsaledate") String endCsaledate,
                                        @Param(value = "startEsaledate") String startEsaledate,
                                        @Param(value = "endEsaledate") String endEsaledate,
                                        @Param(value = "startCreatetime") String startCreatetime,
                                        @Param(value = "endCreatetime") String endCreatetime);
    Long findLimitCount(@Param(value = "basicinformation")Basicinformation basicinformation,
                        @Param(value = "startCsaledate") String startCsaledate,
                        @Param(value = "endCsaledate") String endCsaledate,
                        @Param(value = "startEsaledate") String startEsaledate,
                        @Param(value = "endEsaledate") String endEsaledate,
                        @Param(value = "startCreatetime") String startCreatetime,
                        @Param(value = "endCreatetime") String endCreatetime);

    Basicinformation find(@Param("basicinformation")Basicinformation basicinformation);

    int insert(Basicinformation basicinformation);

    int update(@Param(value = "basicinformation")Basicinformation basicinformation,@Param(value = "id") String id);

    int delete(@Param(value = "id") String id);

    int updateState(@Param(value = "state") String state,@Param(value = "id") String id);

    List<Basicinformation> findAll(@Param(value = "basicinformation")Basicinformation basicinformation);

    List<Basicinformation> findByType(@Param(value = "type") int type);

    Long findCount();

    List<Basicinformation> findGirard(@Param(value = "productName")String productName);

    List<String> getBrands();

}
