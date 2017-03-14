package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by administor on 2016/12/8.
 */
public class Refund implements Serializable {

    public final static String[] refundType = {"0:验货失败","1:叫价失败","2:交易成功","3:买家叫价，交易达成","4:卖家退保证金","5:买家退鞋款","6:违约金"};
    public final static String[] refundstatus = {"0:待退款","1:已退款"};
    public final static String[] refundO = {"0:卖家","1:买家"};
    public final static String refNo = "STXRN";
    public final static String REFUND_NOTIFIY = "0";     //待退款
    public final static String REFUND_SUCESS = "1";    //已退款

    @Id
    private Integer id;
    private String refundNo;
    private String tradeNo;
    private String refundObj;
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

    public Refund(Integer id, String refundNo, String tradeNo, String refundObj, Integer btfId, String btfName, BigDecimal refundPrice, String status, String type, String createDate, String remarks) {
        this.id = id;
        this.refundNo = refundNo;
        this.tradeNo = tradeNo;
        this.refundObj = refundObj;
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

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getRefundObj() {
        return refundObj;
    }

    public void setRefundObj(String refundObj) {
        this.refundObj = refundObj;
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
