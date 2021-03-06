package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ModelAndView;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.Page;
import com.vstock.front.service.*;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        String paroductName = getParam("productName");
        modelMap.addAttribute("productName",paroductName);
        modelMap.addAttribute("brandName",getParam("brandName",""));
        modelMap.addAttribute("sizes",Basicinformation.sizes);
        modelMap.addAttribute("type",getParamToInt("type"));
        return "/sorts/index";
    }

    @RequestMapping("list")
    public String list(ModelMap modelMap){
        String priceStart = "";
        String priceEnd = "";
        int pageStart = Integer.parseInt(getParam("pageStart","0"));
        String productName = getParam("productName","");
        setLastPage(0,1);
        if(VstockConfigService.isChineseChar(productName)){
            //包含中文，检测是否匹配中文检索
            LanguageControl language = new LanguageControl();
            language.setChinese(productName);
            List<LanguageControl> languageControlList = basicinformationService.findLanguage(language);
            if(languageControlList.size() > 0){
                productName = languageControlList.get(0).getEnglish();
            }
        }
        Integer type = getParamToInt("type");
        String size = request.getParameter("size");
        String price = request.getParameter("price");
        String year = request.getParameter("year");
        String brand = request.getParameter("brand");
        if(!"".equals(price) && price != null){
            priceStart = price.split("-")[0];
            priceEnd = price.split("-")[1];
        }
        List<Basicinformation> bidList = basicinformationService.findBasicinForSorts(productName,type,size,year,brand,priceStart,priceEnd,pageStart,20);
        List<BigDecimal> bidMoneyList = new ArrayList<BigDecimal>();
        Bid b = new Bid();
        for(int i = 0;i < bidList.size();i++){
            Basicinformation bft = bidList.get(i);
            if("1549".equals(bft.getId())){
                System.out.print("1549");
            }
            String bftId = bft.getId();
            b.setBasicinformationId(Integer.parseInt(bftId));
            b.setStatus(String.valueOf(Bid.STATUS_INIT));
            List<Bid> bList = bidService.findNewAll(b,lagePage);
            if(bList.size() != 0){
                bidList.get(i).getBid().setBidMoney(bList.get(0).getBidMoney());
            }else{
                bidList.get(i).getBid().setBidMoney(null);
            }
        }
        modelMap.addAttribute("bidMoneyList",bidMoneyList);
        modelMap.addAttribute("bidList",bidList);
        modelMap.addAttribute("size",size);
        modelMap.addAttribute("price",price);
        modelMap.addAttribute("year",year);
        modelMap.addAttribute("brand",brand);
        modelMap.addAttribute("productName",productName);
        modelMap.addAttribute("type",type);
        modelMap.addAttribute("pageStart",pageStart);
        return "/sorts/list";
    }

    @RequestMapping("bidTips")
    @ResponseBody
    public ResultModel bidTips(@RequestParam int bid,@RequestParam String size){
        ResultModel resultModel = new ResultModel();
        Map<String,Object> resultMap = new HashedMap();
        setLastPage(0,1);
        //获取最后成交价
        Basicinformation b = new Basicinformation();
        b.setId(String.valueOf(bid));
        Trade trade = tradeService.getLastTrade(bid,size,lagePage);
        Basicinformation basicinformation = basicinformationService.findObj(b);
        //获取成交价格涨幅
        Map<String,Object> resParams = basicinformationService.getPricesTrend(bid,size,trade);
        //获取最低售价，最高出价
        PricePeak pricePeak1 = pricePeakService.getHighestAndlowest(bid,size,1,lagePage);
        PricePeak pricePeak2 = pricePeakService.getHighestAndlowest(bid,size,2,lagePage);
        resultMap.put("trade",trade);
        resultMap.put("pricePeak1",pricePeak1);
        resultMap.put("pricePeak2",pricePeak2);
        resultMap.put("basicinformation",basicinformation);
        resultMap.put("difference",resParams.get("difference"));
        resultMap.put("percentag",resParams.get("percentag"));
        resultMap.put("roseType",resParams.get("roseType"));
        resultModel.setData(resultMap);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }
}
