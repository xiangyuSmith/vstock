package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by xiangyu on 2016/7/6.
 */
public class ResultDataFactory implements Serializable {

    @Id
    private String id;
    private int bid;
    private String commodityDataId;
    private String productName;
    private String brand;
    private String girard;
    private String sizePrice;
    private String transactionRecord;
    private String createTime;
    private String reservedField;
    private String countTransactionRecord;
    private String difference;

    public ResultDataFactory() {
    }

    public ResultDataFactory(String id, int bid, String commodityDataId, String productName, String brand, String girard, String sizePrice, String transactionRecord, String createTime, String reservedField, String countTransactionRecord, String difference) {
        this.id = id;
        this.bid = bid;
        this.commodityDataId = commodityDataId;
        this.productName = productName;
        this.brand = brand;
        this.girard = girard;
        this.sizePrice = sizePrice;
        this.transactionRecord = transactionRecord;
        this.createTime = createTime;
        this.reservedField = reservedField;
        this.countTransactionRecord = countTransactionRecord;
        this.difference = difference;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getCountTransactionRecord() {
        return countTransactionRecord;
    }

    public void setCountTransactionRecord(String countTransactionRecord) {
        this.countTransactionRecord = countTransactionRecord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getCommodityDataId() {
        return commodityDataId;
    }

    public void setCommodityDataId(String commodityDataId) {
        this.commodityDataId = commodityDataId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGirard() {
        return girard;
    }

    public void setGirard(String girard) {
        this.girard = girard;
    }

    public String getSizePrice() {
        return sizePrice;
    }

    public void setSizePrice(String sizePrice) {
        this.sizePrice = sizePrice;
    }

    public String getTransactionRecord() {
        return transactionRecord;
    }

    public void setTransactionRecord(String transactionRecord) {
        this.transactionRecord = transactionRecord;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReservedField() {
        return reservedField;
    }

    public void setReservedField(String reservedField) {
        this.reservedField = reservedField;
    }
}
