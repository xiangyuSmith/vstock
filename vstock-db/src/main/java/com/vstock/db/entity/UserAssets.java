package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserAssets implements Serializable {

    public final static String USER_ASSETS_MD5_MARK = "@_@";

    public final static String USER_ASSETS_MD5_MARK_NOTIFY = "~o~";

    @Id
    private Integer id;
    private Integer userId;
    private Integer basicinformationId;
    private String userAssetsSize;
    private String purchaseDate;
    private BigDecimal money;
    private Integer status;
    private String createDate;
    private String invalidDate;
    private String sgin;
    private BasicinformationRose basicinformationRose;
    private Basicinformation basicinformation;

    public UserAssets() {
        super();
    }

    public UserAssets(Integer id, Integer userId, Integer basicinformationId, String userAssetsSize, String purchaseDate, BigDecimal money, Integer status, String createDate, String invalidDate, String sgin) {
        this.id = id;
        this.userId = userId;
        this.basicinformationId = basicinformationId;
        this.userAssetsSize = userAssetsSize;
        this.purchaseDate = purchaseDate;
        this.money = money;
        this.status = status;
        this.createDate = createDate;
        this.invalidDate = invalidDate;
        this.sgin = sgin;
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

    public Integer getBasicinformationId() {
        return basicinformationId;
    }

    public void setBasicinformationId(Integer basicinformationId) {
        this.basicinformationId = basicinformationId;
    }

    public String getUserAssetsSize() {
        return userAssetsSize;
    }

    public void setUserAssetsSize(String userAssetsSize) {
        this.userAssetsSize = userAssetsSize;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getSgin() {
        return sgin;
    }

    public void setSgin(String sgin) {
        this.sgin = sgin;
    }

    public BasicinformationRose getBasicinformationRose() {
        return basicinformationRose;
    }

    public void setBasicinformationRose(BasicinformationRose basicinformationRose) {
        this.basicinformationRose = basicinformationRose;
    }

    public Basicinformation getBasicinformation() {
        return basicinformation;
    }

    public void setBasicinformation(Basicinformation basicinformation) {
        this.basicinformation = basicinformation;
    }
}
