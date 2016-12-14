package com.vstock.db.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class BasicinformationRose {
    private long id;
    private int basicinformation_id;
    private String basicinformation_size;
    private BigDecimal current_market_value;
    private BigDecimal change_range;
    private BigDecimal percentage_change;
    private int type;
    private Date create_date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBasicinformation_id() {
        return basicinformation_id;
    }

    public void setBasicinformation_id(int basicinformation_id) {
        this.basicinformation_id = basicinformation_id;
    }

    public String getBasicinformation_size() {
        return basicinformation_size;
    }

    public void setBasicinformation_size(String basicinformation_size) {
        this.basicinformation_size = basicinformation_size;
    }

    public BigDecimal getCurrent_market_value() {
        return current_market_value;
    }

    public void setCurrent_market_value(BigDecimal current_market_value) {
        this.current_market_value = current_market_value;
    }

    public BigDecimal getChange_range() {
        return change_range;
    }

    public void setChange_range(BigDecimal change_range) {
        this.change_range = change_range;
    }

    public BigDecimal getPercentage_change() {
        return percentage_change;
    }

    public void setPercentage_change(BigDecimal percentage_change) {
        this.percentage_change = percentage_change;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public BasicinformationRose() {
    }

    public BasicinformationRose(long id, int basicinformation_id, String basicinformation_size, BigDecimal current_market_value, BigDecimal change_range, BigDecimal percentage_change, int type, Date create_date) {
        this.id = id;
        this.basicinformation_id = basicinformation_id;
        this.basicinformation_size = basicinformation_size;
        this.current_market_value = current_market_value;
        this.change_range = change_range;
        this.percentage_change = percentage_change;
        this.type = type;
        this.create_date = create_date;
    }

    /**
     * 求差
     * @return
     */
    public static BigDecimal getDifference(BigDecimal v1, BigDecimal v2){
        return v1.subtract(v2).abs();
    }

    /**
     * 百分比
     * @return
     */
    public static BigDecimal getPercentag(BigDecimal v1, BigDecimal v2){
        return getDifference(v1,v2).divide(v1,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
    }
}
