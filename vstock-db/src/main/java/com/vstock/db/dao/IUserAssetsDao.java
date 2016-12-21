package com.vstock.db.dao;

import com.vstock.db.entity.UserAssets;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by administor on 2016/12/6.
 */
public interface IUserAssetsDao {

    //分页查询所有
    List<UserAssets> findAll(@Param("obj") UserAssets record, @Param(value = "startPos") Integer startPos, @Param("pageSize") Integer pageSize);

    //查询所有总数
    int findCount(@Param("obj") UserAssets record);

    //添加数据
    int insert(UserAssets record);

    //修改数据
    int update(@Param("status") int status, @Param("invalidDate") String invalidDate, @Param("id") Integer id);

    List<UserAssets> findBasicinformationRoseAll(@Param("obj") UserAssets record);

}
