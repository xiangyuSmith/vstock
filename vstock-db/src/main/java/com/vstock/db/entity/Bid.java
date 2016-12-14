package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

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
    private String termValidity;
    private int status;
    private int type;
    private String sign;
    private Date bidDate;
    private Date invalidDate;
    private BigDecimal highestBid;
    private BigDecimal minimumSellingPrice;
    private Basicinformation basicinformation;

    public Bid() {
        super();
    }

    public Bid(Integer id, Integer userId, Integer basicinformationId, Integer paymentId, String bftName, String bftSize, BigDecimal bidMoney, BigDecimal bidFreight, BigDecimal bidBond, BigDecimal latelyBid, String termValidity, int status, int type, String sign, Date bidDate, Date invalidDate, BigDecimal highestBid, BigDecimal minimumSellingPrice, Basicinformation basicinformation) {
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
        this.termValidity = termValidity;
        this.status = status;
        this.type = type;
        this.sign = sign;
        this.bidDate = bidDate;
        this.invalidDate = invalidDate;
        this.highestBid = highestBid;
        this.minimumSellingPrice = minimumSellingPrice;
        this.basicinformation = basicinformation;
    }

    public Basicinformation getBasicinformation() {
        return basicinformation;
    }

    public void setBasicinformation(Basicinformation basicinformation) {
        this.basicinformation = basicinformation;
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

    public String getTermValidity() {
        return termValidity;
    }

    public void setTermValidity(String termValidity) {
        this.termValidity = termValidity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public BigDecimal getMinimumSellingPrice() {
        return minimumSellingPrice;
    }

    public void setMinimumSellingPrice(BigDecimal minimumSellingPrice) {
        this.minimumSellingPrice = minimumSellingPrice;
    }
}
