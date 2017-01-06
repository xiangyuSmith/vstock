package com.vstock.db.dao;

import com.vstock.db.entity.BasicinformationRose;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IBasicinformationRoseDao {

    BasicinformationRose findRose(@Param("obj")BasicinformationRose basicinformationRose);

    List<BasicinformationRose> findAllDate(@Param("obj")BasicinformationRose record);

    int insert(BasicinformationRose record);

}
