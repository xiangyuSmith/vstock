package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.ConstUtil;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.MD5Util;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.front.service.*;
import com.vstock.server.alipay.service.AlipayRefund;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.util.*;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

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

    public List<ResultData>  removeDuplicate(List<ResultData> list)   {
        for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   {
            for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   {
                if  (list.get(j).getStoreName().equals(list.get(i).getStoreName()))   {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    @RequestMapping
    public String index(ModelMap modelMap) {
        setLastPage(0, 10);
        Bid bid = new Bid();
        bid.setStatus("10");
        bid.setType("0");
        List<Bid> sellBidList = bidService.findNewAll(bid, lagePage);
        bid.setType("1");
        List<Bid> buyBidList = bidService.findNewAll(bid, lagePage);
        List<Basicinformation> baolist = basicinformationService.findByType(1);
        List<Basicinformation> miniMoneyList = basicinformationService.findByType(2);
        List<Basicinformation> heightMoneyList = basicinformationService.findByType(3);
        List<Basicinformation> bigRoseList = basicinformationService.findByType(4);
        List<Basicinformation> bList = basicinformationService.findByType(5);
        for (int i = 0; i < heightMoneyList.size(); i++) {
            PricePeak prHight = pricePeakService.getHighestAndlowest(Integer.parseInt(heightMoneyList.get(i).getId()),"", 1, lagePage);
            PricePeak prMini = pricePeakService.getHighestAndlowest(Integer.parseInt(miniMoneyList.get(i).getId()),"", 2, lagePage);
            if (prHight != null) {
                heightMoneyList.get(i).setHighestBid(prHight.getHighestBid().doubleValue());
            }
            if (prMini != null) {
                miniMoneyList.get(i).setMinimumBid(prMini.getMinimumSellingPrice().doubleValue());
            }
        }
        ResultData r = new ResultData();
        int transactionCount;
        for (Basicinformation b : baolist) {
            transactionCount = 0;
            r.setBasiciformationId(b.getId());
            List<ResultData> resultDataList = resultDataService.findResultDataAll(r);
            resultDataList = removeDuplicate(resultDataList);

            if(resultDataList.size() > 0){
                for (ResultData resultdata : resultDataList) {
                    if(resultdata.getTransactionRecord() != null && !"".equals(resultdata.getTransactionRecord())){
                        transactionCount = transactionCount + Integer.parseInt(resultdata.getTransactionRecord());
                    }
                }
                resultDataList.get(0).setTransactionRecord(String.valueOf(transactionCount));
                b.setResultData(resultDataList.get(0));
            }else{
                ResultData rd = new ResultData();
                rd.setTransactionRecord(String.valueOf(transactionCount));
                b.setResultData(rd);
            }
        }
        //最大涨幅
        BasicinformationRose b = new BasicinformationRose();
        for (int i = 0; i < bigRoseList.size(); i++) {
            b.setBasicinformation_id(Integer.parseInt(bigRoseList.get(i).getId()));
            b.setCreate_date(ToolDateTime.format(ToolDateTime.getDate(), ToolDateTime.pattern_ymd));
            List<BasicinformationRose> brose = basiciformationRoseService.findNewRose(b, lagePage);
            if (brose.size() > 0) {
                bigRoseList.get(i).setCofferprice(brose.get(0).getCurrent_market_value().doubleValue());
                bigRoseList.get(i).setCofferprices(brose.get(0).getPercentage_change().doubleValue() * 100);
            }
        }
//        Long bCount = basicinformationService.findCount();
        modelMap.addAttribute("bList", bList);
        modelMap.addAttribute("baolist", baolist);
        modelMap.addAttribute("sellBidList", sellBidList);
        modelMap.addAttribute("buyBidList", buyBidList);
        modelMap.addAttribute("heightMoneyList", heightMoneyList);
        modelMap.addAttribute("miniMoneyList", miniMoneyList);
        modelMap.addAttribute("bigRoseList", bigRoseList);
//        modelMap.addAttribute("bCount", bCount);
        return "/index/index";
    }

    @RequestMapping("problem")
    public String problem() {
        return "/problem/index";
    }

    @RequestMapping("noviceGuide")
    public String noviceGuide() {
        return "/problem/noviceindex";
    }

    @RequestMapping("getNewBid")
    @ResponseBody
    public ResultModel getNewBid() {
        ResultModel resultModel = new ResultModel();
        Map<String, List<Bid>> params = new HashMap<String, List<Bid>>();
        setLastPage(0, 10);
        Bid bid = new Bid();
        bid.setStatus("10");
        bid.setType("0");
        List<Bid> sellBidList = bidService.findNewAll(bid, lagePage);
        bid.setType("1");
        List<Bid> buyBidList = bidService.findNewAll(bid, lagePage);
        params.put("sellBidList", sellBidList);
        params.put("buyBidList", buyBidList);
        resultModel.setData(params);
        return resultModel;
    }

    @RequestMapping("brandMarket")
    @ResponseBody
    public List<Point> brandMarket() {
        String brand = getParam("brand", "");
        List<Point> jsonArray = VstockConfigService.getBrand(brand);
        return jsonArray;
    }

    @RequestMapping("overallIncrease")
    @ResponseBody
    public Map<String, Object> overallIncrease() {
        String brand = getParam("brand", "");
        Map<String, Object> resultModel = VstockConfigService.getRoes(brand);
        return resultModel;
    }

    @RequestMapping("isBindMobile")
    @ResponseBody
    public ResultModel isBindMobile() {
        ResultModel resultModel = new ResultModel();
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        User user = userService.findById(uid);
        if(user.getMobile() == null || "".equals(user.getMobile())){
            resultModel.setRetCode(resultModel.RET_OK);
        }
        return resultModel;
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public ResultModel updatePassword() {
        ResultModel resultModel = new ResultModel();
        User record = new User();
        String password = getParam("password", "");
        String sendSmsCode = getParam("sendSmsCode", "");
        String nowTime = request.getParameter("timestamp");
        String mobile = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_MOBILE));
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        if (sendSmsCode.equals(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE)))) {
            if (!"".equals(mobile) && mobile != null && !"".equals(password) && !"".equals(sendSmsCode)) {
                User user = userService.findUser(mobile);
                int i = 0;
                if (user != null) {
                    if(!"".equals(uid)){
                        //支付宝登录用户 - 绑定手机 - 手机号已存在
                        resultModel.setRetMsg("");
                        return resultModel;
                    }else{
                        record.setId(user.getId());
                        record.setPassword(MD5Util.getMD5String(user.getSalt() + password + User.REG_MD5_CODE));
                        record.setUpdate_time(DateUtils.dateToString(new Date()));
                        i = userService.update(record);
                        logger.info("忘记密码找回密码，状态：" + i);
                    }
                } else {
                    //没有根据当前手机号查看用户，则为忘记密码 或 支付宝登录用户
                    if(!"".equals(uid)){
                        //支付宝登录用户 - 绑定手机
                        String salt = MD5Util.getSha(mobile + password + nowTime);
                        record.setId(uid);
                        record.setMobile(mobile);
                        record.setSalt(salt);
                        record.setPassword(MD5Util.getMD5String(salt + password + User.REG_MD5_CODE));
                        record.setUpdate_time(DateUtils.dateToString(new Date()));
                        i = userService.update(record);
                        logger.info("用户ID"+uid+"，支付宝绑定手机号，状态：" + i);
                    }else{
                        //忘记密码 - 手机不存在
                        resultModel.setRetMsg("手机号码不存在");
                        logger.info("忘记密码，手机号不存在 ...");
                    }
                }
                if (i == 1) {
                    resultModel.setRetCode(resultModel.RET_OK);
                } else {
                    resultModel.setRetMsg("请求超时");
                }
            } else {
                resultModel.setRetMsg("请求超时");
            }
        } else {
            resultModel.setRetMsg("验证码错误，请重新输入");
        }
        return resultModel;
    }

    @RequestMapping("test")
    public String test(ModelMap model) {
        // 设置/获取
        spyMemcachedManager.set("zlex", "set/get" , 36000);
        System.out.println(spyMemcachedManager.get("zlex"));
        // 替换
        spyMemcachedManager.replace("zlex", "replace" , 36000);
        System.out.println(spyMemcachedManager.get("zlex"));
        // 移除
        spyMemcachedManager.delete("zlex");
        System.out.println(spyMemcachedManager.get("zlex"));
        return "/index/test";
    }

    @RequestMapping("testIndex")
    public String testIndex() {
        return "/index/test";
    }

    @RequestMapping("testSale")
    public String testSale() {
        return "/user/saleRecord";
    }

    @RequestMapping("testPurchase")
    public String testPurchase() {
        return "/user/purchaseRecords";
    }

    @RequestMapping("testUserInfo")
    public String testUserInfo() {
        return "/user/userInfo";
    }

    @RequestMapping("testUserAssets")
    public String testUserAssets() {
        return "/user/userAssets";
    }

    @RequestMapping("testbidwindow")
    public String testbidwindow() {
        return "/common/popup/bidwindow";
    }

    @RequestMapping("testdetailedlistwindow")
    public String testdetailedlistwindow() {
        return "/common/popup/detailedlistwindow";
    }

    @RequestMapping("modaltest")
    public String modaltest() {
        return "/common/popup/modaltest";
    }

    @RequestMapping("offerlist")
    public String offerlist() {
        return "/user/offerlist";
    }
}
