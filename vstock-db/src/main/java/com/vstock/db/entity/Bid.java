package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by administor on 2016/12/6.
 */
public class Bid implements Serializable {
    @Id
    private Integer id;
    private Integer userId;
    private Integer basicinformationId;
    private Integer paymentId;
    private String bftName;
    private String bftSize;
    private BigDecimal bidMoney;
    private BigDecimal bidFreight;
    private BigDecimal bidBond;
    private BigDecimal latelyBid;
    private String status;
    private String type;
    private String sign;
    private Date bidDate;
    private Date invalidDate;

    public Bid() {
        super();
    }

    public Bid(Integer id, Integer userId, Integer basicinformationId, Integer paymentId, String bftName, String bftSize, BigDecimal bidMoney, BigDecimal bidFreight, BigDecimal bidBond, BigDecimal latelyBid, String status, String type, String sign, Date bidDate, Date invalidDate) {
        this.id = id;
        this.userId = userId;
        this.basicinformationId = basicinformationId;
        this.paymentId = paymentId;
        this.bftName = bftName;
        this.bftSize = bftSize;
        this.bidMoney = bidMoney;
        this.bidFreight = bidFreight;
        this.bidBond = bidBond;
        this.latelyBid = latelyBid;
        this.status = status;
        this.type = type;
        this.sign = sign;
        this.bidDate = bidDate;
        this.invalidDate = invalidDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBasicinformationId() {
        return basicinformationId;
    }

    public void setBasicinformationId(Integer basicinformationId) {
        this.basicinformationId = basicinformationId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
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

    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public BigDecimal getBidFreight() {
        return bidFreight;
    }

    public void setBidFreight(BigDecimal bidFreight) {
        this.bidFreight = bidFreight;
    }

    public BigDecimal getBidBond() {
        return bidBond;
    }

    public void setBidBond(BigDecimal bidBond) {
        this.bidBond = bidBond;
    }

    public BigDecimal getLatelyBid() {
        return latelyBid;
    }

    public void setLatelyBid(BigDecimal latelyBid) {
        this.latelyBid = latelyBid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }
}
