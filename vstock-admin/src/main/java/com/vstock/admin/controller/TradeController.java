package com.vstock.admin.controller;

import com.vstock.admin.service.BidService;
import com.vstock.admin.service.ExpressService;
import com.vstock.admin.service.LogisticsInformationService;
import com.vstock.admin.service.TradeService;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.Page;
import com.vstock.server.util.StatusUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")
public class TradeController {

    private static Logger logger = Logger.getLogger(TradeController.class);

    @Autowired
    TradeService tradeService;

    @Autowired
    BidService bidService;

    @Autowired
    ExpressService expressService;

    @Autowired
    LogisticsInformationService logisticsInformationService;

    @RequestMapping("index")
    public String index(Trade record, HttpServletRequest request, ModelMap model) {
        List<String> sizeList = new ArrayList<String>();
        List<Trade> tradeList = new ArrayList<Trade>();
        Express express = new Express();
        Page page = new Page();
//        record = tradeService.btfUser(record);
        String pageNow = request.getParameter("pageNow");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String linkAddress = request.getRequestURI() + "?1=1";
        linkAddress = tradeService.linkAddress(linkAddress,record,startTime,endTime);
        Map<Page,List<Trade>> param = tradeService.dateFindAll(record, startTime, endTime, pageNow);
        for (Page pages : param.keySet()){
            page = pages;
            tradeList = param.get(pages);
        }
        for (int i = 0; i < Basicinformation.sizes.length; i++){
            sizeList.add(Basicinformation.sizes[i]);
        }
        List<List<String>> statusList = tradeService.status();
        List<Express> expressList = expressService.findAll(express);
        record.setTransactionDate(startTime);
        record.setUpdateDate(endTime);
        model.put("tradeList",tradeList);
        model.put("statusList",statusList);
        model.put("expressList",expressList);
        model.put("page",page);
        model.put("trade",record);
        model.put("sizeList",sizeList);
        model.put("linkAddress",linkAddress);
        return "admin/trade/index";
    }

    @RequestMapping("tradeList")
    public String tradeList(Trade record, HttpServletRequest request, ModelMap model) {
        Trade trade = new Trade();
        trade.setId(record.getId());
        trade = tradeService.findTrade(trade);
        List<List<String>> statusList = tradeService.status();
        model.put("statusList",statusList);
        model.put("record",record);
        model.put("trade",trade);
        return "admin/trade/tradeList";
    }

    @RequestMapping("saveTrade")
    @ResponseBody
    public Map<String,Object> saveTrade(HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        Trade record = new Trade();
        String remarks = request.getParameter("remarks");
        record.setId(Integer.parseInt(request.getParameter("id")));
        record.setStatus(Integer.parseInt(request.getParameter("status")));
        if (remarks != null && !"".equals(remarks)){
            record.setSign(remarks);
        }
        record.setUpdateDate(DateUtils.getCurrentTimeAsString());
        int i = tradeService.save(record);
        param.put("reGode",i);
        return param;
    }

    @RequestMapping("bidindex")
    public String bidindex(Bid record, HttpServletRequest request, ModelMap model) {
        List<String> sizeList = new ArrayList<String>();
        List<Bid> bidList = new ArrayList<Bid>();
        Basicinformation basicinformation = new Basicinformation();
        Page page = new Page();
        String artNo = request.getParameter("artNo");
        basicinformation.setArtNo(artNo);
        record.setBasicinformation(basicinformation);
        String pageNow = request.getParameter("pageNow");
        String minimumMoney = request.getParameter("minimumMoney");
        String maximumMoney = request.getParameter("maximumMoney");
        String linkAddress = request.getRequestURI() + "?1=1";
        linkAddress = bidService.linkAddress(linkAddress,record,minimumMoney,maximumMoney);
        Map<Page,List<Bid>> param = bidService.findList(record,pageNow,minimumMoney,maximumMoney);
        for (Page pages : param.keySet()){
            page = pages;
            bidList = param.get(pages);
        }
        List<Bid> statusList = StatusUtil.bidStatusa();
        for (int i = 0; i < Basicinformation.sizes.length; i++){
            sizeList.add(Basicinformation.sizes[i]);
        }
        model.put("minimumMoney",minimumMoney);
        model.put("maximumMoney",maximumMoney);
        model.put("record",record);
        model.put("statusList",statusList);
        model.put("sizeList",sizeList);
        model.put("page",page);
        model.put("bidList",bidList);
        model.put("linkAddress",linkAddress);
        return "admin/trade/bidIndex";
    }

    @RequestMapping("saveLogisticsIn")
    @ResponseBody
    public Map<String,Object> saveLogisticsIn(HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        LogisticsInformation record = new LogisticsInformation();
        Trade trade = new Trade();
        String id = request.getParameter("id");
        String companyName = request.getParameter("companyName");
        String courierNumber = request.getParameter("courierNumber");
        trade.setId(Integer.parseInt(id));
        trade.setCourierNumber(courierNumber);
        trade.setStatus(30);
        record.setTradeId(Integer.parseInt(id));
        record.setCompanyName(companyName);
        int i = logisticsInformationService.save(record);
        if (i > 0) {
            i = tradeService.save(trade);
        }
        param.put("reGode",i);
        return param;
    }

    @RequestMapping("pricepeak")
    public String pricepeak(PricePeak record, HttpServletRequest request, ModelMap model) {
        List<String> sizeList = new ArrayList<String>();
        Basicinformation basicinformation = new Basicinformation();
        String artNo = request.getParameter("artNo");
        String pageNow = request.getParameter("pageNow");
        String name = request.getParameter("name");
        if (artNo != null && !"".equals(artNo)){
            basicinformation.setArtNo(artNo);
            record.setBasicinformation(basicinformation);
        }
        if (name != null && !"".equals(name)){
            basicinformation.setName(name);
            record.setBasicinformation(basicinformation);
        }
        String linkAddress = request.getRequestURI() + "?1=1";
        linkAddress = tradeService.pricelinkAddress(linkAddress,record);
        int totalCount = tradeService.findBftCount(record);
        Page page = new Page(totalCount,pageNow);
        List<PricePeak> pricePeakList = tradeService.findAndBft(record,page);
        for (int i = 0; i < Basicinformation.sizes.length; i++){
            sizeList.add(Basicinformation.sizes[i]);
        }
        model.put("record",record);
        model.put("page",page);
        model.put("sizeList",sizeList);
        model.put("pricePeakList",pricePeakList);
        model.put("linkAddress",linkAddress);
        return "admin/trade/pricepeak";
    }

}
