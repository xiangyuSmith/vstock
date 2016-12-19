package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by administor on 2016/12/7.
 */
public class Trade implements Serializable {

    public final static String[] statuses = {"0:已下单待支付","1:已支付待发货","10:已发货待检验","20:检验通过","21:检验未通过","30:已发货待签收","40:交易完成","41:交易关闭"};

    @Id
    private Integer id;
    private Integer sellerId;
    private Integer buyersId;
    private Integer bidId;
    private Integer basicinformationId;
    private BigDecimal transactionMoney;
    private BigDecimal tradeFreight;
    private String courierNumber;
    private Integer status;
    private String transactionDate;
    private String endDate;
    private String sign;
    private Bid bid;
    private String bftName;
    private String bftSize;
    private String sellerName;
    private String buyersName;

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

    public void setBidId(Integer priceRecordId) {
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Trade(Integer id, Integer sellerId, Integer buyersId, Integer bidId, Integer basicinformationId, BigDecimal transactionMoney, BigDecimal tradeFreight, String courierNumber, Integer status, String transactionDate, String endDate, String sign, Bid bid, String bftName, String bftSize, String sellerName, String buyersName) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyersId = buyersId;
        this.bidId = bidId;
        this.basicinformationId = basicinformationId;
        this.transactionMoney = transactionMoney;
        this.tradeFreight = tradeFreight;
        this.courierNumber = courierNumber;
        this.status = status;
        this.transactionDate = transactionDate;
        this.endDate = endDate;
        this.sign = sign;
        this.bid = bid;
        this.bftName = bftName;
        this.bftSize = bftSize;
        this.sellerName = sellerName;
        this.buyersName = buyersName;
    }
}
