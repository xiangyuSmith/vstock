package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by administor on 2016/5/12.
 */
public class CommodityData {

    @Id
    private String id;
    private int bid;
    private String stockxId;
    private String stockxName;
    private String commodityName;
    private String commodityPrice;
    private String transactionRecord;
    private String colorSort;
    private String girard;
    private String brand;
    private String productUrl;

    public CommodityData() {}

    public String getColorSort() {
        return colorSort;
    }

    public void setColorSort(String colorSort) {
        this.colorSort = colorSort;
    }

    public String getGirard() {
        return girard;
    }

    public void setGirard(String girard) {
        this.girard = girard;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockxName() {
        return stockxName;
    }

    public void setStockxName(String stockxName) {
        this.stockxName = stockxName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getTransactionRecord() {
        return transactionRecord;
    }

    public void setTransactionRecord(String transactionRecord) {
        this.transactionRecord = transactionRecord;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStockxId() {
        return stockxId;
    }

    public void setStockxId(String stockxId) {
        this.stockxId = stockxId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public CommodityData(String id, int bid, String stockxId, String stockxName, String commodityName, String commodityPrice, String transactionRecord, String colorSort, String girard, String brand, String productUrl, String createDate) {
        this.id = id;
        this.bid = bid;
        this.stockxId = stockxId;
        this.stockxName = stockxName;
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
        this.transactionRecord = transactionRecord;
        this.colorSort = colorSort;
        this.girard = girard;
        this.brand = brand;
        this.productUrl = productUrl;
        this.createDate = createDate;
    }
}
