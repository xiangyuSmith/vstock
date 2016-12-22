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
        double amount = Double.valueOf(getParam("amount", "0L"));
        String overdueTime = getParam("overdueTime");
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
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
        //TODO 叫价状态暂时默认为已生效
        int resultBid = bidService.createBid(bName,Integer.parseInt(uid),bId,size,amount,bidService.getOverDueTime(overdueTime)
                ,type,new BigDecimal(10),Bid.STATUS_INIT,DateUtils.dateToString(new Date()),VstockConfigService.getConfig(IVstockConfigService.BID_VSTOCK_MD5KEY));
        PricePeak pricePeak = pricePeakService.getHighestAndlowest(bId,size, DateUtils.dateToString(new Date()),lagePage);
        int resultPeak = pricePeakService.isAmount(pricePeak,new BigDecimal(amount),bId,size,uid,type);
        if(resultBid == 1 && resultPeak == 1){
            resultModel.setRetCode(resultModel.RET_OK);
        }
        return resultModel;
    }

    @RequestMapping("updateBid")
    @ResponseBody
    public Map<String,Object> updateBid(){
        Map<String,Object> param = new HashMap<String,Object>();
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String endDate = request.getParameter("endDate");
        String bidMoney = request.getParameter("bidMoney");
        int sgin = bidService.updateBid(id,status,endDate,bidMoney);
        param.put("sgin",sgin);
        return param;
    }
}
