package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Trade;
import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.front.service.BidService;
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
        Bid bid = new Bid();
        bid.setBidMoney(new BigDecimal(amount));
        bid.setBftSize(size);
        bid.setType(type);
        bid.setStatus(Bid.STATUS_INIT);
        Bid bid1 = bidService.findByBid(bid,lagePage);
        if(bid1 == null){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("未找到叫价记录");
            return resultModel;
        }
        //TODO 加入订单，关联买家叫价
        Date now = new Date();
        Trade trade = new Trade(new BigDecimal(yunFee),size, DateUtils.dateToString(new Date()), DateUtils.dateToString(new Date()), Trade.TRADE_NOTIFIY_PAY_BOND,
                new BigDecimal(amount), bid1.getBasicinformationId(), bid1.getId(), bid1.getUserId(), Integer.parseInt(uid), DateFormatUtils.format(now, "yyyyMMddHHmmss") + RandomStringUtils.randomNumeric(6));
        int trade_result = tradeService.createTradeOne(trade, VstockConfigService.getConfig(IVstockConfigService.TRADE__BOGE_VSTOCK_MD5KEY));
        if(trade_result == 0){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("服务器繁忙，请稍后再试");
            return resultModel;
        }
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

}
