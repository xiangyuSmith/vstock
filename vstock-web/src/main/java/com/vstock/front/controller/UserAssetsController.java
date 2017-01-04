package com.vstock.front.controller;

import com.vstock.db.entity.User;
import com.vstock.db.entity.UserAssets;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.front.service.UserAssetsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

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
        Integer userId = Integer.parseInt(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID).toString());
        Integer id = getParamToInt("id");
        Integer basicinformationId = getParamToInt("bId");
        String purchaseDate = getParam("purchaseDate","");
        Integer status = getParamToInt("status");
        String userAssetsSize = getParam("size");
        double money = Double.valueOf(getParam("money"));
        record.setId(id);
        record.setUserId(userId);
        record.setStatus(status);
        record.setBasicinformationId(basicinformationId);
        record.setUserAssetsSize(userAssetsSize);
        record.setPurchaseDate(purchaseDate);
        record.setMoney(BigDecimal.valueOf(money));
        int i = userAssetsService.save(record);
        resultModel.setRetCode(i);
        return resultModel;
    }
}
