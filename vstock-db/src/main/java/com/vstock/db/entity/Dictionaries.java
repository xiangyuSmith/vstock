package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by xiangyu on 2016/7/5.
 */
public class Dictionaries implements Serializable {

    @Id
    private String id;
    private int bid;
    private String commodityDataId;
    private String colorly;
    private String identification;
    private String girard;
    private String createTime;
    private String updatetime;
    private String updateUser;
    private String status;
    private String reservedField;
    private String reservedFieldT;
    private CommodityData commodityData;

    public Dictionaries() {
    }

    public Dictionaries(String id, int bid, String commodityDataId, String colorly, String identification, String girard, String createTime, String updatetime, String updateUser, String status, String reservedField, String reservedFieldT, CommodityData commodityData) {
        this.id = id;
        this.bid = bid;
        this.commodityDataId = commodityDataId;
        this.colorly = colorly;
        this.identification = identification;
        this.girard = girard;
        this.createTime = createTime;
        this.updatetime = updatetime;
        this.updateUser = updateUser;
        this.status = status;
        this.reservedField = reservedField;
        this.reservedFieldT = reservedFieldT;
        this.commodityData = commodityData;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityDataId() {
        return commodityDataId;
    }

    public void setCommodityDataId(String commodityDataId) {
        this.commodityDataId = commodityDataId;
    }

    public String getColorly() {
        return colorly;
    }

    public void setColorly(String colorly) {
        this.colorly = colorly;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getReservedField() {
        return reservedField;
    }

    public void setReservedField(String reservedField) {
        this.reservedField = reservedField;
    }

    public String getReservedFieldT() {
        return reservedFieldT;
    }

    public void setReservedFieldT(String reservedFieldT) {
        this.reservedFieldT = reservedFieldT;
    }

    public CommodityData getCommodityData() {
        return commodityData;
    }

    public void setCommodityData(CommodityData commodityData) {
        this.commodityData = commodityData;
    }

    public String getGirard() {
        return girard;
    }

    public void setGirard(String girard) {
        this.girard = girard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
