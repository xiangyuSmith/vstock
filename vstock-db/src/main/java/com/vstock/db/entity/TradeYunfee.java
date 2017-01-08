package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class TradeYunfee {

    @Id
    private String id;

    private String cityName;

    private BigDecimal feeMoney;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public BigDecimal getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(BigDecimal feeMoney) {
        this.feeMoney = feeMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TradeYunfee() {
    }

    public TradeYunfee(String id, String cityName, BigDecimal feeMoney, String status) {
        this.id = id;
        this.cityName = cityName;
        this.feeMoney = feeMoney;
        this.status = status;
    }

    public TradeYunfee(String id, String cityName, BigDecimal feeMoney) {
        this.id = id;
        this.cityName = cityName;
        this.feeMoney = feeMoney;
    }
}
