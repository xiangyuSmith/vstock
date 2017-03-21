package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;

public class BasicinformationTemporary implements Serializable {

    @Id
    private Integer id;
    private String brand;
    private String name;
    private Integer type;
    private String artNo;
    private String colores;
    private String csaledate;
    private String esaledate;
    private BigDecimal cofferprice;
    private BigDecimal eofferprice;
    private String chineselogo;
    private String imgUrl;
    private String smallImgUrl;
    private String state;
    private String transactionRecord;
    private BigDecimal minimumSellingPrice;
    private BigDecimal highestBid;
    private BigDecimal changeRange;
    private BigDecimal percentageChange;
    private String createtime;
    private String updatetime;

    public BasicinformationTemporary() {
        super();
    }

    public BasicinformationTemporary(Integer id, String brand, String name, Integer type, String artNo, String colores, String csaledate, String esaledate, BigDecimal cofferprice, BigDecimal eofferprice, String chineselogo, String imgUrl, String smallImgUrl, String state, String transactionRecord, BigDecimal minimumSellingPrice, BigDecimal highestBid, BigDecimal changeRange, BigDecimal percentageChange, String createtime, String updatetime) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.type = type;
        this.artNo = artNo;
        this.colores = colores;
        this.csaledate = csaledate;
        this.esaledate = esaledate;
        this.cofferprice = cofferprice;
        this.eofferprice = eofferprice;
        this.chineselogo = chineselogo;
        this.imgUrl = imgUrl;
        this.smallImgUrl = smallImgUrl;
        this.state = state;
        this.transactionRecord = transactionRecord;
        this.minimumSellingPrice = minimumSellingPrice;
        this.highestBid = highestBid;
        this.changeRange = changeRange;
        this.percentageChange = percentageChange;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getArtNo() {
        return artNo;
    }

    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    public String getColores() {
        return colores;
    }

    public void setColores(String colores) {
        this.colores = colores;
    }

    public String getCsaledate() {
        return csaledate;
    }

    public void setCsaledate(String csaledate) {
        this.csaledate = csaledate;
    }

    public String getEsaledate() {
        return esaledate;
    }

    public void setEsaledate(String esaledate) {
        this.esaledate = esaledate;
    }

    public BigDecimal getCofferprice() {
        return cofferprice;
    }

    public void setCofferprice(BigDecimal cofferprice) {
        this.cofferprice = cofferprice;
    }

    public BigDecimal getEofferprice() {
        return eofferprice;
    }

    public void setEofferprice(BigDecimal eofferprice) {
        this.eofferprice = eofferprice;
    }

    public String getChineselogo() {
        return chineselogo;
    }

    public void setChineselogo(String chineselogo) {
        this.chineselogo = chineselogo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTransactionRecord() {
        return transactionRecord;
    }

    public void setTransactionRecord(String transactionRecord) {
        this.transactionRecord = transactionRecord;
    }

    public BigDecimal getMinimumSellingPrice() {
        return minimumSellingPrice;
    }

    public void setMinimumSellingPrice(BigDecimal minimumSellingPrice) {
        this.minimumSellingPrice = minimumSellingPrice;
    }

    public BigDecimal getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(BigDecimal highestBid) {
        this.highestBid = highestBid;
    }

    public BigDecimal getChangeRange() {
        return changeRange;
    }

    public void setChangeRange(BigDecimal changeRange) {
        this.changeRange = changeRange;
    }

    public BigDecimal getPercentageChange() {
        return percentageChange;
    }

    public void setPercentageChange(BigDecimal percentageChange) {
        this.percentageChange = percentageChange;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
