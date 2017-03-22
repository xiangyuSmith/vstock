package com.vstock.db.dao;

import com.vstock.db.entity.Dictionaries;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangyu on 2016/5/11.
 */

public interface IDictionariesDao {

    Dictionaries findById(@Param(value = "dicId") String dicId);

    List<Dictionaries> findByCommodityId(@Param(value = "commodityId") String commodityId);

    int update(Dictionaries dictionaries);

    int insertdictionaries(Dictionaries dictionaries);

    List<Dictionaries> findDcList(@Param(value = "dictionaries") Dictionaries dictionaries,@Param(value = "status") String status,@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<Dictionaries> getCount(@Param(value = "dictionaries") Dictionaries dictionaries,@Param(value = "status") String status);

    List<Dictionaries> findDcListAll(@Param("dictionaries") Dictionaries dictionaries);

    List<Dictionaries> findAll(@Param(value = "dictionaries") Dictionaries dictionaries,@Param(value = "stockxName") String stockxName,@Param(value = "commodityName") String commodityName,@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize,@Param("datetimeStart")String datetimeStart , @Param("datetimeEnd")String datetimeEnd);

    int findCount(@Param("dictionaries") Dictionaries dictionaries,@Param("stockxName") String stockxName,@Param("commodityName")String commodityName,@Param("datetimeStart")String datetimeStart , @Param("datetimeEnd")String datetimeEnd);
}
