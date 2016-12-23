package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.security.md.ToolMD5;
import com.vstock.front.service.BidService;
import com.vstock.front.service.PaymentService;
import com.vstock.front.service.PricePeakService;
import com.vstock.front.service.VstockConfigService;
import com.vstock.front.service.interfaces.IVstockConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bid")
public class BidController extends BaseController{

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
        int type = getParamToInt("type");
        int bId = getParamToInt("bId");
        double amount = Double.valueOf(getParam("amount", "0"));
        String overdueTime = getParam("overdueTime");
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        int resultBid = bidService.createBid(bName,Integer.parseInt(uid),bId,size,amount,bidService.getOverDueTime(overdueTime)
                ,type,new BigDecimal(10),Bid.STATUS_PENDING,DateUtils.dateToString(new Date()),VstockConfigService.getConfig(IVstockConfigService.BID_VSTOCK_MD5KEY));
        if(resultBid != 0){
            resultModel.setRetCode(resultModel.RET_OK);
        }
        resultModel.setData(resultBid);
        return resultModel;
    }

    @RequestMapping("createPay")
    @ResponseBody
    public ResultModel createPay(){
        ResultModel resultModel = new ResultModel();
        setLastPage(0,1);
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        int type = getParamToInt("type");
        int basicinformationId = getParamToInt("bId");
        int bid = getParamToInt("bid");
        String size = getParam("size");
        double amount = Double.valueOf(getParam("amount", "0"));
        //TODO 校验 sign
        //TODO 支付保证金，若成功，则叫价 （ 固定状态和数据，接第三方后更改 ）
        Payment payment = new Payment();
        payment.setPayment_user_id(Long.parseLong(uid));
        payment.setPayment_status(10);
        payment.setPayment_mode(Payment.PAY_SOURCE_ALIPAY);
        payment.setPayment_type(type);
        payment.setPayment_date(DateUtils.dateToString(new Date()));
        payment.setPayment_over_date(DateUtils.getNowdateAddmm());
        payment.setPayment_money(new BigDecimal(amount));
        payment.setPayment_explain("支付说明");
        int payResult = paymentService.cteatePay(payment,VstockConfigService.getConfig(IVstockConfigService.PAY__BOGE_VSTOCK_MD5KEY));
        if(payResult == 0){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("支付失败，请重新发起支付");
            return resultModel;
        }
        PricePeak pricePeak = pricePeakService.getHighestAndlowest(basicinformationId,size, DateUtils.dateToString(new Date()),lagePage);
        int resultPeak = pricePeakService.isAmount(pricePeak,new BigDecimal(amount),basicinformationId,size,uid,type);
        int paymentId = payment.getId();
        Bid bidObj = new Bid();
        bidObj.setId(bid);
        bidObj.setPaymentId(paymentId);
        bidObj.setInvalidDate(DateUtils.dateToString(new Date()));
        bidObj.setStatus(bidObj.STATUS_INIT);
        bidService.update(bidObj);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

    @RequestMapping("updateBid")
    @ResponseBody
    public Map<String,Object> updateBid(){
        Map<String,Object> param = new HashMap<String,Object>();
        String id = request.getParameter("id");
        String btfId = request.getParameter("btfId");
        String status = request.getParameter("status");
        String endDate = request.getParameter("endDate");
        String bidMoney = request.getParameter("bidMoney");
        String size = request.getParameter("size");
        int sgin = bidService.updateBid(id,btfId,status,size,endDate,bidMoney);
        param.put("sgin",sgin);
        return param;
    }
}
