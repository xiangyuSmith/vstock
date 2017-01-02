package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

public class BackCommodity {

    public final static String[] statuses= {"0:申请退货","10:审核通过","11:审核失败","20:已发货待签收","30:交易完成","40:交易失败"};

    @Id
    private Integer id;
    private Integer btfId;
    private String tradeNo;
    private String backPerson;
    private String consignee;
    private Integer status;
    private String express;
    private String courierNumber;
    private String createTime;
    private String updateTime;
    private String reasons;
    private String btfName;

    public BackCommodity(Integer id, Integer btfId, String tradeNo, String backPerson, String consignee, Integer status, String express, String courierNumber, String createTime, String updateTime, String reasons, String btfName) {
        this.id = id;
        this.btfId = btfId;
        this.tradeNo = tradeNo;
        this.backPerson = backPerson;
        this.consignee = consignee;
        this.status = status;
        this.express = express;
        this.courierNumber = courierNumber;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.reasons = reasons;
        this.btfName = btfName;
    }

    public BackCommodity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBtfId() {
        return btfId;
    }

    public void setBtfId(Integer btfId) {
        this.btfId = btfId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getBackPerson() {
        return backPerson;
    }

    public void setBackPerson(String backPerson) {
        this.backPerson = backPerson;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getBtfName() {
        return btfName;
    }

    public void setBtfName(String btfName) {
        this.btfName = btfName;
    }
}
