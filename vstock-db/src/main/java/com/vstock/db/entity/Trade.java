package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by administor on 2016/12/7.
 */
public class Trade implements Serializable {


    public final static String TRADE_MD5_MARK = "|";

    public final static String TRADE_MD5_MARK_NOTIFY = "^|^";

    public final static String[] statuses = {"0:待支付保证金","1:待支付鞋款","2:已支付待发货","10:已发货待检验","20:检验通过","21:检验未通过","30:已发货待签收","40:交易完成","41:交易关闭","50:订单删除","51:买家删除","52:卖家删除","60:退货中","61:退货成功"};

    public final static String[] tradeStatuses = {"0:待支付","1:待支付","2:待卖家寄出","10:待验货","11:支付失败","20:待验货","21:验货退回","30:待确认收货","40:交易完成","41:交易关闭","50:交易关闭","51:交易关闭","52:交易关闭","60:退货中","61:退货成功"};

    public final static int TRADE_NOTIFIY_PAY_BOND = 0; //待支付保证金
    public final static int TRADE_NOTIFIY_PAY = 1;      //已下单待支付
    public final static int TRADE_PAY_LOGISTICS = 2;    //已支付待发货
    public final static int TRADE_NOTIFIY_TEST = 10;    //已发货待检验
    public final static int TRADE_TEST_SUCESS = 20;     //检验通过
    public final static int TRADE_TEST_FILE = 21;       //检验失败
    public final static int TRADE_WAIT_TAKE = 30;       //已发货待签收
    public final static int TRADE_SUCESS = 40;          //交易成功
    public final static int TRADE_CLOSE = 41;           //交易失败
    public final static int TRADE_DEL = 50;           //订单删除

    @Id
    private Integer id;
    private String tradeNo;
    private Integer sellerId;
    private Integer buyersId;
    private Integer bidId;
    private Integer basicinformationId;
    private Integer userAddressId;
    private BigDecimal transactionMoney;
    private BigDecimal tradeFreight;
    private String courierNumber;
    private Integer status;
    private String transactionDate;
    private String updateDate;
    private String sign;
    private Bid bid;
    private Payment payment;
    private String bftName;
    private String bftSize;
    private String sellerName;
    private String buyersName;
    private String companyName;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public Trade() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getBuyersId() {
        return buyersId;
    }

    public void setBuyersId(Integer buyersId) {
        this.buyersId = buyersId;
    }

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Integer getBasicinformationId() {
        return basicinformationId;
    }

    public void setBasicinformationId(Integer basicinformationId) {
        this.basicinformationId = basicinformationId;
    }

    public BigDecimal getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(BigDecimal transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

    public BigDecimal getTradeFreight() {
        return tradeFreight;
    }

    public void setTradeFreight(BigDecimal tradeFreight) {
        this.tradeFreight = tradeFreight;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sgin) {
        this.sign = sgin;
    }

    public String getBftName() {
        return bftName;
    }

    public void setBftName(String bftName) {
        this.bftName = bftName;
    }

    public String getBftSize() {
        return bftSize;
    }

    public void setBftSize(String bftSize) {
        this.bftSize = bftSize;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBuyersName() {
        return buyersName;
    }

    public void setBuyersName(String buyersName) {
        this.buyersName = buyersName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Integer userAddressId) {
        this.userAddressId = userAddressId;
    }

    public Trade(Integer id, String tradeNo, Integer sellerId, Integer buyersId, Integer bidId, Integer basicinformationId, Integer userAddressId, BigDecimal transactionMoney, BigDecimal tradeFreight, String courierNumber, Integer status, String transactionDate, String updateDate, String sign, Bid bid, String bftName, String bftSize, String sellerName, String buyersName, String companyName) {
        this.id = id;
        this.tradeNo = tradeNo;
        this.sellerId = sellerId;
        this.buyersId = buyersId;
        this.bidId = bidId;
        this.basicinformationId = basicinformationId;
        this.userAddressId = userAddressId;
        this.transactionMoney = transactionMoney;
        this.tradeFreight = tradeFreight;
        this.courierNumber = courierNumber;
        this.status = status;
        this.transactionDate = transactionDate;
        this.updateDate = updateDate;
        this.sign = sign;
        this.bid = bid;
        this.bftName = bftName;
        this.bftSize = bftSize;
        this.sellerName = sellerName;
        this.buyersName = buyersName;
        this.companyName = companyName;
    }

    public Trade(int userAddressId,BigDecimal tradeFreight, String bftSize, String updateDate, String transactionDate, Integer status, BigDecimal transactionMoney, Integer basicinformationId, Integer bidId, Integer buyersId, Integer sellerId, String trandeNo) {
        this.userAddressId = userAddressId;
        this.tradeFreight = tradeFreight;
        this.bftSize = bftSize;
        this.updateDate = updateDate;
        this.transactionDate = transactionDate;
        this.status = status;
        this.transactionMoney = transactionMoney;
        this.basicinformationId = basicinformationId;
        this.bidId = bidId;
        this.buyersId = buyersId;
        this.sellerId = sellerId;
        this.tradeNo = tradeNo;
    }
}
