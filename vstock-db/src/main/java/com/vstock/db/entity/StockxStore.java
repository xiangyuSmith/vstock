package com.vstock.db.entity;

/**
 * Created by administor on 2016/5/11.
 */
public class StockxStore {
    private String id;
    private String name;
    private String brand;
    private String url;
    private String status;
    private String create_user;
    private String update_user;
    private String create_time;
    private String update_time;
    private String pageNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public StockxStore() {
    }

    public StockxStore(String id, String name, String brand, String url, String status, String create_user, String update_user, String create_time, String update_time, String pageNo) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.url = url;
        this.status = status;
        this.create_user = create_user;
        this.update_user = update_user;
        this.create_time = create_time;
        this.update_time = update_time;
        this.pageNo = pageNo;
    }
}
