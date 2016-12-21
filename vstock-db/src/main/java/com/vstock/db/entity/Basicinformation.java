package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Basicinformation implements Serializable {

    private static final long serialVersionUID = 165641247456211L;

    public final static int B_TYPE_ZERO = 0; // 0默认分类
    public final static int B_TYPE_ONE = 1; // 更多流行
    public final static int B_TYPE_TWO = 2; // 最低卖价
    public final static int B_TYPE_THREE = 3; // 最高售价
    public final static int B_TYPE_FOUR = 4; // 最大涨幅
    public final static int B_TYPE_FIVE = 5; // 即将发布

    public final static String[] sizes = {"35","35.5","36","36.5","37","37.5","38","38.5","39","39.5","40","40.5","41","41.5","42","42.5"
    ,"43","43.5","44","44.5","45","45.5","46","46.5","47","47.5","48","48.5"};

    /**
     * sizes 是否包含 str
     * @param str
     * @return
     */
    public static String isContainsSizes(String str){
        if(str == null){
            return "";
        }
        for(int i = 0;i<sizes.length;i++){
            if(sizes[i].contains(str)){
                return str;
            }
        }
        return "40";
    }

    @Id
    private String id;
    private int bid;
    private String brand;
    private String name;
    private String artNo;
    private int type;
    private String colores;
    private String csaledate;
    private String esaledate;
    private Double cofferprice;
    private Double cofferprices;
    private String bscofferprice;
    private String eofferprice;
    private String chineselogo;
    private String chineselogos;
    private String imgUrl;
    private String smallImgUrl;
    private String createtime;
    private String state;
    private ResultDataFactory resultDataFactory;

    public Basicinformation(String id, int bid, String brand, String name, String artNo, int type, String colores, String csaledate, String esaledate, Double cofferprice, Double cofferprices, String bscofferprice, String eofferprice, String chineselogo, String chineselogos, String imgUrl, String smallImgUrl, String createtime, String state, ResultDataFactory resultDataFactory) {
        this.id = id;
        this.bid = bid;
        this.brand = brand;
        this.name = name;
        this.artNo = artNo;
        this.type = type;
        this.colores = colores;
        this.csaledate = csaledate;
        this.esaledate = esaledate;
        this.cofferprice = cofferprice;
        this.cofferprices = cofferprices;
        this.bscofferprice = bscofferprice;
        this.eofferprice = eofferprice;
        this.chineselogo = chineselogo;
        this.chineselogos = chineselogos;
        this.imgUrl = imgUrl;
        this.smallImgUrl = smallImgUrl;
        this.createtime = createtime;
        this.state = state;
        this.resultDataFactory = resultDataFactory;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Basicinformation() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Double getCofferprice() {
        return cofferprice;
    }

    public void setCofferprice(Double cofferprice) {
        this.cofferprice = cofferprice;
    }

    public String getEofferprice() {
        return eofferprice;
    }

    public void setEofferprice(String eofferprice) {
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getCofferprices() {
        return cofferprices;
    }

    public void setCofferprices(Double cofferprices) {
        this.cofferprices = cofferprices;
    }

    public String getChineselogos() {
        return chineselogos;
    }

    public void setChineselogos(String chineselogos) {
        this.chineselogos = chineselogos;
    }

    public String getBscofferprice() {
        return bscofferprice;
    }

    public void setBscofferprice(String bscofferprice) {
        this.bscofferprice = bscofferprice;
    }

    public ResultDataFactory getResultDataFactory() {
        return resultDataFactory;
    }

    public void setResultDataFactory(ResultDataFactory resultDataFactory) {
        this.resultDataFactory = resultDataFactory;
    }
}
