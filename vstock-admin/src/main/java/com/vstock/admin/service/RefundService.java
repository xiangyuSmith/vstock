package com.vstock.admin.service;

import com.vstock.db.dao.IPaymentDao;
import com.vstock.db.dao.IRefundDao;
import com.vstock.db.dao.IUserAccountDao;
import com.vstock.db.entity.*;
import com.vstock.ext.util.DateUtils;
import com.vstock.ext.util.OddNoUtil;
import com.vstock.ext.util.Page;
import com.vstock.server.alipay.service.AlipayFundTransfer;
import com.vstock.server.alipay.service.AlipayRefund;
import com.vstock.server.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class RefundService {
    @Autowired
    IRefundDao refundDao;

    @Autowired
    IPaymentDao paymentDao;

    @Autowired
    IUserAccountDao userAccountDao;

    @Autowired
    BidService bidService;

    @Autowired
    TradeService tradeService;

    /**
     * 查询支付记录
     * @param record
     * @return
     */
    public Payment findPayment(Payment record){
        return paymentDao.findByTrade(record);
    }

    /**
     * 查询所有支付记录
     * @param record
     * @return
     */
    public List<Payment> findPaymentAll(Payment record){
        Page page = new Page(10,"1");
        return paymentDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询所有记录
     * @param record
     * @return
     */
    public List<Refund> findAll(Refund record, Page page){
        return refundDao.findAll(record,page.getStartPos(),page.getPageSize());
    }

    /**
     * 查询一个对象
     * @param record
     * @return
     */
    public Refund find(Refund record){return refundDao.find(record);}

    /**
     * 查询所有总数
     * @param record
     * @return
     */
    public int findCount(Refund record){return refundDao.findCount(record);}

    /**
     * 新增
     * @param record
     * @return
     */
    public int insert(Refund record){return refundDao.insert(record);}

    /**
     * 修改
     * @param record
     * @return
     */
    public int update(Refund record){return refundDao.update(record);}

    /**
     * 根据时间区间分页查询
     * @param record    对象
     * @param page      分页
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public List<Refund> findAllDate(Refund record,Page page,String startTime,String endTime){
        return refundDao.findAllDate(record,startTime,endTime,page.getStartPos(),page.getPageSize());
    }

    /**
     * 根据时间区间查询所有
     * @param record       对象
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @return
     */
    public int findCountDate(Refund record,String startTime,String endTime){return refundDao.findCountDate(record,startTime,endTime);}

    /**
     * 保存方法
     * @param record
     * @return
     */
    public int save(Refund record){
        if (record.getId() != null){
            return this.update(record);
        }else {
            if (record.getCreateDate() == null || "".equals(record.getCreateDate())){
                record.setCreateDate(DateUtils.getCurrentTimeAsString());
            }
            if (record.getRefundNo() == null || "".equals(record.getRefundNo())){
                record.setRefundNo(OddNoUtil.refundNo());
            }
            return this.insert(record);
        }
    }

    /**
     * 验货不合格退回金额，生成退款单
     * @param record
     * @return
     */
    public int refundLiquidated(Trade record, String remarks){
        int i = 0;
        Bid bid = new Bid();
        bid.setId(record.getBidId());
        Page page = new Page(10,"1");
        List<Bid> bidList = bidService.findAll(bid,page);
        for (int a = 0; a <= bidList.size(); a++ ) {
            Refund refund = new Refund();
            refund.setTradeNo(record.getTradeNo());
            refund.setRefundObj("1");
            refund.setBtfId(record.getBasicinformationId());
            refund.setBtfName(record.getBftName());
            if (a == 0) {
                refund.setRefundPrice(record.getTransactionMoney().add(record.getTradeFreight()));
                refund.setType("0");
            }else {
                refund.setRefundPrice(bidList.get(0).getBidBond());
                refund.setType("6");
            }
            refund.setRemarks(remarks);
            refund.setStatus("0");
            i = this.save(refund);
        }
        return i;
    }

    /**
     * 传入ID和类型，查询退款的金额
     * @param id
     * @param type
     * @return
     */
    public BigDecimal refundAmount(int id, int type){
        BigDecimal amount = new BigDecimal(0);
        if (type == 1){
            Bid record = new Bid();
            record.setId(id);
            Page page = new Page(10,"1");
            List<Bid> bidList = bidService.findAll(record,page);
            if (bidList.size() > 0){
                amount = bidList.get(0).getBidBond();
            }
        }else{
            Trade record = new Trade();
            record.setId(id);
            List<Trade> tradeList = tradeService.findModel(record);
            if (tradeList.size() > 0) {
                if (type == 0) {
                    amount = tradeList.get(0).getTransactionMoney();
                }else{
                    amount = tradeList.get(0).getBid().getBidBond();
                }
            }
        }
        return amount;
    }

    /**
     * 修改退款单状态和叫价单或者订单的状态
     * @param record    退款单对象
     * @param upstatus  修改状态
     * @param tradeId   订单ID
     * @return
     */
    public int refundAndTransfer(Refund record,String upstatus,String tradeId){
        //判断退款订单是否修改过
        int a = this.findCount(record);
        if ( a == 0) {
            a = this.save(record);
            if (a > 0) {
                if (upstatus != null && !"".equals(upstatus) && (Integer.parseInt(record.getType()) == 1 || Integer.parseInt(record.getType()) == 3)) {
                    Bid bid = new Bid();
                    bid.setId(Integer.parseInt(record.getTradeNo()));
                    bid.setStatus(upstatus);
                    a = bidService.update(bid);
                } else {
                    Trade trade = new Trade();
                    if (tradeId == null || "".equals(tradeId)) {
                        Trade trades = new Trade();
                        trades.setTradeNo(record.getTradeNo());
                        trades = tradeService.findTrade(trades);
                        trade.setId(trades.getId());
                    } else {
                        trade.setId(Integer.parseInt(tradeId));
                    }
                    trade.setStatus(Integer.parseInt(upstatus));
                    a = tradeService.update(trade);
                }
            }
            return a;
        }
        return a;
    }

    /**
     * 单笔退款方法
     * @param record
     * @param upstatus
     * @return
     */
    public Map<String, String> refundObj(Refund record, String upstatus){
        List<Refund> refundList = StatusUtil.refundType();
        String url = "?id="+record.getId()+"&tradeNo="+record.getTradeNo()+"&type="+record.getType()+"&status="+record.getStatus()+"&upstatus="+upstatus;
        String detail_data ="";
        Refund refundf = new Refund();
        refundf.setId(record.getId());
        refundf = this.find(refundf);
//        refundf.setRefundPrice(new BigDecimal(0.01));
        //判断是叫价退款、还是订单退款
        if (Integer.parseInt(record.getType()) == 1 || Integer.parseInt(record.getType()) == 3){
            Bid bid = new Bid();
            bid.setId(Integer.parseInt(record.getTradeNo()));
            Page page = new Page(10,"1");
            List<Bid> bidList = bidService.findBid(bid,page);
            if (bidList.size() > 0){
                bid = bidList.get(0);
                Payment payment = new Payment();
                payment.setId(bid.getPaymentId());
                payment = this.findPayment(payment);
                if (payment != null){
                    for (Refund  refund : refundList) {
                        if (refund.getType().equals(record.getType())) {
                            detail_data = payment.getPayment_number() + "^" + refundf.getRefundPrice().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + "^"+refund.getBtfName();
                        }
                    }
                }
            }
        }else {
            Trade trade = new Trade();
            trade.setTradeNo(record.getTradeNo());
            trade = tradeService.findTrade(trade);
            Payment payment = new Payment();
            payment.setPayment_status(10);
            //判断是否是卖家叫价出售
            if (Integer.parseInt(record.getType()) == 4){
                Bid bid = new Bid();
                bid.setId(trade.getBidId());
                Page page = new Page(10,"1");
                List<Bid> bidList = bidService.findAll(bid,page);
                if (bidList.size() > 0){
                    if ("0".equals(bidList.get(0).getType())) {
                        payment.setId(bidList.get(0).getPaymentId());
                        payment = this.findPayment(payment);
                    }else {
                        payment.setOrder_record_id(trade.getId());
                        List<Payment> paymentList = this.findPaymentAll(payment);
                        //判断订单是否绑定两笔支付成功的记录
                        if (paymentList.size() > 1) {
                            payment = paymentList.get(1);
                        } else {
                            payment = paymentList.get(0);
                        }
                    }
                }
            }else {
                payment.setOrder_record_id(trade.getId());
                List<Payment> paymentList = this.findPaymentAll(payment);
                //判断订单是否绑定两笔支付成功的记录
                if (paymentList.size() > 1) {
                    payment = paymentList.get(1);
                } else {
                    payment = paymentList.get(0);
                }
            }
            if (payment != null){
                for (Refund  refund : refundList) {
                    if (refund.getType().equals(record.getType())) {
                        detail_data = payment.getPayment_number() + "^" + refundf.getRefundPrice().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + "^"+refund.getBtfName();
                    }
                }
            }
        }
        return AlipayRefund.refund("1",detail_data,url);
    }

    /**
     * 转账借口
     * @param record  退款单对象
     * @return
     */
    public int transferAccountsObj(Refund record){
        int i = 0;
        List<Refund> refundList = StatusUtil.refundType();
        Refund refund = new Refund();
        refund.setId(record.getId());
        refund = this.find(refund);
        if (refund != null){
            Trade trade = new Trade();
            trade.setTradeNo(refund.getTradeNo());
            trade = tradeService.findTrade(trade);
            if (trade != null){
                UserAccount userAccount = new UserAccount();
                if ("0".equals(refund.getRefundObj())){
                    userAccount = userAccountDao.findAccountByUid(trade.getSellerId().toString());
                }else {
                    userAccount = userAccountDao.findAccountByUid(trade.getSellerId().toString());
                    //判断个人身份认证信息中是否有支付宝账号
                    if (userAccount == null){
                        Payment payment = new Payment();
                        payment.setOrder_record_id(trade.getId());
                        payment.setPayment_user_id(trade.getBuyersId());
                        payment.setPayment_status(10);
                        Page page = new Page(10,"1");
                        List<Payment> paymentList = paymentDao.findAll(payment,page.getStartPos(),page.getPageSize());
                        if (paymentList.size() > 0){//查找最近一条付款记录的支付宝账号，为买家账号转账
                            userAccount.setAlipay_account(paymentList.get(paymentList.size()-1).getPayment_alipay_name());
                        }
                    }
                }
                for (Refund refundT : refundList) {
                    if (refundT.getType().equals(refund.getType()))
                    i = AlipayFundTransfer.alipayfundServer(refund.getRefundNo(), userAccount.getAlipay_account(), refund.getRefundPrice().setScale(2,BigDecimal.ROUND_HALF_UP).toString(), refund.getBtfName(), refundT.getBtfName());
                }
            }
        }
        return i;
    }

    public String linkAddress(Refund record,String startTime,String endTime, String linkAddress){
        if (startTime != null && !"".equals(startTime)){
            linkAddress = linkAddress + "&startTime="+startTime;
        }
        if (endTime != null && !"".equals(endTime)){
            linkAddress = linkAddress + "&endTime="+endTime;
        }
        if (record.getRefundNo() != null && !"".equals(record.getRefundNo())){
            linkAddress = linkAddress + "&refundNo="+record.getRefundNo();
        }
        if (record.getStatus() != null && !"".equals(record.getStatus())){
            linkAddress = linkAddress + "&status="+record.getStatus();
        }
        if (record.getType() != null && !"".equals(record.getType())){
            linkAddress = linkAddress + "&type()="+record.getType();
        }
        if (record.getRefundObj() != null && !"".equals(record.getRefundObj())){
            linkAddress = linkAddress + "&refundObj()="+record.getRefundObj();
        }
        if (record.getBtfName() != null && !"".equals(record.getBtfName())){
            linkAddress = linkAddress + "&btfName()="+record.getBtfName();
        }
        if (record.getTradeNo()!= null && !"".equals(record.getTradeNo())){
            linkAddress = linkAddress + "&tradeNo()="+record.getTradeNo();
        }
        return linkAddress;
    }

}
