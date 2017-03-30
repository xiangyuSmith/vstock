package com.vstock.db.entity;

public class Permissions {

    public String id;
    public String parentId;
    public String name;
    public String enName;
    public String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Permissions() {
    }

    public Permissions(String id, String parentId, String name, String enName, String updateTime) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.enName = enName;
        this.updateTime = updateTime;
    }
}
