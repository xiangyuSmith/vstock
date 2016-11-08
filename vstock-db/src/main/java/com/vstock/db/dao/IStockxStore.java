package com.vstock.db.dao;

import com.vstock.db.entity.StockxInfo;
import com.vstock.db.entity.StockxStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by administor on 2016/5/11.
 */
public interface IStockxStore {

    List<StockxStore> findListByPage(@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    List<StockxStore> findList();

    List<StockxInfo> findStockxInfo();

    int insertstockxStore(StockxStore stockxStore);

    Long getStockxStoreCount();

    int updateStockxStore(StockxStore stockxStore);
}
