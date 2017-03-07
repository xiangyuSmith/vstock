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
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
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
            String bname = extra_common_param[6];
            String trade_no = getParam("trade_no");
            String buyer_email = getParam("buyer_email");
            String body = getParam("body");
            //TODO 校验 sign
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
            payment.setPayment_money(new BigDecimal(amount));
            payment.setPayment_explain(body);
            int payResult = paymentService.cteatePay(payment,VstockConfigService.getConfig(IVstockConfigService.PAY__BOGE_VSTOCK_MD5KEY));
            if(payResult == 0){
                logger.error("payment add fail ...");
            }
            PricePeak pricePeak = pricePeakService.getHighestAndlowest(basicinformationId,size, DateUtils.dateToString(new Date()),lagePage);
            int resultPeak = pricePeakService.isAmount(pricePeak,new BigDecimal(amount),basicinformationId,size,uid,type);
            int paymentId = payment.getId();
            Bid bidObj = new Bid();
            bidObj.setId(bid);
            bidObj.setPaymentId(paymentId);
            bidObj.setInvalidDate(DateUtils.dateToString(new Date()));
            bidObj.setStatus(String.valueOf(bidObj.STATUS_INIT));
            bidService.update(bidObj);
            if("0".equals(bname)){
                return "redirect:/user/index";
            }else{
                return "redirect:/detail?proName="+bname;
            }
        }
        return "/error";
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
        sParaTemp.put("extra_common_param", uid+"|"+type+"|"+basicinformationId+"|"+bid+"|"+size+"|"+amount+"|"+bname);
        sParaTemp.put("out_trade_no", String.valueOf(bid));
        sParaTemp.put("subject", String.valueOf("商品叫价"));
        sParaTemp.put("total_fee", String.valueOf(0.01));
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
            resultModel.setRetMsg("不能重复叫价");
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
