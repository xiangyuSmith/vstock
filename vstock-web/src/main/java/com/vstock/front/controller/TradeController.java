package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.front.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("/trade")
public class TradeController extends BaseController{

    @Autowired
    BidService bidService;

    @RequestMapping
    @ResponseBody
    public ResultModel index(){
        ResultModel resultModel = new ResultModel();
        setLastPage(0,1);
        double amount = Double.valueOf(getParam("amount", "0L"));
        String size = getParam("size");
        Bid bid = new Bid();
        bid.setBidMoney(new BigDecimal(amount));
        bid.setBftSize(size);
        Bid bid1 = bidService.findByBid(bid,lagePage);
        if(bid1 == null){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("未找到叫价记录");
            return resultModel;
        }
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

}
