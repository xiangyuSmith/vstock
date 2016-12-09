package com.vstock.db.entity;

public class VstockConfig {

    private String key;

    private String value;

    private int status;

    public VstockConfig() {
    }

    public VstockConfig(String key, String value, int status) {
        this.key = key;
        this.value = value;
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
