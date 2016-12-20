package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Trade;
import com.vstock.db.entity.User;
import com.vstock.db.entity.UserAddress;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.util.Page;
import com.vstock.front.service.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    BidService bidService;

    @Autowired
    TradeService tradeService;

    @Autowired
    UserService userService;

    @Autowired
    UserAddressService userAddressService;

    @Autowired
    CityAddressService cityAddressService;

    private static Logger logger = Logger.getLogger(BidController.class);

    @RequestMapping("index")
    public String testIndex(){
        return "/user/comm/leftmeun";
    }

    //个人中心出售记录
    @RequestMapping("sale")
    public String sale(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        Bid bid = new Bid();
        bid.setUserId(Integer.parseInt(String.valueOf(suid)));
        bid.setType(0);
        bid.setStatus(3);
        Trade trade = new Trade();
        trade.setSellerId(bid.getUserId());
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
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        Bid bid = new Bid();
        String type = request.getParameter("type");
        bid.setUserId(Integer.parseInt(String.valueOf(suid)));
        bid.setStatus(3);
        if ("0".equals(type)) {
            bid.setType(0);
        }else {
            bid.setType(1);
        }
        String pageNow = request.getParameter("pageNow");
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,pageNow);
        List<Bid> bidList = bidService.findAndPricePeak(bid,page);
        model.addAttribute("page",page);
        model.addAttribute("type",type);
        model.addAttribute("bidList",bidList);
        return "/user/offerlist";
    }

    //购买和出售详情历史
    @RequestMapping("buysell")
    public String buysell(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        Trade trade = new Trade();
        String type = request.getParameter("type");
        if ("0".equals(type)) {
            trade.setSellerId(Integer.parseInt(String.valueOf(suid)));
        }else {
            trade.setBuyersId(Integer.parseInt(String.valueOf(suid)));
        }
        String pageNow = request.getParameter("pageNow");
        int totalCount = tradeService.findCount(trade);
        Page page = new Page(totalCount,pageNow);
        List<Trade> tradeList = tradeService.findAndBid(trade,page);
        model.addAttribute("page",page);
        model.addAttribute("type",type);
        model.addAttribute("tradeList",tradeList);
        return "/user/buysell";
    }

    //个人中心购买记录
    @RequestMapping("purchase")
    public String purchase(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        Bid bid = new Bid();
        bid.setUserId(Integer.parseInt(String.valueOf(suid)));
        bid.setType(1);
        bid.setStatus(3);
        Trade trade = new Trade();
        trade.setBuyersId(bid.getUserId());
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
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        UserAddress record = new UserAddress();
        User user = userService.findById(String.valueOf(suid));
        record.setUserId(Integer.parseInt(user.getId()));
        List<UserAddress> userAddressesList = userAddressService.findAllUserAddress(record);
        model.put("userAddressesList",userAddressesList);
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

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseBody
    public void address(HttpServletResponse response){
        JSONObject jsonObject = cityAddressService.adderssAll();
        try {
            response.getWriter().print(jsonObject);
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }
}
