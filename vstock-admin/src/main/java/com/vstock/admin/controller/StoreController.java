package com.vstock.admin.controller;

import com.vstock.admin.service.StockxStoreService;
import com.vstock.db.entity.StockxStore;
import com.vstock.ext.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xiangyu on 2016/7/18.
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    private static Logger logger = Logger.getLogger(StoreController.class);

    @Autowired
    StockxStoreService stockxStoreService;

    @RequestMapping("list")
    public String store(HttpServletRequest request, ModelMap model) {
        String pageNow = request.getParameter("pageNow");
        String linkAddress = request.getRequestURI();
        Long totalCount = stockxStoreService.getCount();
        Page page = new Page(totalCount.intValue(), pageNow);
        List<StockxStore> storeList = stockxStoreService.findListByPage(page);
        model.addAttribute("storeList", storeList);
        model.addAttribute("page", page);
        model.addAttribute("linkAddress", linkAddress);
        return "admin/storeAnalysis/storeBasicInformation";
    }
}
