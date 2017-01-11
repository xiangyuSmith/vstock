package com.vstock.admin.controller;

import com.vstock.admin.service.BackCommodityService;
import com.vstock.admin.service.BasicinformationService;
import com.vstock.admin.service.ExpressService;
import com.vstock.admin.service.TradeService;
import com.vstock.db.entity.BackCommodity;
import com.vstock.db.entity.Basicinformation;
import com.vstock.db.entity.Express;
import com.vstock.db.entity.Trade;
import com.vstock.ext.util.Page;
import com.vstock.server.util.StatusUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backCommodity")
public class BackCommodityController {

    private static Logger logger = Logger.getLogger(BackCommodityController.class);

    @Autowired
    BackCommodityService backCommodityService;

    @Autowired
    BasicinformationService basicinformationService;

    @Autowired
    TradeService tradeService;

    @Autowired
    ExpressService expressService;

    @RequestMapping("index")
    public String index(BackCommodity record, HttpServletRequest request, ModelMap model) {
        Express express = new Express();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String pageNow = request.getParameter("pageNow");
        int dCListCount = backCommodityService.findAndBtfCount(record,startTime,endTime);
        Page page = new Page(dCListCount,pageNow);
        List<BackCommodity> backCommodityList = backCommodityService.findAndBtf(record,startTime,endTime,page);
        List<BackCommodity> statusList = StatusUtil.backStatus();
        List<Express> expressList = expressService.findAll(express);
        model.put("expressList",expressList);
        model.put("backCommodityList",backCommodityList);
        model.put("statusList",statusList);
        model.put("page",page);
        return "admin/trade/backindex";
    }

    @RequestMapping("lodejsp")
    public String lodejsp(HttpServletRequest request, ModelMap model) {
        String type = request.getParameter("type");
        model.put("type",type);
        return "admin/trade/insertBack";
    }

    @RequestMapping("backList")
    public String backList(BackCommodity record, HttpServletRequest request, ModelMap model) {
        String btfName = request.getParameter("btfName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        BackCommodity backCommodity = new BackCommodity();
        backCommodity.setId(record.getId());
        Page page = new Page(10,"1");
        List<BackCommodity> backCommodityList = backCommodityService.findAndBtf(backCommodity,startTime,endTime,page);
        if (backCommodityList.size() > 0){
            backCommodity = backCommodityList.get(0);
        }
        List<BackCommodity> statusList = StatusUtil.backStatus();
        model.put("backCommodity",backCommodity);
        model.put("statusList",statusList);
        model.put("record",record);
        model.put("btfName",btfName);
        model.put("startTime",startTime);
        model.put("endTime",endTime);
        return "admin/trade/backList";
    }

    @RequestMapping("findBtf")
    @ResponseBody
    public Map<String,Object> findBtf(HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        String name = request.getParameter("name");
        String chineselogo = request.getParameter("chineselogo");
        Basicinformation record = new Basicinformation();
        record.setName(name);
        record.setChineselogo(chineselogo);
        Page page = new Page(5,"1");
        List<Basicinformation> btfList = basicinformationService.findbasicAll(record,page,null,null,null,null,null,null);
        param.put("btfList",btfList);
        return param;
    }

    @RequestMapping("findTrade")
    @ResponseBody
    public Map<String,Object> findTrade(HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        String sellerName = request.getParameter("sellerName");
        String buyersName = request.getParameter("buyersName");
        String btfName = request.getParameter("btfName");
        Trade record = new Trade();
        record.setBuyersName(buyersName);
        record.setSellerName(sellerName);
        record.setBftName(btfName);
        Map<Page,List<Trade>> parames = tradeService.dateFindAll(record, null, null, "1");
        for (Page page : parames.keySet()){
            param.put("tradeList",parames.get(page));
        }
        return param;
    }

    @RequestMapping("saveBackCommodity")
    @ResponseBody
    public Map<String, Object> saveBackCommodity(BackCommodity record, HttpServletRequest request) {
        Map<String, Object> param = new HashMap<String, Object>();
        int i = backCommodityService.save(record);
        if (record.getStatus() != null && !"".equals(record.getStatus())) {
            Trade trade = new Trade();
            trade.setTradeNo(record.getTradeNo());
            trade = tradeService.findTrade(trade);
            trade.setStatus(41);
            if (record.getStatus() == 10) {
                i = tradeService.save(trade);
            }
        }
        param.put("reGode",i);
        return param;
    }

}
