package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.front.service.BidService;
import com.vstock.front.service.PaymentService;
import com.vstock.front.service.PricePeakService;
import com.vstock.front.service.VstockConfigService;
import com.vstock.front.service.interfaces.IVstockConfigService;
import com.vstock.server.alipay.config.AlipayConfig;
import com.vstock.server.alipay.util.AlipayNotify;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/bid")
public class BidController extends BaseController{

    private static Logger logger = Logger.getLogger(BidController.class);

    @Autowired
    BidService bidService;
    @Autowired
    PricePeakService pricePeakService;
    @Autowired
    PaymentService paymentService;

    final BigDecimal bidMoney = new BigDecimal(10).setScale(2,BigDecimal.ROUND_HALF_UP);

    @RequestMapping
    @ResponseBody
    public ResultModel index(){
        ResultModel resultModel = new ResultModel();
        setLastPage(0,1);
        String bName = getParam("bname");
        String size = Basicinformation.isContainsSizes(getParam("size"));
        String type = getParam("type");
        int bId = getParamToInt("bId");
        double amount = Double.valueOf(getParam("amount", "0"));
        String overdueTime = getParam("overdueTime");
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        logger.info("发起叫价.当前叫价:"+uid+",叫价时间:"+DateUtils.dateToString(new Date()));
        int resultBid = bidService.createBid(bName,Integer.parseInt(uid),bId,size,amount,overdueTime
                ,type,new BigDecimal(10),String.valueOf(Bid.STATUS_PENDING),DateUtils.dateToString(new Date()),VstockConfigService.getConfig(IVstockConfigService.BID_VSTOCK_MD5KEY));
        if(resultBid != 0){
            resultModel.setRetCode(resultModel.RET_OK);
        }
        resultModel.setData(resultBid);
        return resultModel;
    }

    @RequestMapping("createPay")
    public String createPay() throws Exception {
        //获取支付宝GET过来反馈信息
        Map requestParams = request.getParameterMap();
        Map<String,String> params = bidService.eachMap(requestParams);
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        if(verify_result){
            setLastPage(0,1);
            String[] extra_common_param = getParam("extra_common_param").split("\\|");
            String bname = extra_common_param[6];
            String isUserHome = extra_common_param[7];
            if("1".equals(isUserHome)){
                return "redirect:/user/index";
            }else{
                return "redirect:/detail?proName="+bname;
            }
        }
        return "/error";
    }

    @RequestMapping("returnBid")
    @ResponseBody
    public String returnBid(){
        //获取支付宝GET过来反馈信息
        Map requestParams = request.getParameterMap();
        Map<String,String> params = bidService.eachMap(requestParams);
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        if(verify_result){
            setLastPage(0,1);
            String[] extra_common_param = getParam("extra_common_param").split("\\|");
            String uid = extra_common_param[0];
            int type = Integer.parseInt(extra_common_param[1]);
            int basicinformationId = Integer.parseInt(extra_common_param[2]);
            int bid = Integer.parseInt(extra_common_param[3]);
            String size = extra_common_param[4];
            double amount = Double.parseDouble(extra_common_param[5]);
            String trade_no = getParam("trade_no");
            String buyer_email = getParam("buyer_email");
            String body = getParam("body");
            logger.info("叫价支付宝发起回调...");
            //校验是否重复回调
            Payment p = new Payment();
            p.setPayment_number(trade_no);
            Payment paymentFind = paymentService.findByTrade(p);
//            Bid bFind = new Bid();
//            bFind.setId(bid);
//            bFind = bidService.findbid(bFind);
//            if(String.valueOf(bFind.STATUS_PENDING).equals(bFind.getStatus())){
            logger.info("当前用户:"+uid+"，  time:"+DateUtils.dateToString(new Date()));
            if(paymentFind == null){
                //TODO 支付保证金，若成功，则叫价 （ 固定状态和数据，接第三方后更改 ）
                Payment payment = new Payment();
                payment.setPayment_user_id(Long.parseLong(uid));
                payment.setPayment_status(10);
                payment.setPayment_alipay_name(buyer_email);
                payment.setPayment_number(trade_no);
                payment.setPayment_mode(Payment.PAY_SOURCE_ALIPAY);
                payment.setPayment_type(type);
                payment.setPayment_date(DateUtils.dateToString(new Date()));
                payment.setPayment_over_date(DateUtils.getNowdateAddmm());
                payment.setPayment_money(bidMoney);
                payment.setPayment_explain(body);
                int payResult = paymentService.cteatePay(payment,VstockConfigService.getConfig(IVstockConfigService.PAY__BOGE_VSTOCK_MD5KEY));
                logger.info("当前用户:"+uid+"，支付-payment状态:"+payResult+"，payment编号:"+payment.getId());
                if(payResult != 1){
                    logger.info("bid payment add fail ...");
                    return "fail";
                }else {
                    //添加成功
                    if (payment.getId() == -1){//返回ID为-1重新查询获取ID
                        logger.info("|---------------------------------------------------------------------|");
                        logger.info("|添加支付是否成功返回值："+payResult+"|");
                        logger.info("|---------------------------------------------------------------------|");
                        logger.info("|添加支付是否成功返回对象ID："+payment.getId()+"|");
                        logger.info("|---------------------------------------------------------------------|");
                        logger.info("|添加支付是否成功返回对象中支付宝订单："+payment.getPayment_number()+"|");
                        logger.info("|---------------------------------------------------------------------|");
                        Payment paymentO = new Payment();
                        paymentO.setPayment_number(payment.getPayment_number());
                        payment = paymentService.findByTrade(paymentO);
                    }
                    PricePeak pricePeak = pricePeakService.getHighestAndlowest(basicinformationId,size, DateUtils.dateToString(new Date()),lagePage);
                    int resultPeak = pricePeakService.isAmount(pricePeak,new BigDecimal(amount),basicinformationId,size,uid,type);
                    int paymentId = payment.getId();
                    Bid bidObj = new Bid();
                    bidObj.setId(bid);
                    bidObj.setPaymentId(paymentId);
                    bidObj.setBidDate(DateUtils.dateToString(new Date()));
                    bidObj.setStatus(String.valueOf(bidObj.STATUS_INIT));
                    logger.info("|---------------------------------------------------------------------|");
                    logger.info("bidObj.setPaymentId(paymentId);" + paymentId);
                    logger.info("|---------------------------------------------------------------------|");
                    int bidStatus = bidService.update(bidObj);
                    logger.info("叫价支付,叫价人:"+bidObj.getUserId()+",叫价支付时间:"+DateUtils.dateToString(new Date())+",叫价状态:"+bidStatus);
                    return "success";
                }
            }else{
                logger.info("叫价 - 支付宝重复回调，叫价单号 : " + bid);
                return "success";
            }
        }else{
            return "fail";
        }
    }

    @RequestMapping("createPayAlipay")
    public String createPayAlipay(ModelMap modelMap){
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        int type = getParamToInt("type");
        int basicinformationId = getParamToInt("bId");
        int bid = getParamToInt("bid");
        String size = getParam("size");
        double amount = Double.valueOf(getParam("amount", "0"));
        String bname = getParam("bname");
        String isUserHome = getParam("isUserHome");
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        sParaTemp.put("extra_common_param", uid+"|"+type+"|"+basicinformationId+"|"+bid+"|"+size+"|"+amount+"|"+bname+"|"+isUserHome);
        sParaTemp.put("out_trade_no", "bid_"+System.currentTimeMillis()+String.valueOf(bid));
        sParaTemp.put("subject", String.valueOf("商品叫价") + bname + ",尺码:" + size + "码");
        sParaTemp.put("total_fee", String.valueOf(bidMoney));
        sParaTemp.put("body", "描述");
        modelMap.addAttribute("sParaTemp",sParaTemp);
        return "/common/alipay/alipayapi";
    }

    @RequestMapping("updateBid")
    @ResponseBody
    public Map<String,Object> updateBid(){
        Map<String,Object> param = new HashMap<String,Object>();
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String btfId = request.getParameter("btfId");
        String bidDate = request.getParameter("bidDate");
        String status = request.getParameter("status");
        String endDate = request.getParameter("endDate");
        String bidMoney = request.getParameter("bidMoney");
        String size = request.getParameter("size");
        int sgin = bidService.updateBid(id,btfId,status,size,endDate,bidMoney,bidDate);
        if (sgin > 0){
            Page page = new Page(10,"1");
            pricePeakService.save(page,bidMoney,btfId,size,uid.toString(),type);
        }
        param.put("sgin",sgin);
        return param;
    }

    @RequestMapping("ischeck")
    @ResponseBody
    public ResultModel ischeck(){
        ResultModel resultModel = new ResultModel();
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        setLastPage(0,1);
//        double amount = Double.valueOf(getParam("amount", "0"));
        String size = getParam("size");
        String type = getParam("type");
        int basicinfortionId = getParamToInt("bId");
        Bid bid = new Bid();
//        bid.setBidMoney(new BigDecimal(amount));
        bid.setBftSize(size);
        bid.setUserId(Integer.parseInt(uid));
        bid.setType(type);
        bid.setBasicinformationId(basicinfortionId);
        bid.setStatus(String.valueOf(Bid.STATUS_INIT));
        Bid bid1 = bidService.findByBid(bid,lagePage);
        if(bid1 != null){
            resultModel.setRetCode(bid1.getId());
            resultModel.setRetMsg(bid1.getBidMoney().toString());
        }
        return resultModel;
    }

    @RequestMapping("isbid")
    @ResponseBody
    public ResultModel isbid(){
        ResultModel resultModel = new ResultModel();
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        String type = getParam("type");
        String size = getParam("bftSize");
        int bfzId = getParamToInt("basicinformationId");
        Bid record = new Bid();
        record.setStatus("10");
        record.setUserId(Integer.parseInt(uid));
        record.setBftSize(size);
        record.setType(type);
        record.setBasicinformationId(bfzId);
        int i  = bidService.findCount(record);
        if(i > 0){
            resultModel.setRetCode(3);
            resultModel.setRetMsg("请到个人中心修改叫价金额");
            return resultModel;
        }
        record.setStatus("0");
        List<Bid> jList  = bidService.findAllBid(record);
        if(jList.size() > 0){
            resultModel.setRetCode(2);
            resultModel.setRetMsg("此款宝贝，您已有一笔未支付的叫价记录，是否需要付款");
            resultModel.setData(jList.get(0).getId());
            return resultModel;
        }
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

    @RequestMapping("checkMoneyTips")
    @ResponseBody
    public ResultModel checkMoneyTips(){
        ResultModel resultModel = new ResultModel();
        Integer type =  getParamToInt("type");
        Integer bid = getParamToInt("bid");
        String size = getParam("size","");
        double amount = Double.valueOf(getParam("amount", "0"));
        BigDecimal money = new BigDecimal(amount);
        int tips_number = bidService.checkMoney(money,size,bid,type);
        resultModel.setData(tips_number);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

    @RequestMapping("peakPriceJobBid")
    @ResponseBody
    public ResultModel peakPriceJobBid(){
        ResultModel resultModel = new ResultModel();
        bidService.peakPriceJobBid();
        return resultModel;
    }
}
