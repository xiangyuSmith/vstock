package com.vstock.db.entity;

import java.math.BigDecimal;

public class Payment {

    public static final int  PAY_SOURCE_ALIPAY = 0;

    public final static String PAY_MD5_MARK = "|";

    public final static String PAY_MD5_MARK_NOTIFY = "~`|~`";

    public final static int PAY_STATUS_NOTIFIY = 0; // 待付款
    public final static int PAY_STATUS_SUCCESS = 10; // 支付成功
    public final static int PAY_STATUS_FILE = 11; // 支付失败

    private int id;
    private long payment_user_id;
    private long order_record_id;
    private String payment_number;
    private int payment_status;
    private int payment_mode;
    private int payment_type;
    private String payment_date;
    private String payment_over_date;
    private BigDecimal payment_money;
    private String payment_explain;
    private String sign;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPayment_user_id() {
        return payment_user_id;
    }

    public void setPayment_user_id(long payment_user_id) {
        this.payment_user_id = payment_user_id;
    }

    public long getOrder_record_id() {
        return order_record_id;
    }

    public void setOrder_record_id(long order_record_id) {
        this.order_record_id = order_record_id;
    }

    public String getPayment_number() {
        return payment_number;
    }

    public void setPayment_number(String payment_number) {
        this.payment_number = payment_number;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPayment_over_date() {
        return payment_over_date;
    }

    public void setPayment_over_date(String payment_over_date) {
        this.payment_over_date = payment_over_date;
    }

    public BigDecimal getPayment_money() {
        return payment_money;
    }

    public void setPayment_money(BigDecimal payment_money) {
        this.payment_money = payment_money;
    }

    public String getPayment_explain() {
        return payment_explain;
    }

    public void setPayment_explain(String payment_explain) {
        this.payment_explain = payment_explain;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(int payment_mode) {
        this.payment_mode = payment_mode;
    }

    public Payment() {
    }

    public Payment(int id, long payment_user_id, long order_record_id, String payment_number, int payment_status, int payment_mode, int payment_type, String payment_date, String payment_over_date, BigDecimal payment_money, String payment_explain, String sign) {
        this.id = id;
        this.payment_user_id = payment_user_id;
        this.order_record_id = order_record_id;
        this.payment_number = payment_number;
        this.payment_status = payment_status;
        this.payment_mode = payment_mode;
        this.payment_type = payment_type;
        this.payment_date = payment_date;
        this.payment_over_date = payment_over_date;
        this.payment_money = payment_money;
        this.payment_explain = payment_explain;
        this.sign = sign;
    }
}
