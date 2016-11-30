package com.vstock.front.controller;

import com.vstock.ext.base.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    private static Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping
    public String index(){
        String sss = request.getParameter("sss");
        System.out.println(sss);
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

}
