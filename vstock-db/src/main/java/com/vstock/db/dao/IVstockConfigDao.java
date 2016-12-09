package com.vstock.db.dao;

import com.vstock.db.entity.VstockConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IVstockConfigDao {

    List<VstockConfig> findAll();
}
