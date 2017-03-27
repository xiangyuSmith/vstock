package com.vstock.db.entity;

public class UserActivity {

    private String id;

    private String name;

    private String phone;

    private String is_code;

    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIs_code() {
        return is_code;
    }

    public void setIs_code(String is_code) {
        this.is_code = is_code;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public UserActivity() {
    }

    public UserActivity(String id, String name, String phone, String is_code, String update_time) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.is_code = is_code;
        this.update_time = update_time;
    }
}
