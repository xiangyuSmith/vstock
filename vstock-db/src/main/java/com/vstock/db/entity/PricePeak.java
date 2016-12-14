package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by administor on 2016/12/8.
 */
public class PricePeak implements Serializable {
    @Id
    private Integer id;
    private Integer basicinformationId;
    private String peakSize;
    private BigDecimal highestBid;
    private BigDecimal minimumSellingPrice;
    private int status;
    private String highestBidderId;
    private String minimumSellingId;
    private Date createDate;
    private Date invalidDate;

    public PricePeak() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBasicinformationId() {
        return basicinformationId;
    }

    public void setBasicinformationId(Integer basicinformationId) {
        this.basicinformationId = basicinformationId;
    }

    public String getPeakSize() {
        return peakSize;
    }

    public void setPeakSize(String peakSize) {
        this.peakSize = peakSize;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHighestBidderId() {
        return highestBidderId;
    }

    public void setHighestBidderId(String highestBidderId) {
        this.highestBidderId = highestBidderId;
    }

    public String getMinimumSellingId() {
        return minimumSellingId;
    }

    public void setMinimumSellingId(String minimumSellingId) {
        this.minimumSellingId = minimumSellingId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public PricePeak(Integer id, Integer basicinformationId, String peakSize, BigDecimal highestBid, BigDecimal minimumSellingPrice, int status, String highestBidderId, String minimumSellingId, Date createDate, Date invalidDate) {
        this.id = id;
        this.basicinformationId = basicinformationId;
        this.peakSize = peakSize;
        this.highestBid = highestBid;
        this.minimumSellingPrice = minimumSellingPrice;
        this.status = status;
        this.highestBidderId = highestBidderId;
        this.minimumSellingId = minimumSellingId;
        this.createDate = createDate;
        this.invalidDate = invalidDate;
    }
}
