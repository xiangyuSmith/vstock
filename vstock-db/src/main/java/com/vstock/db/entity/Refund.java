package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by administor on 2016/12/8.
 */
public class Refund implements Serializable {
    @Id
    private Integer id;
    private String tradeNo;
    private String payee;
    private Integer btfId;
    private String btfName;
    private BigDecimal refundPrice;
    private String status;
    private String type;
    private String createDate;
    private String remarks;

    public Refund() {
        super();
    }

    public Refund(Integer id, String tradeNo, String payee, Integer btfId, String btfName, BigDecimal refundPrice, String status, String type, String createDate, String remarks) {
        this.id = id;
        this.tradeNo = tradeNo;
        this.payee = payee;
        this.btfId = btfId;
        this.btfName = btfName;
        this.refundPrice = refundPrice;
        this.status = status;
        this.type = type;
        this.createDate = createDate;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Integer getBtfId() {
        return btfId;
    }

    public void setBtfId(Integer btfId) {
        this.btfId = btfId;
    }

    public String getBtfName() {
        return btfName;
    }

    public void setBtfName(String btfName) {
        this.btfName = btfName;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
