package com.vstock.db.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by administor on 2016/12/14.
 */
public class CityAddress implements Serializable {
    @Id
    private Integer id;
    private Integer code;
    private Integer parentId;
    private String name;
    private Integer level;

    public CityAddress() {
        super();
    }

    public CityAddress(Integer id, Integer code, Integer parentId, String name, Integer level) {
        this.id = id;
        this.code = code;
        this.parentId = parentId;
        this.name = name;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
