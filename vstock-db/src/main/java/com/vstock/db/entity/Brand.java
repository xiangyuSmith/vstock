package com.vstock.db.entity;

/**
 * Created by xiangyu on 2016/7/12.
 */
public class Brand {
    private String id;
    private String brandName;
    private int status;
    private String updateTime;

    public Brand() {
    }

    public Brand(String id, String brandName, int status, String updateTime) {
        this.id = id;
        this.brandName = brandName;
        this.status = status;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
