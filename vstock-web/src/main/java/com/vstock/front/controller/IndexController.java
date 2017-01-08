package com.vstock.front.controller;

import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Point;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.front.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    private static Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    BasicinformationService basicinformationService;
    @Autowired
    BidService bidService;
    @Autowired
    BasiciformationRoseService basiciformationRoseService;

    @Autowired
    ResultDataService resultDataService;

    @RequestMapping
    public String index(ModelMap modelMap){
        setLastPage(0,10);
        Bid bid = new Bid();
        bid.setStatus("10");
        bid.setType("0");
        List<Bid> sellBidList = bidService.findNewAll(bid,lagePage);
        bid.setType("1");
        List<Bid> buyBidList = bidService.findNewAll(bid,lagePage);
        List<Basicinformation> bList = basicinformationService.findByType(-1);
        Long bCount = basicinformationService.findCount();
        modelMap.addAttribute("bList",bList);
        modelMap.addAttribute("sellBidList",sellBidList);
        modelMap.addAttribute("buyBidList",buyBidList);
        modelMap.addAttribute("bCount",bCount);
        return "/index/index";
    }

    @RequestMapping("getNewBid")
    @ResponseBody
    public ResultModel getNewBid(){
        ResultModel resultModel = new ResultModel();
        Map<String,List<Bid>> params = new HashMap<String,List<Bid>>();
        setLastPage(0,10);
        Bid bid = new Bid();
        bid.setStatus("10");
        bid.setType("0");
        List<Bid> sellBidList = bidService.findNewAll(bid,lagePage);
        bid.setType("1");
        List<Bid> buyBidList = bidService.findNewAll(bid,lagePage);
        params.put("sellBidList",sellBidList);
        params.put("buyBidList",buyBidList);
        resultModel.setData(params);
        return resultModel;
    }

    @RequestMapping("brandMarket")
    @ResponseBody
    public List<Point> brandMarket(){
        String brand = getParam("brand","");
        List<Point> jsonArray = VstockConfigService.getBrand(brand);
        return jsonArray;
    }

    @RequestMapping("overallIncrease")
    @ResponseBody
    public Map<String, Object> overallIncrease(){
        String brand = getParam("brand","");
        Map<String, Object> resultModel = VstockConfigService.getRoes(brand);
        return resultModel;
    }

    @RequestMapping("test")
    public String test(){
        resultDataService.insertRose();
        return "/index/test";
    }

    @RequestMapping("testIndex")
    public String testIndex(){
        return "/user/comm/leftmeun";
    }

    @RequestMapping("testSale")
    public String testSale(){
        return "/user/saleRecord";
    }

    @RequestMapping("testPurchase")
    public String testPurchase(){
        return "/user/purchaseRecords";
    }

    @RequestMapping("testUserInfo")
    public String testUserInfo(){
        return "/user/userInfo";
    }

    @RequestMapping("testUserAssets")
    public String testUserAssets(){
        return "/user/userAssets";
    }

    @RequestMapping("testbidwindow")
    public String testbidwindow(){
        return "/common/popup/bidwindow";
    }

    @RequestMapping("testdetailedlistwindow")
    public String testdetailedlistwindow(){return "/common/popup/detailedlistwindow";}

    @RequestMapping("modaltest")
    public String modaltest(){return "/common/popup/modaltest";}

    @RequestMapping("offerlist")
    public String offerlist(){return "/user/offerlist";}
}
