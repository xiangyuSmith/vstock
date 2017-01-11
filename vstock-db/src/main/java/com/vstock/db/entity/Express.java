package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Express implements Serializable {

    @Id
    private Integer id;
    private String expressName;
    private String status;
    private String level;
    private String createDate;
    private String updateDate;

    public Express() {
        super();
    }

    public Express(Integer id, String expressName, String status, String level, String createDate, String updateDate) {
        this.id = id;
        this.expressName = expressName;
        this.status = status;
        this.level = level;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
