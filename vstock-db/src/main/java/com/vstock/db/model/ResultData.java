package com.vstock.db.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by xiangyu on 2016/7/6.
 */
public class ResultData implements Serializable {

    private static final long serialVersionUID = 2123562132187L;

    @Id
    private String id;
    private String commodityDataId;
    private String storeId;
    private String storeName;
    private String productName;
    private String brand;
    private String girard;
    private String sizePrice;
    private String transactionRecord;
    private String createTime;
    private String reservedField;

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

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getReservedField() {
        return reservedField;
    }

    public void setReservedField(String reservedField) {
        this.reservedField = reservedField;
    }


    public ResultData() {
    }

    public ResultData(String id, String commodityDataId, String storeId, String storeName, String productName, String brand, String girard, String sizePrice, String transactionRecord, String createTime, String reservedField) {
        this.id = id;
        this.commodityDataId = commodityDataId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.productName = productName;
        this.brand = brand;
        this.girard = girard;
        this.sizePrice = sizePrice;
        this.transactionRecord = transactionRecord;
        this.createTime = createTime;
        this.reservedField = reservedField;
    }

    @Override
    public String toString() {
        return "{COMMODITYDATA:{id:"+this.id+",commodityDataId:"+this.commodityDataId+",storeId:"+this.storeId+"," +
                "storeName:"+this.storeName+",productName:"+this.productName+",brand:"+this.brand +
                ",girard:"+this.girard+",sizePrice:"+this.sizePrice+",transactionRecord:"+this.transactionRecord +
                ",createTime:"+this.createTime+",reservedField:"+this.reservedField+"}}";
    }

}
