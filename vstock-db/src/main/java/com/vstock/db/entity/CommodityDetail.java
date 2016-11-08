package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

/**
 * Created by administor on 2016/5/12.
 */
public class CommodityDetail {

    @Id
    private String id;
    private int bid;
    private String commodityDataId;
    private String transactionRecord;
    private String colorSize;
    private String createDate;

    public String getCommodityDataId() {
        return commodityDataId;
    }

    public void setCommodityDataId(String commodityDataId) {
        this.commodityDataId = commodityDataId;
    }

    public String getColorSize() {
        return colorSize;
    }

    public void setColorSize(String colorSize) {
        this.colorSize = colorSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTransactionRecord() {
        return transactionRecord;
    }

    public void setTransactionRecord(String transactionRecord) {
        this.transactionRecord = transactionRecord;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public CommodityDetail() {
    }

    public CommodityDetail(String id, int bid, String commodityDataId, String transactionRecord, String colorSize, String createDate) {
        this.id = id;
        this.bid = bid;
        this.commodityDataId = commodityDataId;
        this.transactionRecord = transactionRecord;
        this.colorSize = colorSize;
        this.createDate = createDate;
    }
}
