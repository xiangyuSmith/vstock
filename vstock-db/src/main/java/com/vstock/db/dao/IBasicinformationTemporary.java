package com.vstock.db.dao;

import com.vstock.db.entity.BasicinformationTemporary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/7/12.
 */
public interface IBasicinformationTemporary {

    List<BasicinformationTemporary> findAll(@Param(value = "obj") BasicinformationTemporary basicinformationTemporary);

    int insert(BasicinformationTemporary basicinformationTemporary);

    int update(@Param(value = "obj")BasicinformationTemporary basicinformationTemporary);

    int delete(@Param(value = "id") int id);

}
