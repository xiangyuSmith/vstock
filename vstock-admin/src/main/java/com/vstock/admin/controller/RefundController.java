package com.vstock.admin.controller;

import com.vstock.admin.service.BidService;
import com.vstock.admin.service.RefundService;
import com.vstock.admin.service.TradeService;
import com.vstock.db.entity.Bid;
import com.vstock.db.entity.Refund;
import com.vstock.db.entity.Trade;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.util.Page;
import com.vstock.server.alipay.util.AlipayNotify;
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
        List<Refund> statusList = StatusUtil.refundStatus();
        model.put("statusList",statusList);
        List<Refund> objList = StatusUtil.refundO();
        model.put("objList",objList);
        //判断是否是需要转账
        if ("5".equals(record.getType()) || "6".equals(record.getType())){
            linkAddress = refundService.linkAddress(record,startTime,endTime,linkAddress);
            List<Refund> typeList = StatusUtil.refundType();
            model.put("typeList",typeList);
            int totalCount =  refundService.findCountDate(record,startTime,endTime);
            Page page = new Page(totalCount,pageNow);
            List<Refund> refundList = refundService.findAllDate(record,page,startTime,endTime);
            model.put("page",page);
            model.put("refundList",refundList);
            model.put("record",record);
            //违约金跳转
            if ("6".equals(record.getType())) {
                linkAddress = linkAddress + "&type=6";
                model.put("linkAddress",linkAddress);
                return "admin/finance/damagesindex";
            }else {//转账鞋款跳转
                linkAddress = linkAddress + "&type=5";
                model.put("linkAddress",linkAddress);
                return null;
            }
        }else{
            linkAddress = refundService.linkAddress(record,startTime,endTime,linkAddress);
            model.put("linkAddress",linkAddress);
            List<Refund> typeList = StatusUtil.onlyrfType();
            model.put("typeList",typeList);
            int totalCount =  refundService.findRefundCount(record,startTime,endTime);
            Page page = new Page(totalCount,pageNow);
            List<Refund> refundList = refundService.findRefundAll(record,page,startTime,endTime);
            model.put("page",page);
            model.put("refundList",refundList);
            model.put("record",record);
        }
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

    //退款调用支付宝借口
    @RequestMapping("alipayRefund")
    public String alipayRefund(Refund record, ModelMap model, HttpServletRequest request) {
        String upstatus = request.getParameter("upstatus");
        Map<String, String> sParaTemp = refundService.refundObj(record,upstatus);
        model.addAttribute("sParaTemp",sParaTemp);
        return "admin/common/alipay/alipayapi";
    }

    @RequestMapping("returnRefund")
    @ResponseBody
    public String returnRefund(Refund record, HttpServletRequest request){
        Map<String, String> params = new HashMap<String, String>();
        String rescode = "";
        String upstatus = request.getParameter("upstatus");
        String tradeId = request.getParameter("tradeId");
        String notify_time = request.getParameter("notify_time");
        String notify_type = request.getParameter("notify_type");
        String notify_id = request.getParameter("notify_id");
        String sign_type = request.getParameter("sign_type");
        String sign = request.getParameter("sign");
        String batch_no = request.getParameter("batch_no");
        String success_num = request.getParameter("success_num");
        String result_details = request.getParameter("result_details");
        params.put("notify_time",notify_time);
        params.put("notify_type",notify_type);
        params.put("notify_id",notify_id);
        params.put("sign_type",sign_type);
        params.put("sign",sign);
        params.put("batch_no",batch_no);
        params.put("success_num",success_num);
        params.put("result_details",result_details);
        if (AlipayNotify.verify(params)) {
            if ("1".equals(success_num)) {
                refundService.refundAndTransfer(record, upstatus, tradeId);
            }
            rescode = "success";
        }
        return rescode;
    }

    @RequestMapping("transferAccounts")
    @ResponseBody
    public Map<String,Object> transferAccounts(Refund record, HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        String upstatus = request.getParameter("upstatus");
        String tradeId = request.getParameter("tradeId");
        int i = refundService.transferAccountsObj(record);
        if (i > 0) {
            i = refundService.refundAndTransfer(record, upstatus, tradeId);
        }
        param.put("reGode",i);
        return param;
    }

    //退款保存方法
    @RequestMapping("saveRefund")
    @ResponseBody
    public Map<String,Object> saveRefund(Refund record, HttpServletRequest request){
        Map<String,Object> param = new HashMap<String,Object>();
        String upstatus = request.getParameter("upstatus");
        String tradeId = request.getParameter("tradeId");
        int i = refundService.refundAndTransfer(record,upstatus,tradeId);
        param.put("reGode",i);
        return param;
    }

}
