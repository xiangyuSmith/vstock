package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Trade;
import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.util.Page;
import com.vstock.front.service.BidService;
import com.vstock.front.service.TradeService;
import com.vstock.front.service.UserService;
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

    @Autowired
    TradeService tradeService;

    @Autowired
    UserService userService;

    private static Logger logger = Logger.getLogger(BidController.class);

    @RequestMapping("index")
    public String testIndex(){
        return "/user/comm/leftmeun";
    }

    //个人中心出售记录
    @RequestMapping("sale")
    public String testSale(ModelMap model){
        Bid bid = new Bid();
        Trade trade = new Trade();
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,"1");
        List<Bid> bidList = bidService.findBid(bid,page);
        List<Trade> tradeList = tradeService.findTrade(trade,page);
        model.addAttribute("bidList",bidList);
        model.addAttribute("tradeList",tradeList);
        return "/user/saleRecord";
    }

    //个人中心购买记录
    @RequestMapping("purchase")
    public String testPurchase(ModelMap model){
        Bid bid = new Bid();
        Trade trade = new Trade();
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,"1");
        List<Bid> bidList = bidService.findBid(bid,page);
        List<Trade> tradeList = tradeService.findTrade(trade,page);
        model.addAttribute("bidList",bidList);
        model.addAttribute("tradeList",tradeList);
        return "/user/purchaseRecords";
    }

    @RequestMapping("userInfo")
    public String testUserInfo(ModelMap model){
        User user = new User();
        user = userService.findById("1");
        model.addAttribute("user",user);
        return "/user/userInfo";
    }
}
