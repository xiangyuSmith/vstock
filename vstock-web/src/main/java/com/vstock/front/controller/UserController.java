package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.util.MD5Util;
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
    UserAssetsService userAssetsService;

    @Autowired
    CityAddressService cityAddressService;

    private static Logger logger = Logger.getLogger(BidController.class);

    private static JSONObject staAdder = new JSONObject();

    @RequestMapping("index")
    public String testIndex(ModelMap model){
        String type = request.getParameter("type");
        model.put("urlType",type);
        return "/user/comm/leftmeun";
    }

    //个人中心出售记录
    @RequestMapping("sale")
    public String sale(ModelMap model){
        staAdder = cityAddressService.adderssAll();
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        String type = request.getParameter("type");
        Bid bid = new Bid();
        Trade trade = new Trade();
        bid.setUserId(Integer.parseInt(String.valueOf(suid)));
        trade.setSellerId(bid.getUserId());
        bid.setStatus(3);
        if (Integer.parseInt(type) == 0) {
            bid.setType(0);
        }else {
            bid.setType(1);
            trade.setStatus(0);
        }
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,"1");
        page.setPageSize(5);
        List<Bid> bidList = bidService.findBid(bid,page);
        List<Trade> tradeList = tradeService.findTrade(trade,page);
        List<Trade> statusList = tradeService.status();
        model.addAttribute("bidList",bidList);
        model.addAttribute("tradeList",tradeList);
        model.addAttribute("statusList",statusList);
        if (Integer.parseInt(type) == 0) {
            return "/user/saleRecord";
        }else {
            return "/user/purchaseRecords";
        }
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
//    @RequestMapping("purchase")
//    public String purchase(ModelMap model){
//        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
//        Bid bid = new Bid();
//        Trade trade = new Trade();
//        bid.setUserId(Integer.parseInt(String.valueOf(suid)));
//        trade.setBuyersId(bid.getUserId());
//        bid.setType(1);
//        bid.setStatus(3);
//        trade.setStatus(0);
//        int totalCount = bidService.findCount(bid);
//        Page page = new Page(totalCount,"1");
//        page.setPageSize(5);
//        List<Bid> bidList = bidService.findBid(bid,page);
//        List<Trade> tradeList = tradeService.findTrade(trade,page);
//        List<Trade> statusList = tradeService.status();
//        model.addAttribute("bidList",bidList);
//        model.addAttribute("tradeList",tradeList);
//        model.addAttribute("statusList",statusList);
//        return "/user/purchaseRecords";
//    }

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
    public String userAssets(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        UserAssets record = new UserAssets();
        record.setUserId(Integer.parseInt(String.valueOf(suid)));
        record.setStatus(0);
        List<UserAssets> userAssetsList = userAssetsService.findUserAssets(record);
        BasicinformationRose basicinformationRose = userAssetsService.findUserAssBasRose(record);
        model.put("basicinformationRose",basicinformationRose);
        model.put("userAssetsList",userAssetsList);
        return "/user/userAssets";
    }

    @RequestMapping("addresschoice")
    public String addresschoice(){
        return "/user/comm/addresschoice";
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseBody
    public void address(HttpServletResponse response){
//        JSONObject jsonObject = cityAddressService.adderssAll();
        try {
            response.getWriter().print(staAdder);
        }catch (Exception ex){
            System.out.print(ex.getMessage());
        }
    }

    @RequestMapping("hchar")
    @ResponseBody
    public Map<String,Object> hchar(){
        Map<String,Object> param = new HashMap<String,Object>();
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        UserAssets record = new UserAssets();
        record.setUserId(Integer.parseInt(String.valueOf(suid)));
        record.setStatus(0);
        String hchar = userAssetsService.hChar(record);
        String[] strChar = hchar.split(":");
        param.put("hchar",strChar[0]);
        param.put("moneyChar",strChar[1]);
        return param;
    }

    @RequestMapping("saveAdder")
    @ResponseBody
    public Map<String,Object> saveAdder(){
        Map<String,Object> param = new HashMap<String,Object>();
        String localArea = request.getParameter("localArea");
        String detailedAddress = request.getParameter("detailedAddress");
        String consigneeName = request.getParameter("consigneeName");
        String phoneNumber = request.getParameter("phoneNumber");
        String landlineNumber = request.getParameter("landlineNumber");
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        int retCode = userAddressService.saveAdder(localArea,detailedAddress,consigneeName,phoneNumber,landlineNumber,suid.toString(),type,status,id);
        param.put("retCode",retCode);
        return param;
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public Map<String,Object> updatePassword(){
        Map<String,Object> param = new HashMap<String,Object>();
        User record = new User();
        String mobile = getParam("mobile","");
        String password = getParam("password","");
        User user = userService.findUser(mobile);
        record.setId(user.getId());
        record.setPassword(MD5Util.getMD5String(user.getSalt() + password + User.REG_MD5_CODE));
        int i =userService.update(record);
        param.put("retCode",i);
        return param;
    }

    @RequestMapping("verification")
    @ResponseBody
    public Map<String,Object> verification(){
        Map<String,Object> param = new HashMap<String,Object>();
        String sendSmsCode = getParam("sendSmsCode","");
        Object o = WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE);
        String u = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE));
        if(sendSmsCode.equals(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE)))){
            param.put("retCode",1);
        }
        return param;
    }

}
