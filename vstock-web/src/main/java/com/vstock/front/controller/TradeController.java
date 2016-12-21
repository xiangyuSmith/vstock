package com.vstock.front.controller;

import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.front.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trade")
public class TradeController extends BaseController{

    @Autowired
    BidService bidService;

    @RequestMapping
    @ResponseBody
    public ResultModel index(){
        ResultModel resultModel = new ResultModel();
        double amount = Double.valueOf(getParam("amount", "0L"));
        String size = getParam("size");

        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

}
