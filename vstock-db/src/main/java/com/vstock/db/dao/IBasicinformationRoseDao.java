package com.vstock.db.dao;

import com.vstock.db.entity.BasicinformationRose;
import org.apache.ibatis.annotations.Param;

public interface IBasicinformationRoseDao {

    BasicinformationRose findRose(@Param("obj")BasicinformationRose basicinformationRose);
}