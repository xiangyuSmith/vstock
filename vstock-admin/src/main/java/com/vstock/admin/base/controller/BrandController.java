package com.vstock.admin.base.controller;

import com.vstock.admin.base.service.BrandService;
import com.vstock.ext.util.Page;
import com.vstock.db.entity.Brand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiangyu on 2016/7/18.
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    private static Logger logger = Logger.getLogger(BrandController.class);

    @Autowired
    BrandService brandService;

    /**
     * 品牌列表
     */
    @RequestMapping("brandList")
    public String brandList(Brand brand, HttpServletRequest request, ModelMap model){
        String pageNow = request.getParameter("pageNow");
        String brandName = request.getParameter("brandName");
        String linkAddress = request.getRequestURI();
        if (brandName != null && !"".equals(brandName)){
            linkAddress = linkAddress +"?brandName=" + brandName;
        }
        Long totalCount = brandService.getBrandCount(brand);
        Page page = new Page(totalCount.intValue(), pageNow);
        //获取品牌列表
        List<Brand> brandList = brandService.findList(brand,page);
        model.addAttribute("brandList",brandList);
        model.addAttribute("page", page);
        model.addAttribute("brandName",brandName);
        model.addAttribute("linkAddress", linkAddress);
        return "admin/brand/list";
    }

    /**
     * 新增品牌 & 修改品牌
     */
    @RequestMapping("insertBrand")
    @ResponseBody
    public Map<String,Object> insertBrand(HttpServletRequest request){
        Map<String,Object> params = new HashMap<String,Object>();
        String brandName = request.getParameter("brandName");
        String brandId = request.getParameter("brandId");
        int result = 0;
        if(brandId != null && !"".equals(brandId)){
            //执行修改 , 查询是否有该品牌
            Brand brand = brandService.findBrandById(brandId);
            if(brand != null){
                brand.setBrandName(brandName);
                result = brandService.update(brand);
                logger.info("success :　update brand success");
                params.put("result",result);
                return params;
            }else{
                logger.error("fail :　insert brand error");
                params.put("result",result);
                return params;
            }
        }else{
            //新增品牌
            Brand brand = new Brand();
            brand.setBrandName(brandName);
            result = brandService.inser(brand);
        }

        if(result == 1){
            logger.info("success ：insert brand success");
        }else{
            logger.error("fail :　insert brand error");
        }
        params.put("result",result);
        return params;
    }

    /**
     * 删除品牌数据
     */
    @RequestMapping("deleteBrand")
    @ResponseBody
    public Map<String,Object> deleteBrand(HttpServletRequest request){
        Map<String,Object> params = new HashMap<String,Object>();
        String brandId = request.getParameter("brandId");
        int result = brandService.deleteById(brandId);
        params.put("result",result);
        return params;
    }
}
