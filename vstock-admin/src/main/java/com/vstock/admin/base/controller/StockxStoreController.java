package com.vstock.admin.base.controller;

import com.vstock.db.entity.StockxStore;
import com.vstock.admin.base.service.StockxStoreService;
import com.vstock.ext.util.CookieTool;
import com.vstock.ext.util.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by administor on 2016/5/11.
 */
@Controller
@RequestMapping("/stockxStore")
public class StockxStoreController {

    private static Logger logger = Logger.getLogger(StockxStoreController.class);

    @Autowired
    StockxStoreService stockxStoreService;

    @RequestMapping("storeBasicInformation")
    public String storeBasicInformation(HttpServletRequest request){
        return "admin/storeBasicInformation";
    }

    @RequestMapping("storeBasicUrl")
    public String storeBasicUrl(HttpServletRequest request){
        return "admin/storeBasicUrl";
    }

    @RequestMapping("insert")
    @ResponseBody
    public Object  insert(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        int resultCount = 0;
        DateUtils dateUtils = new DateUtils();
        StockxStore stockxStore = new StockxStore();
        stockxStore.setName(request.getParameter("storeName"));
        stockxStore.setUrl(request.getParameter("storeUrl"));
        stockxStore.setPageNo(request.getParameter("storePageNo"));
        stockxStore.setBrand(request.getParameter("storeBrand"));
        stockxStore.setCreate_time(dateUtils.getCurrentTimeAsString());
        //获取当前用户
        Cookie cokLoginName = CookieTool.getCookieByName(request, "adminName");
        stockxStore.setCreate_user(cokLoginName.getValue());
        resultCount = stockxStoreService.insert(stockxStore);
        return resultCount;
    }
}
