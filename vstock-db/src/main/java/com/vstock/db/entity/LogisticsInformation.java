package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class LogisticsInformation implements Serializable {

    @Id
    private Integer id;
    private Integer tradeId;
    private String companyName;
    private String courierNumber;
    private String type;
    private String information;
    private String status;
    private String createDate;
    private String invalidDate;
    private String sign;
    private String logisticsExplain;

    public LogisticsInformation() {
        super();
    }

    public LogisticsInformation(Integer id, Integer tradeId, String companyName, String courierNumber, String type, String information, String status, String createDate, String invalidDate, String sign, String logisticsExplain) {
        this.id = id;
        this.tradeId = tradeId;
        this.companyName = companyName;
        this.courierNumber = courierNumber;
        this.type = type;
        this.information = information;
        this.status = status;
        this.createDate = createDate;
        this.invalidDate = invalidDate;
        this.sign = sign;
        this.logisticsExplain = logisticsExplain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getLogisticsExplain() {
        return logisticsExplain;
    }

    public void setLogisticsExplain(String logisticsExplain) {
        this.logisticsExplain = logisticsExplain;
    }
}
