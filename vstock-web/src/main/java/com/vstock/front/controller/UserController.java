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

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
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
    public String sale(ModelMap model){
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

    //出价和叫价详情历史
    @RequestMapping("offerlist")
    public String offerlist(ModelMap model){
        Bid bid = new Bid();
        String type = request.getParameter("type");
        String pageNow = request.getParameter("pageNow");
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,pageNow);
        List<Bid> bidList = bidService.findAll(bid,page);
        model.addAttribute("page",page);
        model.addAttribute("type",type);
        model.addAttribute("bidList",bidList);
        return "/user/offerlist";
    }

    //购买和出售详情历史
    @RequestMapping("buysell")
    public String buysell(ModelMap model){
        Trade trade = new Trade();
        String type = request.getParameter("type");
        String pageNow = request.getParameter("pageNow");
        int totalCount = tradeService.findCount(trade);
        Page page = new Page(totalCount,pageNow);
        List<Trade> tradeList = tradeService.findAll(trade,page);
        model.addAttribute("page",page);
        model.addAttribute("type",type);
        model.addAttribute("tradeList",tradeList);
        return "/user/buysell";
    }

    //个人中心购买记录
    @RequestMapping("purchase")
    public String purchase(ModelMap model){
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

    //个人资料
    @RequestMapping("userInfo")
    public String userInfo(ModelMap model){
        User user = new User();
        user = userService.findById("1");
        model.addAttribute("user",user);
        return "/user/userInfo";
    }

    //我的资产
    @RequestMapping("userAssets")
    public String userAssets(){
        return "/user/userAssets";
    }

    @RequestMapping("addresschoice")
    public String addresschoice(){
        return "/user/comm/addresschoice";
    }
}
