package com.vstock.db.entity;

public class Role {

    private String id;

    private String name;

    private String status;

    private String create_time;

    private String update_time;

    private String role_permissions;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getRole_permissions() {
        return role_permissions;
    }

    public void setRole_permissions(String role_permissions) {
        this.role_permissions = role_permissions;
    }

    public Role() {
    }

    public Role(String id, String name, String status, String create_time, String update_time, String role_permissions) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.create_time = create_time;
        this.update_time = update_time;
        this.role_permissions = role_permissions;
    }
}
