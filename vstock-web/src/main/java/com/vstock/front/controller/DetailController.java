package com.vstock.front.controller;

import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.Bid;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping
    public String index(@RequestParam String proName, ModelMap modelMap){
        String size = request.getParameter("size");
        Basicinformation basicinformation = new Basicinformation();
        basicinformation.setName(proName);
        basicinformation = basicinformationService.findObj(basicinformation);
        setLastPage(0,1);
        int bid = Integer.parseInt(basicinformation.getId());
        Trade trade = tradeService.getLastTrade(bid,size,lagePage);
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
