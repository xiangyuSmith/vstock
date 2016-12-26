package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class UserAddress implements Serializable {

    @Id
    private Integer id;
    private Integer userId;
    private String localArea;
    private String detailedAddress;
    private String zipCode;
    private String consigneeName;
    private String phoneNumber;
    private String landlineNumber;
    private Integer status;
    private Integer type;
    private String createDate;
    private String invalidDate;
    private String nick;

    public UserAddress(Integer id, Integer userId, String localArea, String detailedAddress, String zipCode, String consigneeName, String phoneNumber, String landlineNumber, Integer status, Integer type, String createDate, String invalidDate, String nick) {
        this.id = id;
        this.userId = userId;
        this.localArea = localArea;
        this.detailedAddress = detailedAddress;
        this.zipCode = zipCode;
        this.consigneeName = consigneeName;
        this.phoneNumber = phoneNumber;
        this.landlineNumber = landlineNumber;
        this.status = status;
        this.type = type;
        this.createDate = createDate;
        this.invalidDate = invalidDate;
        this.nick = nick;
    }

    public UserAddress() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLocalArea() {
        return localArea;
    }

    public void setLocalArea(String localArea) {
        this.localArea = localArea;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
