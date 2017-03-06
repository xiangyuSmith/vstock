package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.ToolDateTime;
import com.vstock.front.service.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/detail")
public class DetailController extends BaseController{

    @Autowired
    BasicinformationService basicinformationService;
    @Autowired
    BasiciformationRoseService basiciformationRoseService;
    @Autowired
    BidService bidService;
    @Autowired
    PricePeakService pricePeakService;
    @Autowired
    TradeService tradeService;
    @Autowired
    UserAddressService userAddressService;

    @RequestMapping
    public String index(@RequestParam String proName, ModelMap modelMap){
        String size = Basicinformation.isContainsSizes(request.getParameter("size"));
        Basicinformation basicinformation = new Basicinformation();
        basicinformation.setName(proName);
        basicinformation = basicinformationService.findObj(basicinformation);
        setLastPage(0,1);
        if(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID)!=null){
            int suid = Integer.parseInt(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID)));
            UserAddress record = new UserAddress();
            record.setUserId(suid);
            int startPos = 3;
            String type = "0";
            List<UserAddress> userAddressesList = userAddressService.findAllUserAddress(record,startPos,type);
            modelMap.put("userAddressesList",userAddressesList);
        }
        int bid = Integer.parseInt(basicinformation.getId());
        Trade trade = tradeService.getLastTrade(bid,size ,lagePage);
        Map<String,Object> resParams = basicinformationService.getPricesTrend(bid,size,trade);
        //TODO 传递尺码时根据尺码查询最高最低价，不传尺码时查询最高最低价并获取对应尺码
        PricePeak pricePeak1 = pricePeakService.getHighestAndlowest(bid,size,1,lagePage);
        PricePeak pricePeak2 = pricePeakService.getHighestAndlowest(bid,size,2,lagePage);
        Bid sell_bid1 = bidService.getHightAndMinPrice(bid,"0",1,lagePage);
        Bid sell_bid2 = bidService.getHightAndMinPrice(bid,"0",2,lagePage);
        Bid buyer_bid1 = bidService.getHightAndMinPrice(bid,"1",1,lagePage);
        Bid buyer_bid2 = bidService.getHightAndMinPrice(bid,"1",2,lagePage);
        modelMap.addAttribute("resParams",resParams);
        modelMap.addAttribute("pricePeak1",pricePeak1);
        modelMap.addAttribute("pricePeak2",pricePeak2);
        modelMap.addAttribute("sell_bid1",sell_bid1);
        modelMap.addAttribute("sell_bid2",sell_bid2);
        modelMap.addAttribute("buyer_bid1",buyer_bid1);
        modelMap.addAttribute("buyer_bid2",buyer_bid2);
        modelMap.addAttribute("trade",trade);
        modelMap.addAttribute("basicinformation",basicinformation);
        modelMap.addAttribute("size",size);
        return "/detail/index";
    }

    @RequestMapping("getPricePeak")
    @ResponseBody
    public ResultModel getPricePeak(){
        ResultModel resultModel = new ResultModel();
        BasicinformationRose record = new BasicinformationRose();
        Map<String,Object> params = new HashMap<String,Object>();
        String size = getParam("size");
        Integer bid = getParamToInt("bid");
        if (size != null && !"".equals(size)){
            record.setBasicinformation_size(size);
        }
        if (bid != null && !"".equals(bid)){
            record.setBasicinformation_id(bid);
        }
//        String startTime = ToolDateTime.format(DateUtils.wantToLose(new Date(),28),ToolDateTime.pattern_ymd);
        String startTime = ToolDateTime.format(DateUtils.wantToLose(ToolDateTime.getDate(),29),ToolDateTime.pattern_ymd);
        String endTime = ToolDateTime.format(DateUtils.wantToLose(ToolDateTime.getDate(),1),ToolDateTime.pattern_ymd);
//        PricePeak pricePeak1 = pricePeakService.getHighestAndlowestDate(bid,size,1,lagePage,startTime,endTime);
//        PricePeak pricePeak2 = pricePeakService.getHighestAndlowestDate(bid,size,2,lagePage,startTime,endTime);
        List<BasicinformationRose> basicinformationRoseList = basiciformationRoseService.getHighestAndlowestDate(record,startTime,endTime);
        if (basicinformationRoseList.size() > 0) {
            params.put("basicinformationRose", basicinformationRoseList.get(0));
            params.put("basicinformationRoseLog", basicinformationRoseList.get(1));
            resultModel.setRetCode(resultModel.RET_OK);
        }else {
            resultModel.setRetCode(0);
        }
        resultModel.setData(params);
        return resultModel;
    }
}
