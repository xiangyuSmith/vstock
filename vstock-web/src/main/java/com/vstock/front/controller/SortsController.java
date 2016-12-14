package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ModelAndView;
import com.vstock.front.service.BasicinformationService;
import com.vstock.front.service.BidService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        String priceStart = "";
        String priceEnd = "";
        String size = request.getParameter("size");
        String price = request.getParameter("price");
        String year = request.getParameter("year");
        String brand = request.getParameter("brand");
        if(!"".equals(price) && price != null){
            priceStart = price.split("-")[0];
            priceEnd = price.split("-")[1];
        }
        List<Bid> bidList = bidService.findBidForSorts(size,year,brand,priceStart,priceEnd);
        modelMap.addAttribute("bidList",bidList);
        return "/sorts/list";
    }

    @RequestMapping("bidTips")
    @ResponseBody
    public ModelAndView bidTips(){
        ModelAndView modelAndView = new ModelAndView();
        request.getParameter("");
        return modelAndView;
    }
}
