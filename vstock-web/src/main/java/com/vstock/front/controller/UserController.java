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
import java.math.RoundingMode;
import java.util.*;

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

    private static Logger logger = Logger.getLogger(UserController.class);

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
            trade.setNotStatus("52");
            trade.setSellerId(bid.getUserId());
        }else {
            bid.setType("1");
            trade.setStatus(50);
            trade.setNotStatus("51");
            trade.setBuysaleType("1");
            trade.setBuyersId(bid.getUserId());
        }
        int totalCount = bidService.findWebCount(bid);
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
        int totalCount = bidService.findWebCount(bid);
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
            trade.setNotStatus("52");
        }else {
            trade.setBuyersId(Integer.parseInt(String.valueOf(suid)));
            trade.setNotStatus("51");
            trade.setBuysaleType("1");
        }
        String pageNow = request.getParameter("pageNow");
        int totalCount = tradeService.findCountWeb(trade);
        Page page = new Page(totalCount,pageNow);
        List<Trade> tradeList = tradeService.findAllWeb(trade,page);
        List<Trade> statusList = StatusUtil.tradeStatus();
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
        UserAccount userAccount = userAccountService.findAccountByUid(String.valueOf(suid));
        record.setUserId(Integer.parseInt(user.getId()));
        int startPos = 5;
        List<UserAddress> userAddressesList = userAddressService.findAllUserAddress(record,startPos,type);
        model.put("userAddressesList",userAddressesList);
        model.put("type",type);
        model.addAttribute("user",user);
        model.addAttribute("userAccount",userAccount);
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
            if(userAssetsList.get(i).getBasicinformationRose().getCurrent_market_value().compareTo(userAssetsList.get(i).getMoney()) == 1){
                userAssetsList.get(i).getBasicinformationRose().setType(1);
            }else if (userAssetsList.get(i).getBasicinformationRose().getCurrent_market_value().compareTo(userAssetsList.get(i).getMoney()) == -1){
                userAssetsList.get(i).getBasicinformationRose().setType(0);
            }else {
                userAssetsList.get(i).getBasicinformationRose().setType(1);
            }
            userAssetsList.get(i).getBasicinformationRose().setChange_range(userAssetsList.get(i).getBasicinformationRose()
                    .getCurrent_market_value().divide(userAssetsList.get(i).getMoney(),4, RoundingMode.HALF_UP));
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
                            getCurrent_market_value().divide(userAssetsList.get(i).getMoney(),2, RoundingMode.HALF_UP));
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

    @RequestMapping("getAddress")
    @ResponseBody
    public ResultModel getAddress(){
        ResultModel resultModel = new ResultModel();
        Integer userAddressId = getParamToInt("userAddressId");
        if(userAddressId == null){
            return resultModel;
        }
        UserAddress record = new UserAddress();
        record.setId(userAddressId);
        UserAddress userAddresses = userAddressService.findType(record);
        if(userAddresses == null){
            return resultModel;
        }
        resultModel.setData(userAddresses);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
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
        if(!VstockConfigService.isChineseChar(uname)){
            resultModel.setRetMsg("您输入的姓名有误！");
            resultModel.setRetCode(0);
            return resultModel;
        }
        if(identify_img_front.getSize()>10000000 || identify_img_back.getSize()>10000000 || identify_img_handheld.getSize()>10000000){
            resultModel.setRetMsg("上传的身份证照片需小于10MB");
            resultModel.setRetCode(0);
            return resultModel;
        }
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
            double similarity = 0;
            try{
                similarity = Double.parseDouble(jsonObject.get("similarity").toString());
            }catch (Exception e){
                logger.warn("card & Photo matching fail...");
            }
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
            }else{
                resultModel.setRetMsg("身份证照片与本人信息不匹配");
            }
        }else{
            if(result == 2){
                resultModel.setRetMsg("认证结果:不一致");
            }else if(result == 3){
                resultModel.setRetMsg("认证结果:库中无此号，请到户籍所在地进行核实");
            }else{
                resultModel.setRetMsg(jsonObject.get("errorMessage").toString());
            }
        }
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

    @RequestMapping("sendSmsCode")
    @ResponseBody
    public ResultModel sendSmsCode(){
        ResultModel resultModel = new ResultModel();
        String sendSmsCode = getParam("sendSmsCode","");
        if(!sendSmsCode.equals(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE)))){
            resultModel.setRetMsg("验证码错误");
        }else {
            resultModel.setRetCode(ResultModel.RET_OK);
        }
        return resultModel;
    }

    @RequestMapping("bindphone")
    @ResponseBody
    public ResultModel bindphone(){
        ResultModel resultModel = new ResultModel();
        String suid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        String mobile = request.getParameter("mobile");
        String sendSmsCode = getParam("sendSmsCode","");
        String sendSmsCodeT = getParam("sendSmsCodeT","");
        if(!sendSmsCode.equals(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_CODE)))){
            resultModel.setRetMsg("旧验证码错误");
            return resultModel;
        }
        if(!sendSmsCodeT.equals(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_SIGN_TCODE)))){
            resultModel.setRetMsg("新验证码错误");
            return resultModel;
        }
        User isuser = userService.findUser(mobile);
        if(isuser != null){
            resultModel.setRetMsg("手机号已存在");
            return resultModel;
        }
        User user = new User();
        user.setId(suid);
        user.setMobile(mobile);
        int i = userService.update(user);
        if (i > 0) {
            resultModel.setRetCode(ResultModel.RET_OK);
        }else {
            resultModel.setRetMsg("添加失败！");
        }
        return resultModel;
    }

    @RequestMapping("getExpress")
    @ResponseBody
    public List<List<Express>> getExpress(){
        LogisticsInformation logisticsInformation = new LogisticsInformation();
        List<List<Express>> expressListlist = new ArrayList<List<Express>>();
        String tradeId = getParam("tradeId","");
        String expresNum = getParam("expresNum","");
        if (tradeId != null && !"".equals(tradeId)) {
            logisticsInformation.setTradeId(Integer.parseInt(tradeId));
            List<LogisticsInformation> logisticsInformationList = logisticsInformationService.findAll(logisticsInformation);
            if (logisticsInformationList.size() > 0) {
                expressListlist = userService.obtainLogistics(logisticsInformationList.get(0).getCompanyName(), expresNum);
                Express record = new Express();
                record.setExpressName(logisticsInformationList.get(0).getCompanyName());
                record.setStatus(expresNum);
                List<Express> expressList = new ArrayList<Express>();
                expressList.add(record);
                expressListlist.add(expressList);
            }
        }
        return expressListlist;
    }
}
