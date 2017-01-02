package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

public class UserAccount {

    public static final String ACCOUNT_TYPE_FILE = "0";
    public static final String ACCOUNT_TYPE_SUCCESS = "1";

    @Id
    private int id;
    private String userId;
    private String uname;
    private String alipay_account;
    private String identify_no;
    private String identify_img_front;
    private String identify_img_back;
    private String identify_img_handheld;
    private String status;
    private String update_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAlipay_account() {
        return alipay_account;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public String getIdentify_no() {
        return identify_no;
    }

    public void setIdentify_no(String identify_no) {
        this.identify_no = identify_no;
    }

    public String getIdentify_img_front() {
        return identify_img_front;
    }

    public void setIdentify_img_front(String identify_img_front) {
        this.identify_img_front = identify_img_front;
    }

    public String getIdentify_img_back() {
        return identify_img_back;
    }

    public void setIdentify_img_back(String identify_img_back) {
        this.identify_img_back = identify_img_back;
    }

    public String getIdentify_img_handheld() {
        return identify_img_handheld;
    }

    public void setIdentify_img_handheld(String identify_img_handheld) {
        this.identify_img_handheld = identify_img_handheld;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public UserAccount() {
    }

    public UserAccount(int id, String userId, String uname, String alipay_account, String identify_no, String identify_img_front, String identify_img_back, String identify_img_handheld, String status, String update_time) {
        this.id = id;
        this.userId = userId;
        this.uname = uname;
        this.alipay_account = alipay_account;
        this.identify_no = identify_no;
        this.identify_img_front = identify_img_front;
        this.identify_img_back = identify_img_back;
        this.identify_img_handheld = identify_img_handheld;
        this.status = status;
        this.update_time = update_time;
    }
}
