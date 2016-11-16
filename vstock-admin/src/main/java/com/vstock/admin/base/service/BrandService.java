package com.vstock.admin.base.service;

import com.vstock.db.dao.IBrandDao;
import com.vstock.db.entity.Brand;
import com.vstock.ext.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiangyu on 2016/7/12.
 */
@Service
public class BrandService {

    @Autowired
    IBrandDao brandDao;

    public Long getBrandCount(Brand brand){
        return brandDao.getBrandCount(brand);
    }

    public List<Brand> findList(Brand brand,Page page){
        return brandDao.findList(brand,page.getStartPos(),page.getPageSize());
    }

    public int inser(Brand brand){
        return brandDao.insertBrand(brand);
    }

    public Brand findBrandById(String brandId){
        return brandDao.findBrandById(brandId);
    }

    public int update(Brand brand){
        return brandDao.updateBrand(brand);
    }

    public int deleteById(String brandId){
        return brandDao.deleteById(brandId);
    }

    public List<Brand> findAllList(Brand brand){ return brandDao.findAllList(brand);}

}
