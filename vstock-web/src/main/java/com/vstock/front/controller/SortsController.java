package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ModelAndView;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.Page;
import com.vstock.front.service.BasicinformationService;
import com.vstock.front.service.BidService;
import com.vstock.front.service.PricePeakService;
import com.vstock.front.service.TradeService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sorts")
public class SortsController extends BaseController{

    private static Logger logger = Logger.getLogger(SortsController.class);

    @Autowired
    BasicinformationService basicinformationService;
    @Autowired
    BidService bidService;
    @Autowired
    PricePeakService pricePeakService;
    @Autowired
    TradeService tradeService;

    @RequestMapping
    public String index(ModelMap modelMap){
        List<String> brandList = basicinformationService.getBrands();
        modelMap.addAttribute("brandList",brandList);
        return "/sorts/index";
    }

    @RequestMapping("list")
    public String list(ModelMap modelMap){
        String priceStart = "";
        String priceEnd = "";
        String size = request.getParameter("size");
        String price = request.getParameter("price");
        String year = request.getParameter("year");
        String brand = request.getParameter("brand");
        if(!"".equals(price) && price != null){
            priceStart = price.split("-")[0];
            priceEnd = price.split("-")[1];
        }
        List<Bid> bidList = bidService.findBidForSorts(size,year,brand,priceStart,priceEnd);
        modelMap.addAttribute("bidList",bidList);
        return "/sorts/list";
    }

    @RequestMapping("bidTips")
    @ResponseBody
    public ResultModel bidTips(@RequestParam int bid){
        Trade trade = new Trade();
        BasicinformationRose basicinformationRose = new BasicinformationRose();
        PricePeak pricePeak = new PricePeak();
        ResultModel resultModel = new ResultModel();
        Map<String,Object> resultMap = new HashedMap();
        setLastPage(0,1);
        double difference = 0;
        double percentag = 0;
        int roseType = 0;
        //获取最后成交价
        trade.setBasicinformationId(bid);
        List<Trade> tradelist = tradeService.findAll(trade,lagePage);
        if(tradelist.size() != 0){
            trade = tradelist.get(0);
        }
        //获取成交价格涨幅
        if(trade.getTransactionMoney() != null){
            //获取当前市场均价
            basicinformationRose.setBasicinformation_id(bid);
            basicinformationRose = basicinformationService.findRose(basicinformationRose);
            if(basicinformationRose != null){
                //市场价
                BigDecimal market = basicinformationRose.getCurrent_market_value();
                roseType = basicinformationRose.getType();
                //成交价
                BigDecimal transactionMoney = trade.getTransactionMoney();
                difference = BasicinformationRose.getDifference(market,transactionMoney).doubleValue();
                percentag = BasicinformationRose.getPercentag(market,transactionMoney).doubleValue();
            }
        }
        //获取最低售价，最高出价
        pricePeak.setBasicinformationId(bid);
        List<PricePeak> pricePeaks = pricePeakService.findAll(pricePeak,lagePage);
        if(pricePeaks.size() != 0){
            pricePeak = pricePeaks.get(0);
        }
        resultMap.put("trade",trade);
        resultMap.put("difference",difference);
        resultMap.put("percentag",percentag);
        resultMap.put("pricePeak",pricePeak);
        resultMap.put("roseType",roseType);
        resultModel.setData(resultMap);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }
}
