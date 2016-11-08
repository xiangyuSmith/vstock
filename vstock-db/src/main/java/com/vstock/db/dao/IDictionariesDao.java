package com.vstock.db.dao;

import com.vstock.db.entity.Dictionaries;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangyu on 2016/5/11.
 */

public interface IDictionariesDao {

    Dictionaries findById(@Param(value = "dicId") String dicId);

    int update(Dictionaries dictionaries);

    List<Dictionaries> findDcList(@Param(value = "dictionaries") Dictionaries dictionaries, @Param(value = "status") String status, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<Dictionaries> getCount(@Param(value = "dictionaries") Dictionaries dictionaries, @Param(value = "status") String status);

    List<Dictionaries> findDcListAll(@Param("dictionaries") Dictionaries dictionaries);

}
