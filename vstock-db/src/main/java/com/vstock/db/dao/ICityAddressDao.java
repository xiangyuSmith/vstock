package com.vstock.db.dao;

import com.vstock.db.entity.CityAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/12/7.
 */
public interface ICityAddressDao {
    //分页查询所有
    List<CityAddress> findAll(@Param("obj")CityAddress record);

    //查询所有总数
    int findCount(@Param("obj")CityAddress record);

    //添加数据
    int insert(CityAddress record);

}
