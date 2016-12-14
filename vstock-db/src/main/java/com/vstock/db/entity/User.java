package com.vstock.db.entity;

/**
 * Created by sunson on 2015/11/21.
 */
public class User {

    public static final String SESSION_USER_ID = "VSTOCK-VID";

    public static final String REG_MD5_CODE = "vstock.user.register.md5.code";

    private String id;

    private String uname;

    private String password;

    private String mobile;

    private String nick;

    private String size;

    private String salt;

    private String last_login_ip;

    private String last_login_time;

    private int status;

    private String create_time;

    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLast_login_ip() {
        return last_login_ip;
    }

    public void setLast_login_ip(String last_login_ip) {
        this.last_login_ip = last_login_ip;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public User() {
    }

    public User(String id, String uname, String password, String mobile, String nick, String size, String salt, String last_login_ip, String last_login_time, int status, String create_time, String update_time) {
        this.id = id;
        this.uname = uname;
        this.password = password;
        this.mobile = mobile;
        this.nick = nick;
        this.size = size;
        this.salt = salt;
        this.last_login_ip = last_login_ip;
        this.last_login_time = last_login_time;
        this.status = status;
        this.create_time = create_time;
        this.update_time = update_time;
    }
}
