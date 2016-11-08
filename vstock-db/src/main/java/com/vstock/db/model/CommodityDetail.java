package com.vstock.db.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by administor on 2016/5/12.
 */
public class CommodityDetail implements Serializable {

    private static final long serialVersionUID = 11564562132187L;

    @Id
    private String id;
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

    public CommodityDetail(String id, String commodityDataId, String transactionRecord, String colorSize, String createDate) {
        this.id = id;
        this.commodityDataId = commodityDataId;
        this.transactionRecord = transactionRecord;
        this.colorSize = colorSize;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "{COMMODITYDATA:{id:"+this.id+",commodityDataId:"+this.commodityDataId+",transactionRecord:"+this.transactionRecord+"," +
                "colorSize:"+this.colorSize+",createDate:"+this.createDate+"}}";
    }
}
