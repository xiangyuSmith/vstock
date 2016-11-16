package com.vstock.db.dao;

import com.vstock.db.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiangyu on 2016/7/12.
 */
public interface IBrandDao {

    Long getBrandCount(@Param(value = "brand")Brand brand);

    List<Brand> findList(@Param(value = "brand")Brand brand,@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    int insertBrand(@Param(value = "brand")Brand brand);

    Brand findBrandById(@Param(value = "brandId")String brandId);

    int updateBrand(@Param(value = "brand")Brand brand);

    int deleteById(@Param(value = "brandId")String brandId);

    List<Brand> findAllList(@Param(value = "brand")Brand brand);
}
