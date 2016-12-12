package com.vstock.front.controller;

import com.vstock.db.entity.Basicinformation;
import com.vstock.ext.base.BaseController;
import com.vstock.front.service.BasicinformationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    private static Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    BasicinformationService basicinformationService;

    @RequestMapping
    public String index(ModelMap modelMap){
        List<Basicinformation> bList = basicinformationService.findByType(-1);
        Long bCount = basicinformationService.findCount();
        modelMap.addAttribute("bList",bList);
        modelMap.addAttribute("bCount",bCount);
        return "/index/index";
    }

    @RequestMapping("test")
    public String test(){
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

    @RequestMapping("testloginwindow")
    public String testloginwindow(){
        return "/common/popup/loginwindow";
    }

    @RequestMapping("testbidwindow")
    public String testbidwindow(){
        return "/common/popup/bidwindow";
    }

    @RequestMapping("testdetailedlistwindow")
    public String testdetailedlistwindow(){return "/common/popup/detailedlistwindow";}

    @RequestMapping("testsalelistwindow")
    public String testsalelistwindow(){return "/common/popup/salelistwindow";}

    @RequestMapping("modaltest")
    public String modaltest(){return "/common/popup/modaltest";}

    @RequestMapping("offerlist")
    public String offerlist(){return "/user/offerlist";}
}
