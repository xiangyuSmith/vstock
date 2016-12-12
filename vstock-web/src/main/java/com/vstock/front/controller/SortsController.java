package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.ext.base.BaseController;
import com.vstock.front.service.BasicinformationService;
import com.vstock.front.service.BidService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sorts")
public class SortsController extends BaseController{

    private static Logger logger = Logger.getLogger(SortsController.class);

    @Autowired
    BasicinformationService basicinformationService;

    @Autowired
    BidService bidService;

    @RequestMapping
    public String index(ModelMap modelMap){
        List<String> brandList = basicinformationService.getBrands();
        modelMap.addAttribute("brandList",brandList);
        return "/sorts/index";
    }

    @RequestMapping("list")
    public String list(ModelMap modelMap){
        List<Bid> bidList = bidService.findBidForSorts();
        modelMap.addAttribute("bidList",bidList);
        return "/sorts/list";
    }
}
