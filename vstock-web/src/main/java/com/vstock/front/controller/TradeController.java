package com.vstock.front.controller;

import com.vstock.db.entity.*;
import com.vstock.ext.base.BaseController;
import com.vstock.ext.base.ResultModel;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.OddNoUtil;
import com.vstock.ext.util.Page;
import com.vstock.front.service.*;
import com.vstock.front.service.interfaces.IVstockConfigService;
import com.vstock.server.alipay.config.AlipayConfig;
import com.vstock.server.alipay.util.AlipayNotify;
import com.vstock.server.ihuyi.Sendsms;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/trade")
public class TradeController extends BaseController{

    private static Logger logger = Logger.getLogger(TradeController.class);

    @Autowired
    UserAddressService userAddressService;
    @Autowired
    PricePeakService pricePeakService;
    @Autowired
    BasicinformationService basicinformationService;
    @Autowired
    BidService bidService;
    @Autowired
    TradeService tradeService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    UserService userService;
    @Autowired
    RefundService refundService;

    final BigDecimal bidMoney = new BigDecimal(0.01).setScale(2,BigDecimal.ROUND_HALF_UP);

    @RequestMapping
    @ResponseBody
    public ResultModel index(){
        //TODO 重复提交订单校验
        ResultModel resultModel = new ResultModel();
        setLastPage(0,1);
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        logger.info("生成订单:"+uid);
        double amount = Double.valueOf(getParam("amount", "0"));
        String size = getParam("size");
        String type = getParam("type");
        int addressId = Integer.valueOf(getParam("addressId", "0"));
        int bftId = getParamToInt("bId");
        BigDecimal yunFee = BigDecimal.ZERO;
        UserAddress u = new UserAddress();
        u.setId(addressId);
        UserAddress userAddress = userAddressService.findAddressById(u);
        if(!"1".equals(type)){
            yunFee = tradeService.findAllYunFee(userAddress.getLocalArea());
            if(yunFee == null){
                resultModel.setRetMsg("运费信息异常");
                logger.info("用户:"+uid+",运费信息异常");
                return resultModel;
            }
        }
        Bid bid = new Bid();
        bid.setBidMoney(new BigDecimal(amount).setScale(2,   BigDecimal.ROUND_HALF_UP));
        bid.setBftSize(size);
        bid.setType(type);
        bid.setBasicinformationId(bftId);
        bid.setStatus(String.valueOf(Bid.STATUS_INIT));
        Bid bid1 = bidService.findByBid(bid,lagePage);
        if(bid1 == null){
            resultModel.setRetMsg("未找到叫价记录，无法下单");
            logger.info("用户:"+uid+",未找到叫价记录，无法下单");
            return resultModel;
        }
        if(String.valueOf(bid1.getUserId()).equals(uid)){
            if("1".equals(type)){
                resultModel.setRetMsg("不能出售自己叫价的鞋子");
                logger.info("用户:"+uid+",不能出售自己叫价的鞋子");
            }else{
                resultModel.setRetMsg("不能购买自己出价的鞋子");
                logger.info("用户:"+uid+",不能购买自己出价的鞋子");
            }
            return resultModel;
        }
        Integer bidId = 0;
        Integer userTradeId = 0;
        if("0".equals(type)){
            bidId = Integer.parseInt(uid);
            userTradeId = bid1.getUserId();
            if(addressId == 0){
                resultModel.setRetMsg("您还没有设置收货地址");
                logger.info("用户:"+uid+",您还没有设置收货地址");
                return resultModel;
            }
        }else{
            bidId = bid1.getUserId();
            userTradeId = Integer.parseInt(uid);
        }
        //TODO 加入订单，关联买家叫价
        //拼接订单的收货地址
        String detailedAddress = userAddress.getDetailedAddress().replaceAll("-","_");
        String receivingInformation = userAddress.getLocalArea()+"-"+detailedAddress+"-"+userAddress.getConsigneeName()+"-"+userAddress.getPhoneNumber()+"-"+userAddress.getLandlineNumber();
        int status = "0".equals(type) ? Trade.TRADE_NOTIFIY_PAY : Trade.TRADE_NOTIFIY_PAY_BOND;
        int isBond = status==0?2:0;
        String orderNo = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + RandomStringUtils.randomNumeric(6);
        Trade trade = new Trade(isBond,addressId,receivingInformation,yunFee,size, DateUtils.dateToString(new Date()), DateUtils.dateToString(new Date()), status, "0",
                new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP), bid1.getBasicinformationId(), bid1.getId(), bidId, userTradeId,orderNo);
        int tradeId = tradeService.createTradeOne(trade, VstockConfigService.getConfig(IVstockConfigService.TRADE__BOGE_VSTOCK_MD5KEY));
        if(tradeId == 0){
            resultModel.setRetCode(0);
            resultModel.setRetMsg("服务器繁忙，请稍后再试");
            return resultModel;
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("tradeId",tradeId);
        params.put("bidId",bid1.getId());
        resultModel.setData(params);
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

    public void updateTradeInfo(int bidId,BigDecimal yunFee,int pricePeakId,int type,int tradeId){
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        //修改叫价状态
        Bid b = new Bid();
        b.setId(bidId);
        b.setBidFreight(yunFee);
        b.setStatus(String.valueOf(b.STATUS_SUCCESS));
        bidService.update(b);
        //修改峰值信息
        Page page = new Page(1,"1");
        b.setBidFreight(new BigDecimal(0));
        b = bidService.findByBid(b,page);
        Bid bidT = new Bid();
        bidT.setBasicinformationId(b.getBasicinformationId());
        bidT.setBftSize(b.getBftSize());
        bidT.setStatus("10");
        if(type != 2){
            bidT.setType("0");
        }else{
            bidT.setType("1");
        }
        List<Bid> bidList = bidService.findOrderByMoney(bidT);
        pricePeakService.updatePeak(b,bidList,pricePeakId);
        //修改
        Trade td = new Trade();
        Trade t = new Trade();
        t.setBidId(bidId);
        List<Trade> tradeList = tradeService.findAllTrade(t);
        if (tradeList.size() > 1) {
            for (Trade trade : tradeList) {
                if (trade.getId() != tradeId) {
                    if (type == 3) {
                        if (!uid.equals(trade.getSellerId())) {
                            td.setId(trade.getId());
                            td.setStatus(Trade.TRADE_CLOSE);
                            tradeService.update(td);
                        }
                    }
                }
            }
        }
    }

    @RequestMapping("createTradePay")
    public String createTradePay() {
        Map requestParams = request.getParameterMap();
        Map<String,String> params = bidService.eachMap(requestParams);
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        if(verify_result){setLastPage(0,1);
            String[] extra_common_param = getParam("extra_common_param").split("\\|");
            String bname = extra_common_param[5];
            String isUserHome = extra_common_param[6];
            if("1".equals(isUserHome)){
                return "redirect:/user/index";
            }else{
                return "redirect:/detail?proName="+bname;
            }
        }
        return "/error";
    }

    @RequestMapping("returnTradePay")
    @ResponseBody
    public String returnTradePay(){
        //获取支付宝GET过来反馈信息
        Map requestParams = request.getParameterMap();
        Map<String,String> params = bidService.eachMap(requestParams);
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        if(verify_result){
            logger.info("订单 - 支付宝发起回调...");
            setLastPage(0,1);
            String[] extra_common_param = getParam("extra_common_param").split("\\|");
            String uid = extra_common_param[0];
            int type = Integer.parseInt(extra_common_param[1]);
            int tradeId = Integer.parseInt(extra_common_param[2]);
            double amount = Double.parseDouble(extra_common_param[3]);
            String ischeck = extra_common_param[4];
            double yunFee = Double.parseDouble(extra_common_param[7]);
            int addressId = Integer.parseInt(extra_common_param[8]);
            String trade_no = getParam("trade_no");
            String buyer_email = getParam("buyer_email");
            //更新叫价 & 峰值
            Trade t = new Trade();
            t.setId(tradeId);
            List<Trade> tradeList = tradeService.findTrade(t,lagePage);
            Trade tradeUpdate = tradeList.get(0);
            Payment p = new Payment();
            p.setPayment_number(trade_no);
            Payment paymentFind = paymentService.findByTrade(p);
//            if((tradeUpdate.getIsBond() == 2 && tradeUpdate.getStatus() == tradeUpdate.TRADE_NOTIFIY_PAY_BOND) || tradeUpdate.getStatus() == tradeUpdate.TRADE_NOTIFIY_PAY ){
            if(paymentFind == null){
                //校验是否为待支付保证金 或 已下单待支付
                if(tradeUpdate.getIsBond() == 0 || tradeUpdate.getIsBond() == 2){
                    int sort = 0;
                    if(tradeUpdate.getStatus() == 0){
                        sort = 1;
                    }else{
                        if(tradeUpdate.getIsBond() == 1){
                            //如果支付过保证金，此单为卖家叫价
                            sort = 1;
                        }else{
                            sort = 2;
                        }
                    }
                    PricePeak pricePeak = pricePeakService.getHighestAndlowest(tradeUpdate.getBasicinformationId(),tradeUpdate.getBftSize(),sort,lagePage);
                    if(pricePeak != null){
                        updateTradeInfo(tradeUpdate.getBidId(),tradeUpdate.getTradeFreight(),pricePeak.getId(),type,tradeId);
                    }
                }
                Payment payment = new Payment();
                payment.setPayment_user_id(Long.parseLong(uid));
                payment.setOrder_record_id(tradeId);
                payment.setPayment_status(10);
                payment.setPayment_alipay_name(buyer_email);
                payment.setPayment_number(trade_no);
                payment.setPayment_mode(Payment.PAY_SOURCE_ALIPAY);
                payment.setPayment_type(type);
                payment.setPayment_date(DateUtils.dateToString(new Date()));
                payment.setPayment_over_date(DateUtils.getNowdateAddmm());
                payment.setPayment_money(new BigDecimal(amount));
                if(type == 2){ payment.setPayment_explain("支付说明:卖家出售-支付保证金"); }
                if(type == 3){ payment.setPayment_explain("支付说明:买家购买-支付鞋款"); }
                int payResult = paymentService.cteatePay(payment,VstockConfigService.getConfig(IVstockConfigService.PAY__BOGE_VSTOCK_MD5KEY));
                if(payResult == 0){
                    System.out.print("支付失败");
                }
                int tradeStatus = tradeService.checkTradeStatus(tradeId,lagePage);
                Trade trade = new Trade();
                trade.setId(tradeId);
                List<Trade> t_pay_list = tradeService.findAll(trade,lagePage);
                Trade t_pay = t_pay_list.get(0);
                String mobile = "";
                String content = "";
                String key = VstockConfigService.getConfig(IVstockConfigService.SENDSMS_IHUYI_KEY);
                String account = VstockConfigService.getConfig(IVstockConfigService.SENDSMS_IHUYI_ACCOUNT);
                Bid b = new Bid();
                b.setId(t_pay.getBidId());
                Bid bid = bidService.findByBid(b,lagePage);
                if(type == 2){
                    User user = userService.findById(String.valueOf(t_pay.getBuyersId()));
                    mobile = user.getMobile();
                    content = "您出价的鞋子“"+bid.getBftName()+"”，"+bid.getBftSize()+"码，已有卖家出售，请务必在24小时内完成支付，否则本次交易将失效。如有任何疑问，请联系v－stock客服。";
                }
                if(type == 3){
                    Trade tCheck = tradeList.get(0);
                    if(tCheck.getIsBond() != 1){
                        User user = userService.findById(String.valueOf(t_pay.getSellerId()));
                        mobile = user.getMobile();
                        content = "您叫价的鞋子“"+bid.getBftName()+"”，"+bid.getBftSize()+"码，已有买家购买，请及时发货，如有任何疑问请咨询v－stock客服。";
                    }else{
                        User user = userService.findById(String.valueOf(t_pay.getSellerId()));
                        mobile = user.getMobile();
                        content = "您叫价的鞋子“"+bid.getBftName()+"”，"+bid.getBftSize()+"码，已有买家购买，请及时发货，如有任何疑问请咨询v－stock客服。";
                        //买家付鞋款，生成买家保证金退款单
                        Refund refund = new Refund();
                        refund.setType("3");
                        refund.setRefundNo(OddNoUtil.refundNo());
                        refund.setTradeNo(String.valueOf(bid.getId()));
                        refund.setRefundObj(bid.getType());
                        refund.setBtfId(bid.getBasicinformationId());
                        refund.setBtfName(bid.getBftName());
                        refund.setRefundPrice(bid.getBidBond());
                        refund.setStatus(Refund.REFUND_NOTIFIY);
                        refund.setRemarks("买家已付鞋款");
                        refund.setCreateDate(DateUtils.getCurrentTimeAsString());
                        refundService.insert(refund);
                    }
                }
                Sendsms.sendHuyi(String.valueOf(mobile),account,key,content);
                if(tradeUpdate.getIsBond() == 2){
                    trade.setIsBond(1);
                }
                if(type == 3 && tradeUpdate.getIsBond() == 1){
                    UserAddress u = new UserAddress();
                    u.setId(addressId);
                    UserAddress userAddress = userAddressService.findAddressById(u);
                    //拼接订单的收货地址
                    String detailedAddress = userAddress.getDetailedAddress().replaceAll("-","_");
                    String receivingInformation = userAddress.getLocalArea()+"-"+detailedAddress+"-"+userAddress.getConsigneeName()+"-"+userAddress.getPhoneNumber()+"-"+userAddress.getLandlineNumber();
                    trade.setReceivingInformation(receivingInformation);
                    trade.setUserAddressId(addressId);
                    trade.setTradeFreight(new BigDecimal(yunFee).setScale(2,BigDecimal.ROUND_HALF_UP));
                }
                trade.setStatus(tradeStatus);
                trade.setTransactionDate(DateUtils.dateToString(new Date()));
                tradeService.update(trade);
                if("1".equals(ischeck)){
                    //如果为卖家，将其他卖家的状态修改为关闭
                    Trade trade1 = new Trade();
                    trade1.setBftSize(trade.getBftSize());
                    trade1.setBuyersId(trade.getBuyersId());
                    trade1.setBasicinformationId(trade.getBasicinformationId());
                    trade1.setStatus(trade.TRADE_NOTIFIY_PAY_BOND);
                    List<Trade> tradeList1 = tradeService.findAll(trade1,lagePage);
                    if(tradeList1.size() != 0){
                        for (Trade tde: tradeList1) {
                            tde.setStatus(trade.TRADE_CLOSE);
                            tradeService.update(tde);
                        }
                    }
                }
                return "success";
            }else{
                logger.info("Trade - 支付宝重复回调，订单号 : " + tradeId);
                return "success";
            }
        }else{
            return "fail";
        }
    }

    @RequestMapping("createTradePayAlipay")
    public String createTradePayAlipay(ModelMap modelMap){
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        int type = getParamToInt("type");
        int tradeId = getParamToInt("tradeId");
        int addressId = Integer.parseInt(getParam("addressId","0"));
        double yunFee = Double.valueOf(getParam("yunFee", "0"));
        double amount = Double.valueOf(getParam("amount", "0"));
        String ischeck = getParam("ischeck","0");
        String bname = getParam("bname");
        String isUserHome = getParam("isUserHome");
        Trade record = new Trade();
        record.setId(tradeId);
        List<Trade> tradeList  = tradeService.findAllTrade(record);
        Map<String, String> sParaTemp = new HashMap<String, String>();
        BigDecimal amountFinal;
        if(type == 2){
            amountFinal = bidMoney;
            sParaTemp.put("extra_common_param", uid+"|"+type+"|"+tradeId+"|"+amountFinal+"|"+ischeck+"|"+bname+"|"+isUserHome+"|"+yunFee+"|"+addressId);
            sParaTemp.put("subject", String.valueOf("出售商品:保证金") + bname);
            sParaTemp.put("out_trade_no", "100_"+System.currentTimeMillis()+String.valueOf(tradeId));
            logger.info("当前用户为卖家:"+uid+",发起支付,支付金额为:"+amountFinal);
        }else {
            //如果是线上环境则用下面这句
//            amountFinal =  tradeList.get(0).getTradeFreight().add(tradeList.get(0).getTransactionMoney());
//            amountFinal = tradeList.get(0).getTransactionMoney();
            amountFinal = bidMoney;
            sParaTemp.put("extra_common_param", uid+"|"+type+"|"+tradeId+"|"+amountFinal+"|"+ischeck+"|"+bname+"|"+isUserHome+"|"+yunFee+"|"+addressId);
            sParaTemp.put("subject", String.valueOf("购买商品") + bname);
            sParaTemp.put("out_trade_no", "200_"+System.currentTimeMillis()+String.valueOf(tradeId));
            logger.info("当前用户为买家:"+uid+",发起支付,支付金额为:"+amountFinal);
        }
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", AlipayConfig.payment_type);
        sParaTemp.put("notify_url", AlipayConfig.notify_trade_url);
        sParaTemp.put("return_url", AlipayConfig.return_trade_url);
        sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
        sParaTemp.put("total_fee", String.valueOf(amountFinal.doubleValue()));
        sParaTemp.put("body", "描述");
        modelMap.addAttribute("sParaTemp",sParaTemp);
        return "/common/alipay/alipayapi";
    }

    @RequestMapping("istrade")
    @ResponseBody
    public ResultModel istrade(){
        ResultModel resultModel = new ResultModel();
        String uid = String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID));
        String type = getParam("type");
        String size = getParam("bftSize");
        int bfzId = getParamToInt("basicinformationId");
        Trade record = new Trade();
        record.setBftSize(size);
        record.setBasicinformationId(bfzId);
        if("1".equals(type)){
            record.setStatus(0);
            record.setSellerId(Integer.parseInt(uid));
        }else{
            record.setStatus(1);
            record.setBuyersId(Integer.parseInt(uid));
        }
        List<Trade> tradeList  = tradeService.findAllTrade(record);
        if(tradeList.size() > 0){
            resultModel.setRetCode(2);
            resultModel.setRetMsg("此款宝贝，您已有一笔未支付的订单，是否需要付款");
            resultModel.setData(tradeList.get(0).getId());
            return resultModel;
        }else {
            if(!"1".equals(type)){
                Trade trade = new Trade();
                trade.setBftSize(record.getBftSize());
                trade.setBasicinformationId(record.getBasicinformationId());
                trade.setBuyersId(record.getBuyersId());
                trade.setSellerId(record.getSellerId());
                tradeList = tradeService.findInStatus(trade);
                if (tradeList.size() > 0){
                    resultModel.setRetCode(3);
                    resultModel.setRetMsg("您当前购买鞋款已有正在进行中的订单，您确定需要再购买一双吗？");
                    resultModel.setData(tradeList.get(0).getId());
                    return resultModel;
                }
            }
        }
        resultModel.setRetCode(resultModel.RET_OK);
        return resultModel;
    }

    @RequestMapping("saleRecord")
    public String saleRecord(@Param("bid") Integer bid,ModelMap model){
        setLastPage(0,5);
        Trade trade = new Trade();
        trade.setBasicinformationId(bid);
        List<Trade> tradeList = tradeService.findAllSale(trade,lagePage,null,null);
        List<Basicinformation> bList = basicinformationService.findByType(6);
        model.put("bList",bList);
        model.put("tradeList",tradeList);
        model.put("bname",getParam("bname",""));
        return "/detail/salelist";
    }

    @RequestMapping("saleMarket")
    @ResponseBody
    public List<Point> saleMarket(){
        String bidId = getParam("bidId","");
        String size = getParam("size","");
        String startDate = getParam("startDate","");
        String endDate = getParam("endDate","");
        List<Point> saleMarket = tradeService.tradeHchar(bidId,size,startDate,endDate);
        return saleMarket;
    }

    @RequestMapping("saveTrade")
    @ResponseBody
    public ResultModel saveTrade(Trade record){
        ResultModel resultModel = new ResultModel();
        int i = tradeService.save(record,VstockConfigService.getConfig(IVstockConfigService.TRADE__BOGE_VSTOCK_MD5KEY));
        resultModel.setRetCode(i);
        return resultModel;
    }

    @RequestMapping("getYunfee")
    @ResponseBody
    public ResultModel getYunfee(){
        ResultModel resultModel = new ResultModel();
        Integer addressId = getParamToInt("addressId");
        UserAddress u = new UserAddress();
        u.setId(addressId);
        UserAddress userAddress = userAddressService.findAddressById(u);
        BigDecimal yunFee = tradeService.findAllYunFee(userAddress.getLocalArea());
        if(yunFee == null){
            resultModel.setRetMsg("远程服务器繁忙");
            return resultModel;
        }
        resultModel.setRetCode(resultModel.RET_OK);
        resultModel.setData(yunFee);
        return resultModel;
    }

    @RequestMapping("getBuyInfo")
    @ResponseBody
    public ResultModel getBuyInfo(){
        ResultModel resultModel = new ResultModel();
        Map<String,Object> params = new HashMap<String,Object>();
        setLastPage(0,1);
        int tradeId = getParamToInt("tradeId");
        String size = getParam("size","");
        Trade t = new Trade();
        t.setId(tradeId);
        List<Trade> tradeList = tradeService.findTrade(t,lagePage);
        if(tradeList.size() <= 0){
            resultModel.setRetMsg("订单失效或不存在");
            return resultModel;
        }
        Trade trade = tradeList.get(0);
        Integer bidId = trade.getBasicinformationId();
        Basicinformation b = new Basicinformation();
        b.setId(String.valueOf(bidId));
        Basicinformation basicinformation = basicinformationService.findObj(b);
        PricePeak pricePeak1 = pricePeakService.getHighestAndlowest(bidId,size,1,lagePage);
        PricePeak pricePeak2 = pricePeakService.getHighestAndlowest(bidId,size,2,lagePage);
        if( WebUtils.getSessionAttribute(request, User.SESSION_USER_ID) != null){
            int uuid = Integer.parseInt(String.valueOf(WebUtils.getSessionAttribute(request, User.SESSION_USER_ID)));
            UserAddress r = new UserAddress();
            int startPos = 3;
            String type = "0";
            r.setUserId(uuid);
            List<UserAddress> addressesList = userAddressService.findAllUserAddress(r,startPos,type);
            params.put("userAddressesList",addressesList);
        }
        params.put("pricePeak1",pricePeak1);
        params.put("pricePeak2",pricePeak2);
        params.put("basicinformation",basicinformation);
        params.put("trade",trade);
        resultModel.setData(params);
        return resultModel;
    }
}
