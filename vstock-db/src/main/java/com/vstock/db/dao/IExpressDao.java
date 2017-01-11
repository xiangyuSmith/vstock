package com.vstock.db.dao;

import com.vstock.db.entity.Express;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IExpressDao {

    //分页查询所有
    List<Express> findAll(@Param("obj") Express record);

    //查询所有总数
    int findCount(@Param("obj") Express record);

    //添加数据
    int insert(Express record);

    //修改数据
    int update(Express record);

}
