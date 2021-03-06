package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class UserInvitation implements Serializable {

    public static final int INVITATION_Already_USE = 1;

    public static final int INVITATION_NOT_USED = 0;

    @Id
    private Integer id;
    private String code;
    private String createTime;
    private String status;
    private String reservedField;
    private String reservedField1;
    private String reservedField2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReservedField() {
        return reservedField;
    }

    public void setReservedField(String reservedField) {
        this.reservedField = reservedField;
    }

    public String getReservedField1() {
        return reservedField1;
    }

    public void setReservedField1(String reservedField1) {
        this.reservedField1 = reservedField1;
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserInvitation(Integer id, String code, String createTime, String status, String reservedField, String reservedField1, String reservedField2) {
        this.id = id;
        this.code = code;
        this.createTime = createTime;
        this.status = status;
        this.reservedField = reservedField;
        this.reservedField1 = reservedField1;
        this.reservedField2 = reservedField2;
    }

    public UserInvitation() {
        super();
    }
}
