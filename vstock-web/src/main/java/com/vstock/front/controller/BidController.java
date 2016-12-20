package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.PricePeak;
import com.vstock.db.entity.User;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.security.md.ToolMD5;
import com.vstock.front.service.BidService;
import com.vstock.front.service.PricePeakService;
import com.vstock.front.service.VstockConfigService;
import com.vstock.front.service.interfaces.IVstockConfigService;
import com.vstock.front.support.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by administor on 2016/12/6.
 */
@Controller
@RequestMapping("/bid")
public class BidController extends BaseController{

    @Autowired
    BidService bidService;
    @Autowired
    PricePeakService pricePeakService;
    @Autowired
    AccessInterceptor accessInterceptor;

    @RequestMapping
    @ResponseBody
    public ResultModel index(){
        ResultModel resultModel = new ResultModel();
        setLastPage(0,1);
        String bName = getParam("bname");
        String size = getParam("size");
        int type = getParamToInt("type");
        int bId = getParamToInt("bId");
        double amount = Double.valueOf(getParam("amount", "0L"));
        String overdueTime = getParam("overdueTime");
        String sign = ToolMD5.encodeMD5Hex(new StringBuilder()
                .append("bId=").append(bId)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("bName=").append(bName)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("size=").append(size)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("amount=").append(amount)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("Md5Sign=").append(VstockConfigService.getConfig(IVstockConfigService.BID_VSTOCK_MD5KEY))
                .toString());
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        Bid bid = new Bid();
        bid.setBftName(bName);
        bid.setUserId(Integer.parseInt(uid));
        bid.setBasicinformationId(bId);
        bid.setBftSize(size);
        bid.setBidMoney(new BigDecimal(amount));
        bid.setInvalidDate(bidService.getOverDueTime(overdueTime));
        bid.setSign(sign);
        bid.setType(type);
        bid.setBidBond(new BigDecimal(10));
        bid.setStatus(0);
        bid.setBidDate(DateUtils.dateToString(new Date()));
        PricePeak pricePeak = pricePeakService.getHighestAndlowest(bId,size, DateUtils.dateToString(new Date()),lagePage);
        int resultBid = bidService.insert(bid);
        int resultPeak = pricePeakService.isAmount(pricePeak,new BigDecimal(amount),bId,size,uid,type);
        if(resultBid == 1 && resultPeak == 1){
            resultModel.setRetCode(resultModel.RET_OK);
        }
        return resultModel;
    }

    @RequestMapping("updateBid")
    @ResponseBody
    public Map<String,Object> updateBid(){
        Map<String,Object> param = new HashMap<String,Object>();
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String endDate = request.getParameter("endDate");
        String bidMoney = request.getParameter("bidMoney");
        int sgin = bidService.updateBid(id,status,endDate,bidMoney);
        param.put("sgin",sgin);
        return param;
    }
}
