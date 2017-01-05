package com.vstock.front.controller;

import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.BasicinformationRose;
import com.vstock.db.entity.Point;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.front.service.BasiciformationRoseService;
import com.vstock.front.service.BasicinformationService;
import com.vstock.front.service.ResultDataService;
import com.vstock.front.service.VstockConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

    private static Logger logger = Logger.getLogger(IndexController.class);

    @Autowired
    BasicinformationService basicinformationService;

    @Autowired
    BasiciformationRoseService basiciformationRoseService;

    @Autowired
    ResultDataService resultDataService;

    @RequestMapping
    public String index(ModelMap modelMap){
        List<Basicinformation> bList = basicinformationService.findByType(-1);
        Long bCount = basicinformationService.findCount();
        modelMap.addAttribute("bList",bList);
        modelMap.addAttribute("bCount",bCount);
        return "/index/index";
    }

    @RequestMapping("brandMarket")
    @ResponseBody
    public List<Point> brandMarket(){
        String brand = getParam("brand","");
        List<Point> jsonArray = VstockConfigService.getBrand(brand);
        return jsonArray;
    }

    @RequestMapping("timingBrandMarket")
    @ResponseBody
    public ResultModel timingBrandMarket(){
        ResultModel resultModel = new ResultModel();
        for (String brand : BasicinformationRose.brandStr) {
            List<Point> brad = resultDataService.brandMarket(brand);
            if (brad != null && !"".equals(brad)) {
                VstockConfigService.setBrandMap(brand,brad);
            }
        }
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

    @RequestMapping("overallIncrease")
    @ResponseBody
    public Map<String, Object> overallIncrease(){
        String brand = getParam("brand","");
        Map<String, Object> resultModel = VstockConfigService.getRoes(brand);
        return resultModel;
    }

    @RequestMapping("timingOverallIncrease")
    @ResponseBody
    public ResultModel timingOverallIncrease(){
        ResultModel resultModel = new ResultModel();
        for (String brand : BasicinformationRose.brandStr) {
            Map<String, Object> brad = basiciformationRoseService.roseDegree(brand, DateUtils.dateToString(new Date(),"yyyy-MM-dd"));
            if (brad != null && !"".equals(brad)) {
                VstockConfigService.setRoesMap(brand,brad);
            }
        }
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
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
