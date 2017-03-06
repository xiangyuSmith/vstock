package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.MD5Util;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.front.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

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
    @Autowired
    PricePeakService pricePeakService;
    @Autowired
    UserService userService;

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
        //爆款推荐
        List<Basicinformation> baolist = basicinformationService.findByBao(1);
        //最低卖价 & 最高叫价
        List<Basicinformation> miniMoneyList = basicinformationService.findByType(2);
        List<Basicinformation> heightMoneyList = basicinformationService.findByType(3);
        List<Basicinformation> bigRoseList = basicinformationService.findByType(4);
        PricePeak p = new PricePeak();
        p.setStatus(0);
        for(int i = 0;i < heightMoneyList.size();i++){
            p.setBasicinformationId(Integer.parseInt(heightMoneyList.get(i).getId()));
            List<PricePeak> prHight = pricePeakService.findByType(p,1,lagePage);
            p.setBasicinformationId(Integer.parseInt(miniMoneyList.get(i).getId()));
            List<PricePeak> prMini = pricePeakService.findByType(p,2,lagePage);
            if(prHight.size() > 0){
                heightMoneyList.get(i).setCofferprice(prHight.get(0).getHighestBid().doubleValue());
            }
            if(prMini.size() > 0){
                miniMoneyList.get(i).setCofferprice(prMini.get(0).getMinimumSellingPrice().doubleValue());
            }
        }
        //最大涨幅
        BasicinformationRose b = new BasicinformationRose();
        for(int i = 0;i < bigRoseList.size();i++){
            b.setBasicinformation_id(Integer.parseInt(bigRoseList.get(i).getId()));
            b.setCreate_date(ToolDateTime.format(ToolDateTime.getDate(),ToolDateTime.pattern_ymd));
            List<BasicinformationRose> brose = basiciformationRoseService.findNewRose(b,lagePage);
            if(brose.size() > 0){
                bigRoseList.get(i).setCofferprice(brose.get(0).getCurrent_market_value().doubleValue());
                bigRoseList.get(i).setCofferprices(brose.get(0).getPercentage_change().doubleValue()*100);
            }
        }
        Long bCount = basicinformationService.findCount();
        modelMap.addAttribute("bList",bList);
        modelMap.addAttribute("baolist",baolist);
        modelMap.addAttribute("sellBidList",sellBidList);
        modelMap.addAttribute("buyBidList",buyBidList);
        modelMap.addAttribute("heightMoneyList",heightMoneyList);
        modelMap.addAttribute("miniMoneyList",miniMoneyList);
        modelMap.addAttribute("bigRoseList",bigRoseList);
        modelMap.addAttribute("bCount",bCount);
        return "/index/index";
    }

    @RequestMapping("problem")
    public String problem(){
        return "/problem/index";
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

    @RequestMapping("updatePassword")
    @ResponseBody
    public ResultModel updatePassword(){
        ResultModel resultModel = new ResultModel();
        User record = new User();
        String password = getParam("password","");
        String sendSmsCode = getParam("sendSmsCode","");
        String mobile = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_MOBILE));
        if(sendSmsCode.equals(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE)))){
            if(!"".equals(mobile) && mobile != null && !"".equals(password) && !"".equals(sendSmsCode)){
                User user = userService.findUser(mobile);
                if(user != null){
                    record.setId(user.getId());
                    record.setPassword(MD5Util.getMD5String(user.getSalt() + password + User.REG_MD5_CODE));
                    int i = userService.update(record);
                    if(i == 1){
                        resultModel.setRetCode(resultModel.RET_OK);
                    }else{
                        resultModel.setRetMsg("请求超时");
                    }
                }else{
                    resultModel.setRetMsg("手机号码不存在");
                }
            }else{
                resultModel.setRetMsg("请求超时");
            }
        }else{
            resultModel.setRetMsg("验证码错误，请重新输入");
        }
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
