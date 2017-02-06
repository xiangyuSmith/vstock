package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.MD5Util;
import com.vstock.ext.util.Page;
import com.vstock.front.service.*;
import com.vstock.server.util.StatusUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
    UserAccountService userAccountService;

    @Autowired
    ExpressService expressService;

    @Autowired
    LogisticsInformationService logisticsInformationService;

    private static Logger logger = Logger.getLogger(BidController.class);

    @RequestMapping("index")
    public String testIndex(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        String type = request.getParameter("type");
        UserAccount record = userAccountService.findAccountByUid(suid.toString());
        model.put("userAccount",record);
        model.put("urlType",type);
        return "/user/comm/leftmeun";
    }

    //个人中心出售记录
    @RequestMapping("sale")
    public String sale(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        Express express = new Express();
        if (suid == null || "".equals(suid)){
            return "/index/index";
        }
        String type = request.getParameter("type");
        Bid bid = new Bid();
        Trade trade = new Trade();
        bid.setUserId(Integer.parseInt(String.valueOf(suid)));
        if ("0".equals(type)) {
            bid.setType("0");
            trade.setSellerId(bid.getUserId());
        }else {
            bid.setType("1");
            trade.setStatus(50);
            trade.setBuyersId(bid.getUserId());
        }
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,"1");
        page.setPageSize(5);
        List<Bid> bidList = bidService.findBidStatus(bid,page);
        List<Trade> tradeList = tradeService.findAllWeb(trade,page);
        List<Trade> statusList = StatusUtil.tradeStatus();
        List<Bid> bidStatus = StatusUtil.bidStatus();
        List<Express> expressList = expressService.findAll(express);
        model.addAttribute("expressList",expressList);
        model.addAttribute("bidStatus",bidStatus);
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
        if (suid == null || "".equals(suid)){
            return "/index/index";
        }
        Bid bid = new Bid();
        String type = request.getParameter("type");
        bid.setUserId(Integer.parseInt(String.valueOf(suid)));
        if ("0".equals(type)) {
            bid.setType("0");
        }else {
            bid.setType("1");
        }
        String pageNow = request.getParameter("pageNow");
        int totalCount = bidService.findCount(bid);
        Page page = new Page(totalCount,pageNow);
        List<Bid> bidList = bidService.findBidStatus(bid,page);
        List<Bid> bidStatus = StatusUtil.bidStatus();
        model.addAttribute("bidStatus",bidStatus);
        model.addAttribute("page",page);
        model.addAttribute("type",type);
        model.addAttribute("bidList",bidList);
        return "/user/offerlist";
    }

    //购买和出售详情历史
    @RequestMapping("buysell")
    public String buysell(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        if (suid == null || "".equals(suid)){
            return "/index/index";
        }
        Trade trade = new Trade();
        Express express = new Express();
        String type = request.getParameter("type");
        if ("0".equals(type)) {
            trade.setSellerId(Integer.parseInt(String.valueOf(suid)));
            trade.setStatus(50);
        }else {
            trade.setBuyersId(Integer.parseInt(String.valueOf(suid)));
        }
        String pageNow = request.getParameter("pageNow");
        int totalCount = tradeService.findCountWeb(trade);
        Page page = new Page(totalCount,pageNow);
        List<Trade> tradeList = tradeService.findAllWeb(trade,page);
        List<Trade> statusList = tradeService.status();
        List<Express> expressList = expressService.findAll(express);
        model.addAttribute("expressList",expressList);
        model.addAttribute("statusList",statusList);
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
        if (suid == null || "".equals(suid)){
            return "/index/index";
        }
        String type = getParam("type","");
        UserAddress record = new UserAddress();
        User user = userService.findById(String.valueOf(suid));
        record.setUserId(Integer.parseInt(user.getId()));
        int startPos = 5;
        List<UserAddress> userAddressesList = userAddressService.findAllUserAddress(record,startPos,type);
        model.put("userAddressesList",userAddressesList);
        model.put("type",type);
        model.addAttribute("user",user);
        return "/user/userInfo";
    }

    //我的资产
    @RequestMapping("userAssets")
    public String userAssets(ModelMap model){
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        if (suid == null || "".equals(suid)){
            return "/index/index";
        }
        UserAssets record = new UserAssets();
        record.setUserId(Integer.parseInt(String.valueOf(suid)));
        record.setStatus(0);
        List<UserAssets> userAssetsList = userAssetsService.findUserAssets(record);
        for (int i = 0; i < userAssetsList.size(); i++){
            userAssetsList.get(i).getBasicinformationRose().
                    setChange_range(userAssetsList.get(i).getBasicinformationRose().
                            getCurrent_market_value().divide(userAssetsList.get(i).getMoney()));
        }
        BasicinformationRose basicinformationRose = userAssetsService.findUserAssBasRose(record);
        model.put("basicinformationRose",basicinformationRose);
        model.put("userAssetsList",userAssetsList);
        return "/user/userAssets";
    }

    @RequestMapping("userAssetsList")
    public String userAssetsList(ModelMap model){
        String pageNow = request.getParameter("pageNow");
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        if (suid == null || "".equals(suid)){
            return "/index/index";
        }
        UserAssets record = new UserAssets();
        record.setUserId(Integer.parseInt(String.valueOf(suid)));
        record.setStatus(0);
        int totalCount = userAssetsService.findCount(record);
        Page page = new Page(totalCount,pageNow);
        List<UserAssets> userAssetsList = userAssetsService.findAll(record,page);
        for (int i = 0; i < userAssetsList.size(); i++){
            userAssetsList.get(i).getBasicinformationRose().
                    setChange_range(userAssetsList.get(i).getBasicinformationRose().
                            getCurrent_market_value().divide(userAssetsList.get(i).getMoney()));
        }
        model.put("userAssetsList",userAssetsList);
        model.put("page",page);
        return "/user/userAssetsList";
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
            response.getWriter().print(VstockConfigService.getJsonAdder());
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
        if(!"".equals(hchar)){
            String[] strChar = hchar.split(":");
            param.put("hchar",strChar[0]);
            param.put("moneyChar",strChar[1]);
        }
        return param;
    }

    @RequestMapping("saveAdder")
    @ResponseBody
    public Map<String,Object> saveAdder(){
        Map<String,Object> param = new HashMap<String,Object>();
        setLastPage(0,1);
        String localArea = request.getParameter("localArea");
        String detailedAddress = request.getParameter("detailedAddress");
        String consigneeName = request.getParameter("consigneeName");
        String phoneNumber = request.getParameter("phoneNumber");
        String landlineNumber = request.getParameter("landlineNumber");
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Object suid = WebUtils.getSessionAttribute(request, User.SESSION_USER_ID);
        UserAddress u = new UserAddress();
        u.setUserId(Integer.parseInt(String.valueOf(suid)));
        List<UserAddress> list = userAddressService.findAll(u,lagePage);
        if(list.size() <= 0){
            //设置默认地址
            type = "1";
        }
        UserAddress userAddress = userAddressService.saveAdder(localArea,detailedAddress,consigneeName,phoneNumber,landlineNumber,suid.toString(),type,status,id);
        if(userAddress != null){
            param.put("retCode",1);
            param.put("obj",userAddress);
            return param;
        }
        return param;
    }

    @RequestMapping("getFindByAddress")
    @ResponseBody
    public ResultModel getFindByAddress(){
        ResultModel resultModel = new ResultModel();
        UserAddress recode = new UserAddress();
        Integer userAddressId = getParamToInt("userAddressId");
        String type = "1";
        if(userAddressId == null){
            return resultModel;
        }
        UserAddress record = new UserAddress();
        record.setId(userAddressId);
        int startPos = 1;
        List<UserAddress> userAddressesList = userAddressService.findAllUserAddress(record,startPos,type);
        if(userAddressesList.size() == 0){
            return resultModel;
        }
        recode = userAddressesList.get(0);
        resultModel.setData(recode);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
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

    @RequestMapping("cardIdentify")
    @ResponseBody
    public ResultModel cardIdentify(){
        ResultModel resultModel = new ResultModel();
        String suid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        UserAccount userAccount = userAccountService.findAccountByUid(suid);
        if(userAccount != null){
            if(UserAccount.ACCOUNT_TYPE_SUCCESS.equals(userAccount.getStatus())){
                resultModel.setRetCode(resultModel.RET_OK);
                return resultModel;
            }
        }
        return resultModel;
    }

    @RequestMapping("uploadUserProfile")
    @ResponseBody
    public ResultModel uploadUserProfile(){
        ResultModel resultModel = new ResultModel();
        String alipayAccount = getParam("alipayAccount");
        String uname = getParam("uname");
        String identifyNo = getParam("identifyNo");
        String suid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        UserAccount userAccount = new UserAccount();
        MultipartRequest multipartRequest = (MultipartRequest) request;
        MultipartFile identify_img_front = multipartRequest.getFile("identify_img_front");
        MultipartFile identify_img_back = multipartRequest.getFile("identify_img_back");
        MultipartFile identify_img_handheld = multipartRequest.getFile("identify_img_handheld");
        String identify_img_handheldUrl = userAccountService.uploadFile(identify_img_handheld,suid);
        //调用合一道接口验证身份信息
        String  resultJson = userAccountService.httphyd(uname,identifyNo,identify_img_handheldUrl);
        JSONObject jsonObject = new JSONObject(resultJson);
        int result = 0;
        try{
            result = Integer.parseInt(jsonObject.get("result").toString());
        }catch (Exception e){
            logger.warn("hyd sign fail...");
        }
        if(result == 1){
            double similarity = Double.parseDouble(jsonObject.get("similarity").toString());
            if(similarity>60){
                if(true){
                    userAccount.setUserId(suid);
                    userAccount.setAlipay_account(alipayAccount);
                    userAccount.setUname(uname);
                    userAccount.setIdentify_no(identifyNo);
                    userAccount.setIdentify_img_front(userAccountService.uploadFile(identify_img_front,suid));
                    userAccount.setIdentify_img_back(userAccountService.uploadFile(identify_img_back,suid));
                    userAccount.setIdentify_img_handheld(identify_img_handheldUrl);
                    userAccount.setUpdate_time(DateUtils.dateToString(new Date()));
                    userAccount.setStatus(userAccount.ACCOUNT_TYPE_SUCCESS);
                    int addRet = userAccountService.insert(userAccount);
                    resultModel.setRetCode(addRet);
                    return resultModel;
                }
            }
        }
        resultModel.setRetMsg(jsonObject.get("msg").toString());
        resultModel.setRetCode(0);
        return resultModel;
    }

    @RequestMapping("insertlogiscsIn")
    @ResponseBody
    public ResultModel insertlogiscsIn(LogisticsInformation record){
        ResultModel resultModel = new ResultModel();
        Trade trade = new Trade();
        String courierNumber = getParam("courierNumber","");
        trade.setCourierNumber(courierNumber);
        trade.setId(record.getTradeId());
        int i = tradeService.updateAll(trade);
        if (i > 0) {
            i = logisticsInformationService.save(record);
        }
        resultModel.setRetCode(i);
        return resultModel;
    }
}
