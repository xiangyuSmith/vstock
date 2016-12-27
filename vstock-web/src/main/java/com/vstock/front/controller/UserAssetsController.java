package com.vstock.front.controller;

import com.vstock.db.entity.UserAssets;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.front.service.UserAssetsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("/userAssets")
public class UserAssetsController extends BaseController {
    @Autowired
    UserAssetsService userAssetsService;

    private static Logger logger = Logger.getLogger(BidController.class);

    @RequestMapping("saveUserAssets")
    @ResponseBody
    public ResultModel saveUserAssets(){
        ResultModel resultModel = new ResultModel();
        UserAssets record = new UserAssets();
        int id = getParamToInt("id");
        int userId = getParamToInt("userId");
        int basicinformationId = getParamToInt("bId");
        String status = getParam("status");
        String userAssetsSize = getParam("size");
        double money = Double.valueOf(getParam("money"));
        record.setId(id);
        record.setUserId(userId);
        record.setStatus(Integer.parseInt(status));
        record.setBasicinformationId(basicinformationId);
        record.setUserAssetsSize(userAssetsSize);
        record.setMoney(BigDecimal.valueOf(money));
        int i = userAssetsService.save(record);
        resultModel.setRetCode(i);
        return resultModel;
    }
}
