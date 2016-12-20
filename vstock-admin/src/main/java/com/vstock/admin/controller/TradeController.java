package com.vstock.admin.controller;

import com.vstock.admin.service.TradeService;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")
public class TradeController {

    private static Logger logger = Logger.getLogger(TradeController.class);

    @Autowired
    TradeService tradeService;

    @RequestMapping("index")
    public String index(Trade record, HttpServletRequest request, ModelMap model) {
        List<String> sizeList = new ArrayList<String>();
        List<Trade> tradeList = new ArrayList<Trade>();
        Page page = new Page();
        record = tradeService.btfUser(record);
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
        record.setTransactionDate(startTime);
        record.setEndDate(endTime);
        model.put("tradeList",tradeList);
        model.put("statusList",statusList);
        model.put("page",page);
        model.put("trade",record);
        model.put("sizeList",sizeList);
        model.put("linkAddress",linkAddress);
        return "admin/trade/index";
    }

    @RequestMapping("tradeList")
    public String tradeList(Trade record, HttpServletRequest request, ModelMap model) {
        Trade trade = new Trade();
        String id = request.getParameter("fid");
        trade.setId(Integer.parseInt(id));
        trade = tradeService.findTrade(trade);
        model.put("record",record);
        model.put("trade",trade);
        return "admin/trade/tradeList";
    }

}
