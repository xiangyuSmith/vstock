package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by administor on 2016/12/7.
 */
public class Trade implements Serializable {
    @Id
    private Integer id;
    private Integer sellerId;
    private Integer buyersId;
    private Integer bidId;
    private Integer basicinformationId;
    private BigDecimal transactionMoney;
    private BigDecimal tradeFreight;
    private String status;
    private Date transactionDate;
    private Date endDate;
    private String sign;
    private Bid bid;
    private String bftName;
    private String bftSize;

    public Trade(Integer id, Integer sellerId, Integer buyersId, Integer bidId, Integer basicinformationId, BigDecimal transactionMoney, BigDecimal tradeFreight, String status, Date transactionDate, Date endDate, String sign, Bid bid) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyersId = buyersId;
        this.bidId = bidId;
        this.basicinformationId = basicinformationId;
        this.transactionMoney = transactionMoney;
        this.tradeFreight = tradeFreight;
        this.status = status;
        this.transactionDate = transactionDate;
        this.endDate = endDate;
        this.sign = sign;
        this.bid = bid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
}
