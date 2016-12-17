package com.vstock.front.controller;

import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.PricePeak;
import com.vstock.db.entity.Trade;
import com.vstock.ext.base.BaseController;
import com.vstock.front.service.BasicinformationService;
import com.vstock.front.service.BidService;
import com.vstock.front.service.PricePeakService;
import com.vstock.front.service.TradeService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/detail/{proName}")
public class DetailController extends BaseController{

    @Autowired
    BasicinformationService basicinformationService;
    @Autowired
    BidService bidService;
    @Autowired
    PricePeakService pricePeakService;
    @Autowired
    TradeService tradeService;

    @RequestMapping
    public String index(@PathVariable String proName,ModelMap modelMap){
//        Basicinformation basicinformation = new Basicinformation();
//        basicinformation.setName(proName);
//        basicinformation = basicinformationService.findObj(basicinformation);
//        setLastPage(0,1);
//        int bid = Integer.parseInt(basicinformation.getId());
//        Trade trade = tradeService.getLastTrade(bid,lagePage);
//        Map<String,Object> resParams = basicinformationService.getPricesTrend(bid,trade);
//        PricePeak pricePeak = pricePeakService.getHighestAndlowest(bid,lagePage);
//        modelMap.addAttribute("resParams",resParams);
//        modelMap.addAttribute("pricePeak",pricePeak);
//        modelMap.addAttribute("trade",trade);
//        modelMap.addAttribute("basicinformation",basicinformation);
        return "/detail/index";
    }
}
