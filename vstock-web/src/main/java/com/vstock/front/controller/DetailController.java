package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.front.service.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/detail")
public class DetailController extends BaseController{

    @Autowired
    BasicinformationService basicinformationService;
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
        Trade trade = tradeService.getLastTrade(bid,size,Trade.TRADE_SUCESS ,lagePage);
        Map<String,Object> resParams = basicinformationService.getPricesTrend(bid,size,trade);
        //TODO 传递尺码时根据尺码查询最高最低价，不传尺码时查询最高最低价并获取对应尺码
        PricePeak pricePeak1 = pricePeakService.getHighestAndlowest(bid,size,1,lagePage);
        PricePeak pricePeak2 = pricePeakService.getHighestAndlowest(bid,size,2,lagePage);
        Bid sell_bid1 = bidService.getHightAndMinPrice(bid,0,1,lagePage);
        Bid sell_bid2 = bidService.getHightAndMinPrice(bid,0,2,lagePage);
        Bid buyer_bid1 = bidService.getHightAndMinPrice(bid,1,1,lagePage);
        Bid buyer_bid2 = bidService.getHightAndMinPrice(bid,1,2,lagePage);
        modelMap.addAttribute("resParams",resParams);
        modelMap.addAttribute("pricePeak1",pricePeak1);
        modelMap.addAttribute("pricePeak2",pricePeak2);
        modelMap.addAttribute("sell_bid1",sell_bid1);
        modelMap.addAttribute("sell_bid2",sell_bid2);
        modelMap.addAttribute("buyer_bid1",buyer_bid1);
        modelMap.addAttribute("buyer_bid2",buyer_bid2);
        modelMap.addAttribute("trade",trade);
        modelMap.addAttribute("basicinformation",basicinformation);
        modelMap.addAttribute("sizes",Basicinformation.sizes);
        modelMap.addAttribute("size",size);
        return "/detail/index";
    }
}
