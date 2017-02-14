package com.vstock.admin.controller;

import com.vstock.admin.service.BidService;
import com.vstock.admin.service.RefundService;
import com.vstock.admin.service.TradeService;
import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Refund;
import com.vstock.db.entity.Trade;
import com.vstock.ext.base.BaseController;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by administor on 2016/5/9.
 */
@Controller
@RequestMapping("/resfund")
public class RefundController extends BaseController {

    private static Logger logger = Logger.getLogger(RefundController.class);

    @Autowired
    RefundService refundService;

    @Autowired
    BidService bidService;

    @Autowired
    TradeService tradeService;

    //退款查询页面
    @RequestMapping("index")
    public String index(Refund record, HttpServletRequest request, ModelMap model) {
        String pageNow = request.getParameter("pageNow");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        model.put("startTime",startTime);
        model.put("endTime",endTime);
        String linkAddress = request.getRequestURI() + "?1=1";
        if (startTime != null && !"".equals(startTime)){
            startTime = startTime + " 00:00:00";
        }
        if (endTime != null && !"".equals(endTime)){
            endTime = endTime + " 23:59:59";
        }
        int totalCount =  refundService.findCountDate(record,startTime,endTime);
        Page page = new Page(totalCount,pageNow);
        List<Refund> refundList = refundService.findAllDate(record,page,startTime,endTime);
        linkAddress = refundService.linkAddress(record,startTime,endTime,linkAddress);
        List<Refund> statusList = StatusUtil.refundStatus();
        List<Refund> typeList = StatusUtil.refundType();
        List<Refund> objList = StatusUtil.refundO();
        model.put("linkAddress",linkAddress);
        model.put("statusList",statusList);
        model.put("typeList",typeList);
        model.put("objList",objList);
        model.put("refundList",refundList);
        model.put("page",page);
        model.put("record",record);
        return "admin/finance/index";
    }

    //进入退款新增界面
    @RequestMapping("insertRefund")
    public String insertRefund(ModelMap model) {
        List<Refund> typeList = StatusUtil.refundType();
        List<Refund> objList = StatusUtil.refundO();
        model.put("typeList",typeList);
        model.put("objList",objList);
        return "admin/finance/insertRefund";
    }

    //查询退款弹窗信息
    @RequestMapping("findBidTrade")
    @ResponseBody
    public Map<String,Object> findBidTrade(HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        String name = request.getParameter("name");
        String btfName = request.getParameter("btfName");
        String type = request.getParameter("type");
        if (Integer.parseInt(type) == 1){
            Bid record = new Bid();
            record.setName(name);
            record.setStatus("11");
            record.setBftName(btfName);
            Page page = new Page(10,"1");
            List<Bid> bidList = bidService.findAndUser(record,new BigDecimal(0),new BigDecimal(0),page);
            param.put("bidList",bidList);
        }else {
            Trade record = new Trade();
            record.setBftName(btfName);
            record.setSellerName(name);
            List<Trade> tradeList = tradeService.findModel(record);
            param.put("tradeList",tradeList);
        }
        return param;
    }

    //计算退款金额
    @RequestMapping("calculateRefund")
    @ResponseBody
    public Map<String,Object> calculateRefund(HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        if (id != "" && type != "") {
            param.put("price", refundService.refundAmount(Integer.parseInt(id), Integer.parseInt(type)));
        }
        return param;
    }

    //退款保存方法
    @RequestMapping("saveRefund")
    @ResponseBody
    public Map<String,Object> saveRefund(Refund record, HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        String upstatus = request.getParameter("upstatus");
        String tradeId = request.getParameter("tradeId");
        String btfId = request.getParameter("btfId");
        int i = refundService.save(record);
        if (i > 0 && !"4".equals(record.getType())){
            if (upstatus != null && !"".equals(upstatus) && Integer.parseInt(record.getType()) == 1){
                Bid bid = new Bid();
                bid.setId(Integer.parseInt(btfId));
                bid.setStatus(upstatus);
                i = bidService.update(bid);
            }else {
                Trade trade = new Trade();
                if (tradeId == null && "".equals(tradeId)){
                    Trade trades = new Trade();
                    trades.setTradeNo(record.getTradeNo());
                    trades = tradeService.findTrade(trades);
                    trade.setId(trades.getId());
                }else {
                    trade.setId(Integer.parseInt(tradeId));
                }
                trade.setStatus(Integer.parseInt(upstatus));
                i = tradeService.update(trade);
            }
        }
        param.put("reGode",i);
        return param;
    }

}
