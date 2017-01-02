package com.vstock.admin.controller;

import com.vstock.admin.service.BackCommodityService;
import com.vstock.admin.service.BasicinformationService;
import com.vstock.admin.service.TradeService;
import com.vstock.db.entity.BackCommodity;
import com.vstock.db.entity.Basicinformation;
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

    @RequestMapping("index")
    public String index(BackCommodity record, HttpServletRequest request, ModelMap model) {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String pageNow = request.getParameter("pageNow");
        int dCListCount = backCommodityService.findAndBtfCount(record,startTime,endTime);
        Page page = new Page(dCListCount,pageNow);
        List<BackCommodity> backCommodityList = backCommodityService.findAndBtf(record,startTime,endTime,page);
        List<BackCommodity> statusList = StatusUtil.backStatus();
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
    public ModelMap saveBackCommodity(BackCommodity record, HttpServletRequest request, ModelMap model) {
        int i = backCommodityService.save(record);
        model.put("reGode",i);
        return model;
    }

}
