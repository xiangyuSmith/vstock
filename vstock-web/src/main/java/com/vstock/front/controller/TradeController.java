package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Payment;
import com.vstock.db.entity.Trade;
import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.front.service.BidService;
import com.vstock.front.service.PaymentService;
import com.vstock.front.service.TradeService;
import com.vstock.front.service.VstockConfigService;
import com.vstock.front.service.interfaces.IVstockConfigService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/trade")
public class TradeController extends BaseController{

    @Autowired
    BidService bidService;
    @Autowired
    TradeService tradeService;
    @Autowired
    PaymentService paymentService;

    @RequestMapping
    @ResponseBody
    public ResultModel index(){
        //TODO 重复提交订单校验
        ResultModel resultModel = new ResultModel();
        setLastPage(0,1);
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        double amount = Double.valueOf(getParam("amount", "0"));
        double yunFee = Double.valueOf(getParam("yunFee", "0"));
        String size = getParam("size");
        int type = getParamToInt("type");
        int addressId = Integer.valueOf(getParam("addressId", "0"));
        Bid bid = new Bid();
        bid.setBidMoney(new BigDecimal(amount));
        bid.setBftSize(size);
        bid.setType(type);
        //TODO 运费待加入
//        bid.setBidFreight(new BigDecimal(yunFee));
        bid.setStatus(Bid.STATUS_INIT);
        Bid bid1 = bidService.findByBid(bid,lagePage);
        if(bid1 == null){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("未找到叫价记录");
            return resultModel;
        }
        //TODO 加入订单，关联买家叫价
        Date now = new Date();
        int status = type==0 ? Trade.TRADE_NOTIFIY_PAY : Trade.TRADE_NOTIFIY_PAY_BOND;
        Trade trade = new Trade(addressId,new BigDecimal(yunFee),size, DateUtils.dateToString(new Date()), DateUtils.dateToString(new Date()), status,
                new BigDecimal(amount), bid1.getBasicinformationId(), bid1.getId(), bid1.getUserId(), Integer.parseInt(uid), DateFormatUtils.format(now, "yyyyMMddHHmmss") + RandomStringUtils.randomNumeric(6));
        int tradeId = tradeService.createTradeOne(trade, VstockConfigService.getConfig(IVstockConfigService.TRADE__BOGE_VSTOCK_MD5KEY));
        if(tradeId == 0){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("服务器繁忙，请稍后再试");
            return resultModel;
        }
        resultModel.setRetCode(resultModel.RET_OK);
        resultModel.setData(tradeId);
        return resultModel;
    }

    @ResponseBody
    @RequestMapping("createTradePay")
    public ResultModel createTradePay() {
        ResultModel resultModel = new ResultModel();
        setLastPage(0,1);
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        int type = getParamToInt("type");
        int tradeId = getParamToInt("tradeId");
        double amount = Double.valueOf(getParam("amount", "0"));
        Payment payment = new Payment();
        payment.setPayment_user_id(Long.parseLong(uid));
        payment.setPayment_status(10);
        //TODO 默认状态暂定为成功
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
        int tradeStatus = tradeService.checkTradeStatus(tradeId,lagePage);
        if(tradeStatus == -1){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("服务器繁忙，请稍后再试");
            return resultModel;
        }
        Trade trade = new Trade();
        trade.setId(tradeId);
        trade.setStatus(tradeStatus);
        trade.setUpdateDate(DateUtils.dateToString(new Date()));
        tradeService.update(trade);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }
}
