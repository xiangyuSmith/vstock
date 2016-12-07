package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ModelAndView;
import com.vstock.ext.util.Page;
import com.vstock.front.service.BidService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by administor on 2016/12/6.
 */
@Controller
@RequestMapping("/bid")
public class BidController extends BaseController{

    @Autowired
    BidService bidService;

    private static Logger logger = Logger.getLogger(BidController.class);

    @RequestMapping("index")
    public String testIndex(){
        return "/user/comm/leftmeun";
    }

    @RequestMapping("sale")
    public String testSale(ModelMap model){
        Bid bid = new Bid();
//        ModelAndView modelAndView = new ModelAndView();
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,"1");
        List<Bid> bidList = bidService.findAll(bid,page);
//        modelAndView.put("bidList",bidList);
        model.addAttribute("bidList",bidList);
        return "/user/saleRecord";
    }
}
