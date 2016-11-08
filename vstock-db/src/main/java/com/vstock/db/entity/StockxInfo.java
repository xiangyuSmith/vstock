package com.vstock.db.entity;

/**
 * Created by xiangyu on 2016/7/13.
 */
public class StockxInfo {
    private String id;
    private String brandId;
    private String name;
    private String interfacesApi;
    private int pageNum;
    private int category;
    private String jsonText;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfacesApi() {
        return interfacesApi;
    }

    public void setInterfacesApi(String interfacesApi) {
        this.interfacesApi = interfacesApi;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StockxInfo() {
    }

    public StockxInfo(String id, String brandId, String name, String interfacesApi, int pageNum, int category, String jsonText, String time) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.interfacesApi = interfacesApi;
        this.pageNum = pageNum;
        this.category = category;
        this.jsonText = jsonText;
        this.time = time;
    }
}
