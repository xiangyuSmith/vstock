package com.vstock.front.controller;

import com.vstock.db.entity.Bid;
import com.vstock.db.entity.PricePeak;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.security.md.ToolMD5;
import com.vstock.front.service.BidService;
import com.vstock.front.service.PricePeakService;
import com.vstock.front.service.VstockConfigService;
import com.vstock.front.service.interfaces.IVstockConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

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
                .append(Bid.BID_MD5_MARK_NOTIFY).append("bId=").append(bId)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("bName=").append(bName)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("size=").append(size)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("bName=").append(bName)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("amount=").append(amount)
                .append(Bid.BID_MD5_MARK_NOTIFY).append("Md5Sign=").append(VstockConfigService.getConfig(IVstockConfigService.BID_VSTOCK_MD5KEY))
                .toString());
        Bid bid = new Bid();
        bid.setBftName(bName);
        bid.setBasicinformationId(bId);
        bid.setBftSize(size);
        bid.setBidMoney(new BigDecimal(amount));
        bid.setInvalidDate(bidService.getOverDueTime(overdueTime));
        bid.setSign(sign);
        bid.setType(type);
        bid.setBidBond(new BigDecimal(10));
        bid.setStatus(0);
        PricePeak pricePeak = pricePeakService.getHighestAndlowest(bId,size,lagePage);
        int i = bidService.insert(bid);
        resultModel.setRetCode(i);
        return resultModel;
    }
}
